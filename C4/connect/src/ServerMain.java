 import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try{
            // creates a socket that allows connections on port 8001
            ServerSocket serverSocket = new ServerSocket(8001);

            // allow Y to connect and build streams to / from Y
            Socket xCon = serverSocket.accept();
            ObjectOutputStream xos = new ObjectOutputStream(xCon.getOutputStream());
            ObjectInputStream xis = new ObjectInputStream(xCon.getInputStream());

            // Lets the client know they are the Y player
            xos.writeObject(new CommandFromServer(CommandFromServer.CONNECT_AS_Y,null));
            System.out.println("Y has Connected.");

            // Creates a Thread to listen to the Y client
            ServersListener sl = new ServersListener(xis,xos,'Y');
            Thread t = new Thread(sl);
            t.start();

            // allow R to connect and build streams to / from R
            Socket oCon = serverSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(oCon.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(oCon.getInputStream());

            // Lets the client know they are the R player
            oos.writeObject(new CommandFromServer(CommandFromServer.CONNECT_AS_R,null));
            System.out.println("R has Connected.");

            // Creates a Thread to listen to the R client
            sl = new ServersListener(ois,oos,'R');
            t = new Thread(sl);
            t.start();


            xos.writeObject(new CommandFromServer(CommandFromServer.Y_TURN,null));
            oos.writeObject(new CommandFromServer(CommandFromServer.Y_TURN,null));
        }
        catch (Exception error){
            error.printStackTrace();
        }
    }
}

