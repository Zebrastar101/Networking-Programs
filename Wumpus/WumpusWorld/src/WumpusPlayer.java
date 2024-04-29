public class WumpusPlayer {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WAFFLES = 3;
    private int direction;
    private int colPos;
    private int rowPos;
    private boolean arrow;
    private boolean gold;
    public int WumpusPlayer(){
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
    }
}


