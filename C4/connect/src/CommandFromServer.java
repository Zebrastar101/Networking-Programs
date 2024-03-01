
import java.io.Serializable;
public class CommandFromServer implements Serializable{
    //The command being sent to the client
    private int command;
    //Text data for command
    private String data="";

    //Command list
    public static final int CONNECT_AS_Y = 0;
    public static final int CONNECT_AS_R = 1;
    public static final int Y_TURN = 2;
    public static final int R_TURN = 3;
    public static final int MOVE = 4;
    public static final int Y_WIN = 5;
    public static final int R_WIN = 6;
    public static final int TIE = 7;
    public static final int RESTART=9;

    public static final int QUIT = 8;



    public CommandFromServer(int command, String data){
        this.command = command;
        this.data = data;
    }

    public int getCommand(){
        return command;
    }

    public String getData(){
        return data;
    }



}
