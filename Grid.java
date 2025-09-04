import java.awt.Color;
import javax.swing.JButton;

public class Grid extends JButton{

    //Attributes
    private int nearbyMines = 0;
    private boolean isRevealed = false;
    private boolean isFlagged = false;
    private boolean isMine = false;
    private Color originalColor;

    //Attributes for Computer
    private int localMines;

    //Getters
    public int getNearbyMines() {
        return nearbyMines;
    }
    public boolean isRevealed() {
        return isRevealed;
    }
    public boolean isFlagged() {
        return isFlagged;
    }
    public boolean isMine() {
        return isMine;
    }
    public int getLocalNearbyMines(){
        return localMines;
    }

    //Setters
    public void setNearbyMines(int nearbyMines) {
        this.nearbyMines = nearbyMines;
    }
    public void setRevealed() {
        this.isRevealed = true;
    }
    public void setFlagged() {
        this.isFlagged = !this.isFlagged;
    }
    public void setMine() {
        this.isMine = true;
    }
    public void setColor(Color color){
        this.originalColor = color;
        this.setBackground(this.originalColor);
    }
    public void setOriginalColor(){
        this.setBackground(this.originalColor);
    }
    public void setLocalNearbyMines(int localMines){
        this.localMines = localMines;
    }

    public void reset() {
        this.nearbyMines = 0;
        this.isRevealed = false;
        this.isFlagged = false;
        this.isMine = false;
    }
}
