import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        try{
            //create a connection to server
            Socket socket = new Socket("127.0.0.1", 8001);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            //determine if playing as X or O
            CommandFromServer cfs = (CommandFromServer)is.readObject();
            if (cfs.getCommand() == CommandFromServer.CONNECTED_AS_X){
                System.out.println("Connected as X");
            }
            else{
                System.out.println("Connected as O");
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
    }

}
