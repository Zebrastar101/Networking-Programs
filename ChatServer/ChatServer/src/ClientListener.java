import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class ClientListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    public static ArrayList<String> clients;
    private Socket socket;
    private Scanner scanner;
    public ClientListener(ObjectInputStream is, ObjectOutputStream os, CSFrame frame) {
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
                String s;
                //System.out.println(cfs.getCommand());
                //quit
                if(cfs.getCommand()==CommandFromServer.USERLIST){
                    s=cfs.getData();
                    s=s.substring(1,s.length()-1);
                    clients=new ArrayList<>(List.of(s.split(", ")));
                }
                else if(cfs.getCommand()==CommandFromServer.SENDMESSAGE){
                    s=cfs.getData();
                    frame.sendCalledByClientListener(frame.getName(),s);
                }
                else if(cfs.getCommand()==CommandFromServer.EXIT){
                    s=cfs.getData();
                    frame.exitCalledByClientListener(s);
                    frame.UpdateUserList(clients);
                }
                else if (cfs.getCommand()==CommandFromServer.VALIDNEWUSER){
                    s=cfs.getData();
                    frame.NewUser(s);
                    frame.UpdateUserList(clients);
                }

            }

        }
        catch(Exception error){
            error.printStackTrace();

        }
    }

}



