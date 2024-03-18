import java.io.ObjectInputStream;
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

                /*
                xos.writeObject(new CommandFromServer(CommandFromServer.VALIDNEWUSER, idk));
                System.out.println("new person connected");

                 */

                // Creates a Thread to listen to the Y client
                ServerListener sl = new ServerListener(xis,xos,"idk");
                Thread t = new Thread(sl);
                t.start();
            }


        }
        catch (Exception error){
            error.printStackTrace();
        }
    }
}



