
import java.io.Serializable;
public class CommandFromServer implements Serializable{
    //The command being sent to the client
    private int command;
    //Text data for command
    private String data="";

    //Command list
    public static final int NEWUSER = 0;
    public static final int EXIT = 1;

    public static final int SENDMESSAGE = 1;



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



