public class WumpusMap {
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_PITS = 10;
    private WumpusSquare[][] grid;
    private int ladderColumn;
    private int ladderRow;

    public WumpusMap(){
        createMap();
    }

    public void createMap(){

    }

    public int getLadderColumn() {
        return ladderColumn;
    }

    public int getLadderRow() {
        return ladderRow;
    }

    public WumpusSquare getSquare(int col, int row){

        return null;
    }

    public String toString(){

        return null;
    }

}
