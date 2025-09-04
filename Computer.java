import java.awt.*;
public class Computer extends Game {

    public void findBombs(){
        for (int row = 0; row < 20; row++){
            for(int col = 0; col < 24; col++){
                if (grid[row][col].isRevealed()) {
                    findBomb(row, col);
                }
            }
        }
    }
    private void findBomb(int x, int y){
        int iMin = Math.max(0, x - 1);
        int iMax = Math.min(rows - 1, x + 1);
        int jMin = Math.max(0, y - 1);
        int jMax = Math.min(cols - 1, y + 1);
        int unclearedTiles = 0;
            for (int i = iMin; i <= iMax; i++) {
                for (int j = jMin; j <= jMax; j++) {
                    if (!grid[i][j].isRevealed()){
                        unclearedTiles++;
                    }
                }
            }
            if (grid[x][y].getNearbyMines() == unclearedTiles){
                for (int i = iMin; i <= iMax; i++) {
                    for (int j = jMin; j <= jMax; j++) {
                        if (!grid[i][j].isRevealed() && !grid[i][j].isFlagged()){
                            grid[i][j].setFlagged();
                            grid[i][j].setBackground(Color.BLUE);
                            totalFlags--;
                        }
                    }
                }
                
            }
    }
    public void clearTiles(){
        for (int row = 0; row < 20; row++){
            for(int col = 0; col < 24; col++){
                if (grid[row][col].isRevealed()) {
                    clearTile(row, col);
                }
            }
        }
    }
    private void clearTile(int x, int y){
        int iMin = Math.max(0, x - 1);
        int iMax = Math.min(rows - 1, x + 1);
        int jMin = Math.max(0, y - 1);
        int jMax = Math.min(cols - 1, y + 1);
        int minesFlagged = 0;
        int unclearedTiles = 0;
        for (int i = iMin; i <= iMax; i++) {
            for(int j = jMin; j <= jMax; j++) {
                if (!grid[i][j].isRevealed()){
                    unclearedTiles++;
                }
                if (grid[i][j].isFlagged()) {
                    minesFlagged++;
                }
            }
        }
        if (minesFlagged == grid[x][y].getNearbyMines() && unclearedTiles > grid[x][y].getNearbyMines()) {
            for (int i = iMin; i <= iMax; i++) {
            for(int j = jMin; j <= jMax; j++) {
                if (!grid[i][j].isRevealed() && !grid[i][j].isFlagged()){
                    removeTile(i, j);
                }
            }
        }
        }
    }
    public void calcLocalBombs(){
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 24; col++) {
                if(grid[row][col].isRevealed()){
                calcLocalBomb(row, col);
                }
            }
        }
    }
    private void calcLocalBomb(int x, int y){
        int iMin = Math.max(0, x - 1);
        int iMax = Math.min(rows - 1, x + 1);
        int jMin = Math.max(0, y - 1);
        int jMax = Math.min(cols - 1, y + 1);
        int localMines = grid[x][y].getNearbyMines();
        for (int i = iMin; i <= iMax; i++) {
            for(int j = jMin; j <= jMax; j++) {
                if (grid[i][j].isFlagged()) {
                    localMines--;
                }
            }
        }
        grid[x][y].setLocalNearbyMines(localMines);
    }
    
}
