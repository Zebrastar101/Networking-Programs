/*import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try{
            // creates a socket that allows connections on port 8001
            ServerSocket serverSocket = new ServerSocket(8001);

            while(true){


                // allow Y to connect and build streams to / from Y
                Socket xCon = serverSocket.accept();
                ObjectOutputStream xos = new ObjectOutputStream(xCon.getOutputStream());
                ObjectInputStream xis = new ObjectInputStream(xCon.getInputStream());

                // Lets the client know they are the Y player
                xos.writeObject(new CommandFromServer(CommandFromServer.NEWGUY, idk));
                System.out.println("new person connected");

                // Creates a Thread to listen to the Y client
                ServersListener sl = new ServersListener(xis,xos,"idk");
                Thread t = new Thread(sl);
                t.start();
            }


        }
        catch (Exception error){
            error.printStackTrace();
        }
    }
}

 */


