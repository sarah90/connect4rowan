import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JMenu;

/**
 *
 * @author Samir Brian Milkias
 */
public class DrawBoard implements MouseListener{

    private static int board[][];
    private static final int ROWS = 6;
    private static final int COLS = 7;

    JFrame frame;

    public DrawBoard()
    {
        board = new int[6][7];
        initComponents();
    }

    public void initComponents()
    {
        newBoard();
        frame = new JFrame("CONNECT 4!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menubar.add(menu);
        JMenuItem newGame = new JMenuItem("New Game");
        menu.add(newGame);
        newGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                newBoard();
                addComponentsToPane();
                frame.pack();
            }
        });
        frame.setJMenuBar(menubar);
        addComponentsToPane();
        frame.addMouseListener(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void addComponentsToPane()
    {
        ImageIcon blue = new ImageIcon("pics/blue.jpg");
        ImageIcon red = new ImageIcon("pics/red.jpg");
        ImageIcon blank = new ImageIcon("pics/empty.jpg");
//try{        

        frame.getContentPane().setLayout(new GridLayout(6,7));
        frame.getContentPane().removeAll();
//}catch(Exception e){}

        for(int i = 0; i < ROWS; i++)
            for(int j = 0; j < COLS; j++)
            {
                   if((board[i][j] == 0))
                      frame.getContentPane().add(new JLabel(blank));

                   else if((board[i][j] == 1))
                      frame.getContentPane().add(new JLabel(red));

                   else if((board[i][j] == 2))
                      frame.getContentPane().add(new JLabel(blue));
            }
    }

    private void newBoard()
    {
        for(int i = 0; i < ROWS; i++)
            for(int j = 0; j < COLS; j++)
            {
                board[i][j] = 0;
            }

        /*board[5][6]=2;
        board[4][6]=2;
        board[3][6]=2;
        board[5][5]=2;
        board[4][5]=2;
        board[5][4]=2;

        board[5][0]=2;
        board[4][0]=2;
        board[3][0]=2;
        board[5][1]=2;
        board[4][1]=2;
        board[5][2]=2;*/
       // addComponentsToPane();
         //       frame.pack();
    }

    //Returns true if player specified wins. False if not.
    public boolean checkWin(int row, int col, int player)
    {
        if(checkRowWin(row, col, player) || checkColWin(row, col, player)
                || checkDiagWin(row, col, player) || checkInvDiagWin(row, col, player))
        {
            JOptionPane.showMessageDialog (null, "WINNER WINNER CHICKEN DINNER: Player \n" +
                                player +
                                ". Press ok to start a new game.",
                                "WINNER", JOptionPane.INFORMATION_MESSAGE);
            newBoard();
            addComponentsToPane();
                frame.pack();
            return true;
        }
        else
            return false;
    }


    private boolean checkInvDiagWin(int row, int col, int player)
    {
        //if player is on these locations diagonal check it not needed (no WIN)
        if((row == 4 && col ==5)||(row ==4 && col ==6)||(row ==5&& col ==5)
            ||(row == 5 && col == 6 )||(row == 3 && col == 6)||(row == 0 && col ==0)||
            (row == 0 && col == 1)||(row == 1&& col == 0)||
            (row == 1 && col == 1)||(row == 0&& col == 2)
            || (row == 2&& col == 0)|| (row == 3&& col == 6))
        {
            return false;
        }
        int on_diagonal = 0;
        int temp_row = row + col; //set temp row where the check will start
        int temp_col = Math.abs(((temp_row - row)* 1)-col);

        if(temp_row > 5)
        {
            temp_row = 5; //adjust range to largest row
            temp_col = Math.abs(((temp_row - row)* 1)-col);
        }
        while(temp_row >=0 && temp_col <= 6)
        {
            if(board[temp_row][temp_col] == player)
            {
                on_diagonal++;
            }
            else
            {
                on_diagonal = 0;
            }
            temp_row --;
            temp_col++;
            if(on_diagonal == 4)
            {
                return true;
            }
        }
        return false; 
    }
    //Checks win along main diagnol. Upper left to lower right
    private boolean checkDiagWin(int row, int col, int player)
    {
        int inarow = 0;
        //Check Main Diagnol
        int min = min(row, col);
        int r = row - min;
        int c = col - min;
        while(r < ROWS && c < COLS)
        {
            if(board[r][c] == player)
            {
                inarow++;
            }
            else
            {
                inarow = 0;
            }
            if(inarow == 4)
            {
                System.out.println("diagwin");
                return true;
            }
            r++;
            c++;
        }
        return false;
    }

    //Checks win in the row the piece is placed
    private boolean checkRowWin(int row, int col, int player)
    {
        int inarow = 0;

        //Check row win
        for(int i = 0; i < 7; i++)
        {
            if(board[row][i] == player)
            {
                inarow++;
            }
            else
                inarow = 0;

            if(inarow == 4)
            {
                return true;
            }
        }
        return false;
    }

    //Checks win in the column piece is dropped.
    private boolean checkColWin(int row, int col, int player)
    {
        int inarow = 0;
        //Check col win
        for(int i = 0; i < 6; i ++)
        {
            if(board[i][col] == player)
            {
                inarow++;
            }
            else
                inarow = 0;

            if(inarow == 4)
            {
                return true;
            }            
        }
        return false;
    }

    private int min(int r, int c)
    {
        if(r < c)
            return r;
        else return c;
    }


    private boolean dropPiece(MouseEvent e, int player){
        int colPicked = e.getX() / (frame.getWidth() / COLS);

        for(int i = ROWS-1; i >= 0; i--){
            if(board[i][colPicked] == 0){
                board[i][colPicked] = player;
                addComponentsToPane();
                frame.pack();
                checkWin(i, colPicked, 1);
                return true;
            }
        }

        return false;
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == 1)
            dropPiece(e, 1);
        else
            dropPiece(e, 2);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}


}