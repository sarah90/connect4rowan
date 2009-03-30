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
            return my_heuristic(board);
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
        
    
            
        return depth;
    }
    
    public int minimax(int [] [] board, int depth, boolean player)
    { 
        int min = Integer.MAX_VALUE;
        int move = -1;
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
                    move = j;                   
                }
                board = unmove(board, j);
            }
            
        }
        return move;
        
    }
    
   private int[][] unmove(int[][] b, int j)
   {
        for(int i = 0; i < 6; i++){
            if(b[i][j] != 0){
                b[i][j] = 0;
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
            }
        }
        return b;
    }
    private boolean isFull(int[][] board, int j)
    {
        if(board[5][j] != 0)
            return true;
        else
            return false;
    }
    
    
    private int my_heuristic(int[][] board )
    {
        return (int)Math.random();
    }
    
}
