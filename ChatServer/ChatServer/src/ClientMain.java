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
                String checkedName;
                os.writeObject(new CommandFromClient(CommandFromClient.CHECKNEWUSER, name ));
                if(cfs.getCommand()==CommandFromServer.VALIDNEWUSER){
                    checkedName=cfs.getData();
                    if(checkedName!=null){
                        frame= new CSFrame(os, name);
                        ClientListener cl = new ClientListener(is, os, frame);
                        Thread t = new Thread(cl);
                        t.start();
                        break;

                    }
                }
            }



        }
        catch(Exception error){
            error.printStackTrace();
        }
    }
}
