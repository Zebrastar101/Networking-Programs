
import java.io.Serializable;
public class CommandFromServer implements Serializable{
      //The command being sent to the client
    private int command;
    //Text data for command
    private String data="";

    //Command list
    public static final int CONNECT_AS_X = 0;
    public static final int CONNECT_AS_O = 1;

}
