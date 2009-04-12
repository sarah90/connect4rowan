/**
 *
 * @author Samir, Brian, and Milkias
 */
public class MiniMax {
    final int NUM_COLS = 7;
    boolean col_full = true;
    int[][] board;
    private final int MAX_POSSIBLE_VALUE;
    private final int MIN_POSSIBLE_VALUE;


    public MiniMax(int[][] b)
    {
        board = b;
        MAX_POSSIBLE_VALUE = Integer.MAX_VALUE;
        MIN_POSSIBLE_VALUE = Integer.MIN_VALUE;

    }

  public int minMax (int depth)
  {
      if(depth == 0)
      {
          System.out.println("error 0 depth.");
          return -1;
      }
      int maxStrength = MIN_POSSIBLE_VALUE;
      int maxIndex = 0;

      for(int j = 0; j < NUM_COLS; j++)
      {
          if(!isFull(j))
          {
                move(j, false);
                int strength = expandMinNode(depth -1, maxStrength);
                if(strength > maxStrength)
                {
                    maxStrength = strength;
                    maxIndex = j;
                }
                unmove(j);

          }
      }
    return maxIndex;
  }

  public int expandMaxNode (int depth, int parentMin)
  {
        if (depth == 0)
        {
            return my_heuristic(true);
        }
        int maxStrength = MIN_POSSIBLE_VALUE;

        for(int j = 0; j < NUM_COLS; j++)
        {
          if(!isFull(j))
          {
                move(j, true);
                int strength = expandMinNode(depth -1, maxStrength);
                if(strength > parentMin)
                {
                    unmove(j);
                    return strength;
                }
                if(strength > maxStrength)
                {
                    maxStrength = strength;
                }
                unmove(j);
          }
        }
        return maxStrength;
}


  private int expandMinNode(int depth, int parentMax)
  {
      if(depth == 0)
      {
            return my_heuristic(false);
      }

      int minStrength = MAX_POSSIBLE_VALUE;

        for(int j = 0; j < NUM_COLS; j++)
        {
          if(!isFull(j))
          {
                move(j, false);
                int strength = expandMaxNode(depth -1, minStrength);
                if(strength < parentMax)
                {
                    unmove(j);
                    return strength;
                }
                if(strength < minStrength)
                {
                    minStrength = strength;
                }
                unmove(j);
          }
        }
        return minStrength;
}

   //removes the last move from the specified column.
   private void unmove(int j)
   {
        for(int i = 0; i <= 5; i++){
            if(board[i][j] != 0){
                board[i][j] = 0;
                break;
            }
        }
   }
   //places a piece in the specified column for the player.
    private void move(int j, boolean player)
    {
        for(int i = 5; i >= 0; i--){
            if(board[i][j] == 0){
                if(player)
                    board[i][j] = 1;
                else board[i][j] = 2;

                break;
            }
        }
    }

    //returns true if a column is full, false otherwise.
    private boolean isFull(int j)
    {
        if(board[0][j] != 0)
            return true;
        else
            return false;
    }

    private int my_heuristic(boolean playersturn)
    {
        int h = 0;
        int temp = 0;
        int piece = 0;

        if(playersturn)
            piece = 1;
        else piece = 2;

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == piece){

                    if(j == 0) temp+= 1;
                    if(j == 1) temp += 2;
                    if(j == 2) temp += 3;
                    if(j == 3) temp += 4;
                    if(j == 4) temp += 3;
                    if(j == 5) temp += 2;
                    if(j == 6) temp += 1;

//                    if(j == 0) temp += 1;
//                    if(j == 1) temp += 2;
//                    if(j == 2) temp += 3;
//                    if(j == 3) temp += 3;
//                    if(j == 4) temp += 2;
//                    if(j == 5) temp += 1;



                }
            }
        }

        return temp;
    }


}