package Server;
/*
Modificam cu principii SOLID
sincronizarea = este pt performanta
*/
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CeachiBogdan
 */
public class Server {


    // pastram Clientii


    public static void main(String[] args) {
        try {

            while (true) {
                
              ServerThread st = ServerEngine.getInstance().connectClient();
               ClientsContainer.addClient(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
