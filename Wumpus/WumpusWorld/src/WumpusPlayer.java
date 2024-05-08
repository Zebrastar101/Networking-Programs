public class WumpusPlayer {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    private int direction;
    private int colPos;
    private int rowPos;
    private boolean arrow;
    private boolean gold;


    public  WumpusPlayer() {
        direction=NORTH;
        gold=false;
        arrow=true;
    }

    public int getDirection() {
        return direction;
    }

    public int getColPos() {
        return colPos;
    }

    public int getRowPos() {
        return rowPos;
    }

    public boolean isArrow() {
        return arrow;
    }

    public boolean isGold() {
        return gold;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setArrow(boolean arrow) {
        this.arrow = arrow;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

}


