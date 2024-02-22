import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try{
            //server socket creation allowing clients to connect
            ServerSocket serverSocket = new ServerSocket(8000);
            while(true){
                // creates connection to the client
                Socket socket = serverSocket.accept();
                //creates a stream for writing objects to the client
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                //creates a stream for reading objects from the client
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
    }
}
