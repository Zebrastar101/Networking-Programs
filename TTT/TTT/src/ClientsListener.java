import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientsListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private TTTFrame frame = null;

    public ClientsListener(ObjectInputStream is, ObjectOutputStream os, TTTFrame frame) {
        this.is = is;
        this.os = os;
        this.frame = frame;
    }

}
