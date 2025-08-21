import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        new Game();
    }
    int totalMines = 99;
    boolean color = false;
    boolean start = true;
    boolean gameOver = false;

    int boardWidth = 720;
    int boardHeight = 600;

    JFrame frame = new JFrame("MineSweeper");
    JPanel board = new JPanel();
    JButton[][] grid = new JButton[20][24];

    Game() {
        board.setLayout(new GridLayout(20, 24));
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(board, BorderLayout.CENTER);
        
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                JButton tile = new JButton();
                grid[i][j] = tile;
                board.add(tile);
                tile.setFont(new Font("Arial", Font.BOLD, 20));

                if((i+j) % 2 == 0) {
                    grid[i][j].setBackground(new Color(76, 175, 80));
                } 
                else {
                    grid[i][j].setBackground(new Color(167, 216, 161));
                }

                grid[i][j].setForeground(Color.white);
                tile.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();
                        if (start) {
                            start = false;
                            while(totalMines > 0) {
                                int x = (int) (Math.random() * 20);
                                int y = (int) (Math.random() * 24);
                                // if (grid[x][y].isMine()){

                                // }
                            }
                        }
                    }
                });
            }

        }
    }
}

