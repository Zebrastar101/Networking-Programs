import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    /*Stores the which player this listener is for
    private String user;
     */

    // static data that is shared between both listeners

    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();

    private static ArrayList<String> existingUsers = new ArrayList<>();


    public ServerListener(ObjectInputStream is, ObjectOutputStream os) {
        this.is = is;
        this.os = os;
        outs.add(os);
    }


    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromClient cfc = (CommandFromClient) is.readObject();

                //new User
                //still confused about how to operate the UserList, there must be a better way to do it than how I'm doing it
                if(cfc.getCommand()==CommandFromClient.CHECKNEWUSER){
                    String user=cfc.getData();
                    if(!existingUsers.contains(user)){
                        existingUsers.add(user);
                        sendCommand(new CommandFromServer(CommandFromServer.USERLIST,existingUsers.toString()));
                        sendCommand(new CommandFromServer(CommandFromServer.VALIDNEWUSER,user));
                    }

                }

                //quit
                if(cfc.getCommand()==CommandFromClient.EXIT){
                    String user=cfc.getData();
                    existingUsers.remove(user);
                    sendCommand(new CommandFromServer(CommandFromServer.USERLIST,existingUsers.toString()));
                    sendCommand(new CommandFromServer(CommandFromServer.EXIT,user));
                }

                if(cfc.getCommand()==CommandFromClient.SENDMESSAGE){
                    String data=cfc.getData();
                    sendCommand(new CommandFromServer(CommandFromServer.EXIT,data));
                }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }




    public void sendCommand(CommandFromServer cfs)
    {
        // Sends command to both players
        for (ObjectOutputStream o : outs) {
            try {
                o.writeObject(cfs);
                o.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}







