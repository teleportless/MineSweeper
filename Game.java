import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        new Game();
    }

    boolean start = true;
    boolean gameOver = false;
    int totalMines = 99;
    int elapsedTime = 0;

    final int rows  = 20;
    final int cols = 24;
    final int tileSize = 30;

    JFrame frame = new JFrame("MineSweeper");
    JPanel timerPanel = new JPanel();
    JPanel explanationPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] tiles = new JButton[rows][cols];
    Grid[][] grid = new Grid[rows][cols];
    Timer gameTimer;
    JLabel timerLabel = new JLabel("Time: 0 seconds");

    Game() {
        // ===== Timer Panel =====
        timerPanel.setBackground(Color.lightGray);
        timerPanel.setPreferredSize(new Dimension(cols * tileSize, 50));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timerLabel.setForeground(Color.white);
        timerPanel.add(timerLabel);

        // ===== Explanation Panel =====
        explanationPanel.setBackground(Color.lightGray);
        explanationPanel.setPreferredSize(new Dimension(cols * tileSize, 150));
        JLabel explanationLabel = new JLabel("Click on a tile to start the game!");
        explanationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        explanationLabel.setForeground(Color.white);
        explanationPanel.add(explanationLabel);

        // ===== Game Grid Setup =====
        boardPanel.setLayout(new GridLayout(rows, cols));

        // Initialize grid logic cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Grid();
                grid[i][j].setX(i);
                grid[i][j].setY(j);
            }
        }

        // Initialize tiles (buttons)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton tile = new JButton();
                tiles[i][j] = tile;

                tile.setFont(new Font("Arial", Font.BOLD, 16));
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFocusPainted(false);
                tile.setBorder(BorderFactory.createLineBorder(new Color(60, 120, 60), 1));
                tile.setContentAreaFilled(true);
                tile.setOpaque(true);

                // Alternate tile background color
                if ((i + j) % 2 == 0) {
                    tile.setBackground(new Color(76, 175, 80));
                } else {
                    tile.setBackground(new Color(167, 216, 161));
                }

                tile.setForeground(Color.WHITE);
                boardPanel.add(tile);

                final int row = i;
                final int col = j;

                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            if (start) {
                                start = false;
                                placeMines(row, col);
                                gameTimer.start();
                            }

                            //handleLeftClick(row, col);
                        }
                        // Optional: add right-click flag logic here
                    }
                });
            }
        }

        // ===== Timer Logic =====
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    elapsedTime++;
                    timerLabel.setText("Time: " + elapsedTime + " seconds");
                }
            }
        });

        // ===== Frame Setup =====
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(timerPanel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(explanationPanel, BorderLayout.SOUTH);

        frame.setSize(cols * tileSize, rows * tileSize + 200); // Automatically size to fit components
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setResizable(false);
        frame.setVisible(true); // FINAL step (prevents squished layout)
    }

    // ===== Place Mines After First Click =====
    private void placeMines(int startRow, int startCol) {
        int minesPlaced = 0;

        while (minesPlaced < totalMines) {
            int x = (int) (Math.random() * rows);
            int y = (int) (Math.random() * cols);

            // Skip first clicked tile and already mined ones
            if ((x == startRow && y == startCol) || grid[x][y].isMine()) {
                continue;
            }

            grid[x][y].setMine(true);
            tiles[x][y].setBackground(Color.RED); // For testing purposes, show where mines are placed
            minesPlaced++;
            
            int iMin = grid[x][y].getX()-1;
            int iMax = grid[x][y].getX()+1;
            int jMin = grid[x][y].getY()-1;
            int jMax = grid[x][y].getY()+1;
            if (iMin < 0) iMin = 0;
            if (iMax >= rows) iMax = rows;
            if (jMin < 0) jMin = 0;
            if (jMax >= cols) jMax = cols;
            for (int i = iMin; i < iMax; i++) {
                for (int j = jMin; j < jMax; j++) {
                     grid[i][j].setNearbyMines(grid[i][j].getNearbyMines() + 1);
                    
                }
            }
            
    }

    
    //Method checks if the user loses

    private void lose(){
    boolean lose = false;

    for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols; j++){
            if(grid[i][j].isMine() && grid[i][j].isRevealed()){
                lose = true;
                if(true){
                    for(int r = 0; r < rows; r++){
                        for(int c = 0; c < cols; c++){
                            if(grid[r][c].isMine()){
                              tiles[r][c].setBackground(Color.RED); // Show all mines
                            }
                        }
                    }
                
                }
                
                
            }
        }
    }

}

    //Method checks if the user wins
    
    private boolean win(){
    for(int i = 0; i < rows; i++){
        for(j = 0; j < cols; j++){
            if(!grid[i][j].isMine() && !grid[i][j].isRevealed()){
                return false;;
            }

            
        }
    }
    return true; 




}







}
