import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        try{
            //creates an object for the TTT game
            GameData gameData=new GameData();
            //create a connection to server
            Socket socket = new Socket("127.0.0.1", 8001);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            //determine if playing as X or O
            CommandFromServer cfs = (CommandFromServer)is.readObject();
            C4Frame frame;

            if (cfs.getCommand() == CommandFromServer.CONNECT_AS_Y){
                frame = new C4Frame(gameData, os,'Y');
            }
            else{
                frame = new C4Frame(gameData,os, 'R');
            }
            ClientsListener cl = new ClientsListener(is, os, frame);
            Thread t = new Thread(cl);
            t.start();
        }
        catch(Exception error){
            error.printStackTrace();
        }
    }
}
