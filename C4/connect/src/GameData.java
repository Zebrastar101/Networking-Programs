public class GameData
{
    private char[][] grid ={{' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' '}
            ,{' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' '}
            };

    public char[][] getGrid()
    {
        return grid;
    }

    public void reset()
    {

        grid = new char[6][7];
        for(int r=0;r<grid.length; r++)
            for(int c=0; c<grid[0].length; c++)
                grid[r][c]=' ';
    }


    public boolean isCat()
    {
        if(grid[0][0] !=' ' && grid[0][1] !=' ' && grid[0][2] !=' ' &&
                grid[0][3] !=' ' && grid[0][4] !=' ' && grid[0][5] !=' ' && grid[0][6] !=' '&&
                grid[1][0] !=' ' && grid[1][1] !=' ' && grid[1][2] !=' '&& grid[1][3] !=' '&&
                grid[1][4] !=' '&& grid[1][5] !=' '&& grid[1][6] !=' '&&
                grid[2][0] !=' '&&grid[2][1] !=' '&& grid[2][3] !=' '&& grid[2][2] !=' '&&
                grid[2][4] !=' '&& grid[2][5] !=' '&& grid[2][6] !=' '&&
                grid[3][0] !=' '&& grid[3][1] !=' '&& grid[3][2] !=' '&&
                grid[3][3] !=' '&& grid[3][4] !=' '&& grid[3][5] !=' '&& grid[3][6] !=' ' &&
                grid[4][0] !=' '&& grid[4][1] !=' '&& grid[4][2] !=' '&& grid[4][3] !=' '&&
                grid[4][4] !=' '&& grid[4][5] !=' '&& grid[4][6] !=' '&&
                grid[5][0] !=' '&& grid[5][1] !=' '&& grid[5][2] !=' '&& grid[5][3] !=' '&&
                grid[5][4] !=' '&& grid[5][5] !=' '&& grid[5][6] !=' '&&isWinner('X')==false&&isWinner('O')==false)
            return true;
        else
            return false;
    }

    public boolean isWinner(char letter)
    {
        //Check horizontal win row one
        // Check horizontal win
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 4; c++) {
                if (grid[r][c] == letter &&
                        grid[r][c + 1] == letter &&
                        grid[r][c + 2] == letter &&
                        grid[r][c + 3] == letter) {
                    return true;
                }
            }
        }
        //Check vertical win
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(grid[i][j] == letter && grid[i+1][j] == letter && grid[i+2][j] == letter && grid[i+3][j] == letter)
                    return true;
            }
        }
        //Check diagonal win
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(grid[i][j] == letter && grid[i+1][j+1] == letter && grid[i+2][j+2] == letter && grid[i+3][j+3] == letter)
                    return true;
            }
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 3; j < 7; j++)
            {
                if(grid[i][j] == letter && grid[i+1][j-1] == letter && grid[i+2][j-2] == letter && grid[i+3][j-3] == letter)
                    return true;
            }
        }
        return false;
    }

}
