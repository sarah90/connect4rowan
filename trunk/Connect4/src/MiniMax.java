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
                int strength = expandMaxNode(depth -1, maxStrength);
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
            return my_heuristic2(true);
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
            return my_heuristic2(false);
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
        int piece = 0;

        if(playersturn)
            piece = 1;
        else piece = 2;

        if(hasWinner(1))
        {
            return Integer.MIN_VALUE;
        }
        if(hasWinner(2))
        {
            return Integer.MAX_VALUE;
        }


        for(int row = 0; row < 6; row++)
        {
            for(int col = 0; col < 7; col++)
            {
                if ((col>=3)
                && (board[row][col-1] == piece)
                && (board[row][col-2] == piece)
                && (board[row][col-3] == piece))
                h=h+16;
                //right
                if ((col<=3)
                && (board[row][col+1] == piece)
                && (board[row][col+2] == piece)
                && (board[row][col+3] == piece))
                    h=h+16;
                //check y direction
                if ((row<=2)
                && (board[row+1][col] == piece)
                && (board[row+2][col] == piece)
                && (board[row+3][col] == piece))
                    h=h+16;
                //check left diagonal
                if ((col>=3) && (row<=2)
                && (board[row+1][col-1] == piece)
                && (board[row+2][col-2] == piece)
                && (board[row+3][col-3] == piece))
                    h=h+16;

                if ((col<=3) && (row<=2)
                && (board[row+1][col+1] == piece)
                && (board[row+2][col+2] == piece)
                && (board[row+3][col+3] == piece))
                    h=h+16;

                if ((col>=3) && (row>=3)
                && (board[row-1][col-1] == piece)
                && (board[row-2][col-2] == piece)
                && (board[row-3][col-3] == piece))
                    h=h+16;

                if ((col<=3) && (row>=3)
                && (board[row-1][col+1] == piece)
                && (board[row-2][col+2] == piece)
                && (board[row-3][col+3] == piece))
                    h=h+16;

                if ((col>=2)
                && (board[row][col-1] == piece)
                && (board[row][col-2] == piece))
                    h=h+4;
                //right
                if ((col<=4)
                && (board[row][col+1] == piece)
                && (board[row][col+2] == piece))
                    h=h+4;
                //check y direction
                if ((row<=3)
                && (board[row+1][col] == piece)
                && (board[row+2][col] == piece))
                    h=h+4;
                //check left diagonal
                if ((col>=2) && (row<=3)
                && (board[row+1][col-1] == piece)
                && (board[row+2][col-2] == piece))
                    h=h+4;

                if ((col<=4) && (row<=3)
                && (board[row+1][col+1] == piece)
                && (board[row+2][col+2] == piece))
                    h=h+4;

                if ((col>=2) && (row>=2)
                && (board[row-1][col-1] == piece)
                && (board[row-2][col-2] == piece))
                h=h+4;

                if ((col<=4) && (row>=2)
                && (board[row-1][col+1] == piece)
                && (board[row-2][col+2] == piece))
                    h=h+4;

                if ((col>=1)
                && (board[row][col-1] == piece))
                    h=h+2;
                //right

                if ((col<=5)
                && (board[row][col+1] == piece))
                    h=h+2;
                //check y direction
                if ((row<=4)
                && (board[row+1][col] == piece))
                    h=h+2;
                //check left diagonal
                if ((col>=1) && (row<=4)
                && (board[row+1][col-1] == piece))
                    h=h+2;

                if ((col<=5) && (row<=4)
                && (board[row+1][col+1] == piece))
                    h=h+2;

                if ((col>=1) && (row>=1)
                && (board[row-1][col-1] == piece))
                    h=h+2;

                if ((col<=5) && (row>=1)
                && (board[row-1][col+1] == piece))
                    h=h+2;


                //Defensive check
                //check x direction.
                //left
                if ((col>=3)
                && (board[row][col-1] == 3 - piece)
                && (board[row][col-2] == 3 - piece)
                && (board[row][col-3] == 3 - piece))
                    h=h+14;
                //right
                if ((col<=3)
                && (board[row][col+1] == 3 - piece)
                && (board[row][col+2] == 3 - piece)
                && (board[row][col+3] == 3 - piece))
                   h=h+14;
               //check y direction
                if ((row<=2)
                && (board[row+1][col] == 3 - piece)
                && (board[row+2][col] == 3 - piece)
                && (board[row+3][col] == 3 - piece))
                   h=h+14;
               //check left diagonal
                if ((col>=3) && (row<=2)
                && (board[row+1][col-1] == 3 - piece)
                && (board[row+2][col-2] == 3 - piece)
                && (board[row+3][col-3] == 3 - piece))
                    h=h+14;

                if ((col<=3) && (row<=2)
                && (board[row+1][col+1] == 3 - piece)
                && (board[row+2][col+2] == 3 - piece)
                && (board[row+3][col+3] == 3 - piece))
                    h=h+14;

                if ((col>=3) && (row>=3)
                && (board[row-1][col-1] == 3 - piece)
                && (board[row-2][col-2] == 3 - piece)
                && (board[row-3][col-3] == 3 - piece))
                    h=h+14;

                if ((col<=3) && (row>=3)
                && (board[row-1][col+1] == 3 - piece)
                && (board[row-2][col+2] == 3 - piece)
                && (board[row-3][col+3] == 3 - piece))
                    h=h+14;

                    if ((col>=2)
                && (board[row][col-1] == 3 - piece)
                && (board[row][col-2] == 3 - piece))
                    h=h+4;
                //right
                if ((col<=4)
                && (board[row][col+1] == 3 - piece)
                && (board[row][col+2] == 3 - piece))
                    h=h+4;
                //check y direction
                if ((row<=3)
                && (board[row+1][col] == 3 - piece)
                && (board[row+2][col] == 3 - piece))
                    h=h+4;
                //check left diagonal
                if ((col>=2) && (row<=3)
                && (board[row+1][col-1] == 3 - piece)
                && (board[row+2][col-2] == 3 - piece))
                    h=h+4;

                if ((col<=4) && (row<=3)
                && (board[row+1][col+1] == 3 - piece)
                && (board[row+2][col+2] == 3 - piece))
                    h=h+4;

                if ((col>=2) && (row>=2)
                && (board[row-1][col-1] == 3 - piece)
                && (board[row-2][col-2] == 3 - piece))
                    h=h+4;

                if ((col<=4) && (row>=2)
                && (board[row-1][col+1] == 3 - piece)
                && (board[row-2][col+2] == 3 - piece))
                    h=h+4;

                if ((col>=1)
                && (board[row][col-1] == 3 - piece))
                    h=h+2;
                //right

                if ((col<=5)
                && (board[row][col+1] == 3 - piece))
                    h=h+2;
                //check y direction
                if ((row<=4)
                && (board[row+1][col] == 3 - piece))
                    h=h+2;
                //check left diagonal
                if ((col>=1) && (row<=4)
                && (board[row+1][col-1] == 3 - piece))
                    h=h+2;

                if ((col<=5) && (row<=4)
                && (board[row+1][col+1] == 3 - piece))
                    h=h+2;

                if ((col>=1) && (row>=1)
                && (board[row-1][col-1] == 3 - piece))
                    h=h+2;

                if ((col<=5) && (row>=1)
                && (board[row-1][col+1] == 3 - piece))
                    h=h+2;
        }
     }

        System.out.println("\nH = "+h);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(board[i][j]);

            }
            System.out.println();
        }


        return h;
    }

    private int my_heuristic2(boolean playersturn)
    {
        int h = 0;
        int piece = 0;

        if(playersturn)
            piece = 1;
        else piece = 2;

        if(hasWinner(1))
        {
            return Integer.MIN_VALUE;
        }
        if(hasWinner(2))
        {
            return Integer.MAX_VALUE;
        }


        for(int row = 5; row >= 0; row--)
        {
            for(int col = 0; col < 7; col++)
            {
                //check 2 in a row
                //vertical check
                if (    row <= 3 &&
                        board[row][col] == 2 &&
                        board[row+1][col] == 1 &&
                        board[row+2][col] == 1)
                    h += 10;


                //horizontal check
                if (    col <= 3 &&
                        board[row][col] == 2 &&
                        board[row][col+1] == 1 &&
                        board[row][col+2] == 1)
                    h += 10;

                if (    col >= 3 &&
                        board[row][col] == 2 &&
                        board[row][col-1] == 1 &&
                        board[row][col-2] == 1)
                    h += 10;


                //check 3 in a row

                //vertical check
                if (    row <= 2 &&
                        board[row][col] == 2 &&
                        board[row+1][col] == 1 &&
                        board[row+2][col] == 1 &&
                        board[row+3][col] == 1)
                    h += 10;


                //horiztonal check
                if (    col <= 3 &&
                        board[row][col] == 2 &&
                        board[row][col+1] == 1 &&
                        board[row][col+2] == 1 &&
                        board[row][col+3] == 1)
                    h += 30;

                if (    col >= 3 &&
                        board[row][col] == 2 &&
                        board[row][col-1] == 1 &&
                        board[row][col-2] == 1 &&
                        board[row][col-3] == 1)
                    h += 30;
            }
        }

                System.out.println("\nH = "+h);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(board[i][j]);

            }
            System.out.println();
        }

        return h;

    }

    private boolean hasWinner(int piece)
    {
        for(int row = 0; row < 6; row ++)
            for(int col = 0; col < 7; col ++)
            {
                //horizontal checks for winner
                if((col >= 3) && (board[row][col] == piece) && (board[row][col-1] == piece) &&
                        (board[row][col-2] == piece) && (board[row][col-3] == piece))
                    return true;

                if((col <= 3) && (board[row][col] == piece) && (board[row][col+1] == piece) &&
                        (board[row][col+2] == piece) && (board[row][col+3] == piece))
                    return true;

                if((row <= 2) && (board[row][col] == piece) && (board[row+1][col] == piece)
                         && (board[row+2][col] == piece) && (board[row+3][col] == piece))
                    return true;
            }
       return false;
    }

}