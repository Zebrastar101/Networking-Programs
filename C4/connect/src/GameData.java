public class GameData
{
    private char[][] grid = 

    public char[][] getGrid()
    {
        return grid;
    }

    public void reset()
    {

        grid = new char[3][3];
        for(int r=0;r<grid.length; r++)
            for(int c=0; c<grid[0].length; c++)
                grid[r][c]=' ';
    }


    public boolean isCat()
    {
        if(grid[0][0] !=' ' && grid[0][1] !=' ' && grid[0][2] !=' '
                && grid[1][0] !=' ' && grid[1][1] !=' ' && grid[1][2] !=' '
                && grid[2][0] !=' ' && grid[2][1] !=' ' && grid[2][2] !=' '
                && !isWinner('X') && !isWinner('O'))
            return true;
        else
            return false;
    }

    public boolean isWinner(char letter)
    {
        if((grid[0][0] ==letter && grid[0][1] ==letter && grid[0][2] ==letter)
                || (grid[1][0] ==letter && grid[1][1] ==letter && grid[1][2] ==letter)
                || (grid[2][0] ==letter && grid[2][1] ==letter && grid[2][2] ==letter)

                || (grid[0][0] ==letter && grid[1][0] ==letter && grid[2][0] ==letter)
                || (grid[0][1] ==letter && grid[1][1] ==letter && grid[2][1] ==letter)
                || (grid[0][2] ==letter && grid[1][2] ==letter && grid[2][2] ==letter)

                || (grid[0][0] ==letter && grid[1][1] ==letter && grid[2][2] ==letter)
                || (grid[0][2] ==letter && grid[1][1] ==letter && grid[2][0] ==letter))
            return true;
        else
            return false;
    }

}
