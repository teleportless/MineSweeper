public class Grid {
private int nearbyMines = 0;
private boolean isRevealed = false;
private boolean isFlagged = false;
private boolean isMine = false;

public int getNearbyMines() {
    return nearbyMines;
}
public void setNearbyMines(int nearbyMines) {
    this.nearbyMines = nearbyMines;
}
public boolean isRevealed() {
    return isRevealed;
}
public void setRevealed(boolean isRevealed) {
    this.isRevealed = isRevealed;
}
public boolean isFlagged() {
    return isFlagged;
}
public void setFlagged() {
    this.isFlagged = !this.isFlagged;
}
public boolean isMine() {
    return isMine;
}
public void setMine(boolean isMine) {
    this.isMine = isMine;
}
public void reset() {
    this.nearbyMines = 0;
    this.isRevealed = false;
    this.isFlagged = false;
    this.isMine = false;
}
}
