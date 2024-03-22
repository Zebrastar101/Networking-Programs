import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try{
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter your ip address: ");
            String ipAddress=sc.nextLine();
            //create a connection to server
            Socket socket = new Socket(ipAddress, 8001);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            //Scanner sc= new Scanner(System.in);


            CSFrame frame;

            String checkedName="";
            boolean r=true;
            boolean us=false;
            while(r){
                System.out.println("Enter your name: ");
                String name=sc.nextLine();
                os.writeObject(new CommandFromClient(CommandFromClient.CHECKNEWUSER, name ));
                CommandFromServer cfs = (CommandFromServer)is.readObject();
                if(cfs.getCommand()==CommandFromServer.VALIDNEWUSER){
                    checkedName=cfs.getData();
                    if(checkedName!=null){
                        us=true;
                        break;
                    }
                }
            }

            frame= new CSFrame(os, checkedName);
            ClientListener cl = new ClientListener(is, os, frame);
            Thread t = new Thread(cl);
            t.start();



        }
        catch(Exception error){
            error.printStackTrace();
        }
    }
}
