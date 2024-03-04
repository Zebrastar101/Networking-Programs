import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientsListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    public static ArrayList<ClientsListener> clientListeners = new ArrayList<ClientsListener>();
    private Socket socket;
    private Scanner scanner;
    public ClientsListener(ObjectInputStream is, ObjectOutputStream os, ChatServer frame) {
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
                //System.out.println(cfs.getCommand());
                //quit


            }

        }
        catch(Exception error){
            error.printStackTrace();

        }
    }

}



