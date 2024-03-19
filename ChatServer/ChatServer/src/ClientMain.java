import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try{

            //create a connection to server
            Socket socket = new Socket("127.0.0.1", 8001);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            Scanner sc= new Scanner(System.in);

            CommandFromServer cfs = (CommandFromServer)is.readObject();
            CSFrame frame;


           boolean r=true;
            while(r){
                System.out.println("What is your name: ");
                String name=sc.nextLine();
                os.writeObject(os.writeObject(new CommandFromClient(CommandFromClient.CHECKNEWUSER, name ));
                if(cfs.getCommand()==CommandFromServer.CHECKNEWUSER){
                    r=cfs.getData();
                    if(r==true){
                        frame= new CSFrame(os, name);
                        break;

                    }
                }
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
