
import java.io.Serializable;
public class CommandFromClient implements Serializable{
    //The comand being sent to the server
    private int command;
    //Text data for the command
    private String data ="";
    //Command List
    public static final int MOVE = 0;
    public static final int RESTART = 1;

    public static final int QUIT = 2;

    public CommandFromClient(int command, String data){
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
