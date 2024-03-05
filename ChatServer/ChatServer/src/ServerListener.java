/*import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServersListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    // Stores the which player this listener is for
    private char chatter;

    // static data that is shared between both listeners

    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();





    public ServersListener(ObjectInputStream is, ObjectOutputStream os, char chatter) {
        this.is = is;
        this.os = os;
        this.chatter = chatter;
        outs.add(os);
    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromClient cfc = (CommandFromClient) is.readObject();

                //quit

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

 */



