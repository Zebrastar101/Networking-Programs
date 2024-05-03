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
        for(int r = 0; r<=9; r++){
            for(int c = 0; c<=9; c++){
                grid[r][c]= new WumpusSquare();
            }
        }
        grid= new WumpusSquare[10][10];
        for(int r = 0; r<=9; r++){
            for(int c = 0; c<=9; c++){
                for(int i=1; i<=10; i++){
                    //pits
                    //int randomNum = min + (int)(Math.random() * ((max – min) + 1));
                    int randRow = 0+(int)(Math.random()*((9-0)+1));
                    int randCol = 0+(int)(Math.random()*((9-0)+1));
                    while(grid[randRow][randCol].isPit() || grid[randRow][randCol].isBreeze()){
                        randRow = 0+(int)(Math.random()*((9-0)+1));
                        randCol = 0+(int)(Math.random()*((9-0)+1));
                    }
                    grid[r][c].setPit(true);
                    grid[r+1][c].setBreeze(true);
                    grid[r-1][c].setBreeze(true);
                    grid[r][c+1].setBreeze(true);
                    grid[r][c-1].setBreeze(true);
                    
                }
            }
        }
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
