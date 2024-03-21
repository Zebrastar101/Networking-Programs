
import java.io.Serializable;
public class CommandFromServer implements Serializable{
    //The command being sent to the client
    private int command;
    //Text data for command
    private String data="";

    //Command list
    public static final int CHECKNEWUSER = 0;
    public static final int VALIDNEWUSER = 1;
    public static final int USER=5;
    public static final int EXIT = 2;

    public static final int SENDMESSAGE = 3;

    public static final int USERLIST = 4;




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



