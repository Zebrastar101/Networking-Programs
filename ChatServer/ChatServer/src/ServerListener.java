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

                if(cfc.getCommand()==CommandFromClient.CHECKNEWUSER){
                    String user=cfc.getData();
                    if(!existingUsers.contains(user)){
                        existingUsers.add(user);
                        os.writeObject(new CommandFromServer(CommandFromServer.VALIDNEWUSER,user));
                    }
                }

                //quit
                if(cfc.getCommand()==CommandFromClient.EXIT){
                    String user=cfc.getData();
                    existingUsers.remove(user);
                    os.writeObject(new CommandFromServer(CommandFromServer.EXIT,user));

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







