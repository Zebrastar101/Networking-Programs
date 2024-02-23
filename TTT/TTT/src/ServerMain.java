import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try{
            //creates socket that allows connection on port 8001
            ServerSocket serverSocket=new ServerSocket(8001);
            //allow X to connect and build streams to&from X
            Socket xConnection = serverSocket.accept();
            ObjectOutputStream xos = new ObjectOutputStream(xConnection.getOutputStream());
            ObjectInputStream xis = new ObjectInputStream(xConnection.getInputStream());
            //CommandFromServer will be an error until me and zara merge
            //send a command
            xos.writeObject(new CommandFromServer(CommandFromServer.CONNECTED_AS_X,null));
            System.out.println("X has connected");

            ServersListener sl = new ServersListener();

            //allow O to connect and build streams to&from O
            Socket oConnection = serverSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(oConnection.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(oConnection.getInputStream());
            //CommandFromServer will be an error until me and zara merge
            //send a command
            oos.writeObject(new CommandFromServer(CommandFromServer.CONNECTED_AS_O,null));
            System.out.println("O has connected");
        }
        catch (Exception error){
            error.printStackTrace();
        }
    }
}
