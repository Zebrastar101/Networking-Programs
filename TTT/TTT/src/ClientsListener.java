import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientsListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private TTTFrame frame = null;

    public ClientsListener(ObjectInputStream is, ObjectOutputStream os, TTTFrame frame) {
        this.is = is;
        this.os = os;
        this.frame = frame;
    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromServer cfs = (CommandFromServer)is.readObject();

                // processes the received command
                if(cfs.getCommand() == CommandFromServer.X_TURN)
                    frame.setTurn('X');
                else if(cfs.getCommand() == CommandFromServer.O_TURN)
                    frame.setTurn('O');
                else if(cfs.getCommand() == cfs.MOVE)
                {
                    String data = cfs.getData();
                    // pulls data for the move from the data field
                    int c = data.charAt(0) - '0';
                    int r = data.charAt(1) - '0';

                    // changes the board and redraw the screen
                    frame.makeMove(c,r,data.charAt(2));
                }
                // handles the various end game states
                else if(cfs.getCommand() == CommandFromServer.TIE)
                {
                    frame.setText("Tie game.");
                }
                else if(cfs.getCommand() == CommandFromServer.X_WIN)
                {
                    frame.setText("X wins!");
                }
                else if(cfs.getCommand() == CommandFromServer.O_WIN)
                {
                    frame.setText("O wins!");
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();

        }
    }
    public void changeTurn()
    {
        // changes the turn
        if(turn=='X')
            turn = 'O';
        else
            turn ='X';

        // informs both client of the new player turn
        if (turn == 'X')
            sendCommand(new CommandFromServer(CommandFromServer.X_TURN, null));
        else
            sendCommand(new CommandFromServer(CommandFromServer.O_TURN, null));
    }

    public void checkGameOver()
    {
        int command = -1;
        if(gameData.isCat())
            command = CommandFromServer.TIE;
        else if(gameData.isWinner('X'))
            command = CommandFromServer.X_WINS;
        else if(gameData.isWinner('O'))
            command = CommandFromServer.O_WINS;

        // if the game ended, informs both clients of the game's end state
        if(command!=-1)
            sendCommand(new CommandFromServer(command, null));
    }
    public void sendCommand(CommandFromServer cfs)
    {
        // Sends command to both players
        for (ObjectOutputStream o : outs) {
            try {
                o.writeObject(cfs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
