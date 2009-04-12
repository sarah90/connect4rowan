/**
 *
 * @author bp2070
 */
public class MiniMax {
    final int NUM_COLS = 7;
    boolean col_full = true;

    int expand(int[][] board, int depth, boolean playersturn)
    {
        int hvalue = 0;
        if(depth == 0)
        {
            return my_heuristic(board, playersturn);
        }
        else
        {
            if(playersturn)
            {
                hvalue = 0;
            }
            else
                hvalue = Integer.MAX_VALUE;
            for(int j = 0; j < NUM_COLS; j++)
            {
                if(!isFull(board, j)){
                    move(board, j, playersturn);
                    int temp = expand(board, depth - 1, !playersturn);
                    if(playersturn && temp > hvalue)
                    {
                        hvalue = temp;
                    }
                    else if(!playersturn && temp < hvalue)
                    {
                        hvalue = temp;
                    }
                    unmove(board, j);
                }
            }
        }
        return hvalue;

    }

    public int minimax(int [] [] board, int depth, boolean player)
    {
        int min = Integer.MAX_VALUE;
        int h = -1;
        int temp = 0;
        for(int j = 0; j < NUM_COLS; j++)
        {
            if(!isFull(board, j))
            {
                board = move(board, j, player);
                temp = expand(board, depth, player);
                if(temp < min)
                {
                    min = temp;
                    h = j;
                }
                board = unmove(board, j);
            }

        }
        return h;

    }

   private int[][] unmove(int[][] b, int j)
   {
        for(int i = 0; i < 6; i++){
            if(b[i][j] != 0){
                b[i][j] = 0;
                break;
            }
        }
        return b;
   }
    private int[][] move(int[][] b, int j, boolean player)
    {
        for(int i = 5; i > 0; i--){
            if(b[i][j] == 0){
                if(player)
                    b[i][j] = 1;
                else b[i][j] = 2;
                break;
            }
        }
        return b;
    }
    private boolean isFull(int[][] board, int j)
    {
        if(board[0][j] != 0)
            return true;
        else
            return false;
    }


    private int my_heuristic(int[][] board, boolean playersturn)
    {
        int h = 0;
        int temp = 0;
        int piece = 0;

        if(playersturn)
            piece = 1;
        else piece = 2;

        for(int i = 0; i < board.length -1; i++){
            for(int j = 0; j < board.length -1; j++){
                if(board[i][j] == piece){

                    if(i == 0) temp+= 1;
                    if(i == 1) temp += 2;
                    if(i == 2) temp += 3;
                    if(i == 3) temp += 4;
                    if(i == 4) temp += 3;
                    if(i == 5) temp += 2;
                    if(i == 6) temp += 1;

                    if(j == 0) temp += 1;
                    if(j == 1) temp += 2;
                    if(j == 2) temp += 3;
                    if(j == 3) temp += 3;
                    if(j == 4) temp += 2;
                    if(j == 5) temp += 1;



                }
            }
        }

        return temp;


    }

}
