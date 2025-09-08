import java.awt.*;
import java.util.*;

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
    private void setTheories(){
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 24; col++) {
                
            }
        }
    }
    private void setTheory(int row, int col){
        if (grid[row][col].isRevealed() && grid[row][col].getNearbyMines() != 0) {
            boolean nextToRevealed = false;
            boolean nextToUnrevealed = false;
            ArrayList<Grid> possiblePair = new ArrayList<Grid>();
            for (Grid directions : grid[row][col].getCardinalDirections()) {
                if (directions != null) {
                    if (directions.isRevealed() == false){
                        nextToUnrevealed = true;
                        break;
                    }
                }
            }
            if (nextToUnrevealed == false){
                return;
            }
            for (Grid directions : grid[row][col].getCardinalDirections()) {
                if (directions != null) {
                    if (directions.isRevealed() && directions.getNearbyMines() != 0){
                        nextToRevealed = true;
                        possiblePair.add(directions);
                    }
                }
            }
            // possiblePair is now in scope here
            for (int i = 0; i < possiblePair.size(); i++){
                for (int j = 0; j < 8; j++) {
                    if (grid[row][col].getCardinalDirections() != null) {
                        if (grid[row][col].getCardinalDirections().get(j).isRevealed() == false){
                            possiblePair.remove(i);
                            i--;
                        }
                    }
                }
            }
        
    }
}
}
