
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author CeachiBogdan
 */
public class ConnectionController {
    public static final int PORT = 444;
    
    private Socket socket;
    
    // fluxuri
    private BufferedReader in;
    private PrintWriter out;
    
    private ConnectionController() {
        
        try {
            socket = new Socket("localhost", PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new  PrintWriter(socket.getOutputStream(),true);
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static final class SingletonHolder {
        private static final ConnectionController INSTANCE = new ConnectionController();
    }
    
    
    public static ConnectionController getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    
    public void sendMessage(String mesaj) {
           out.println(mesaj);
    }
    
    public String receiveMessage() {
        try {
           return in.readLine();
        }catch(IOException e) {
            // ar trebui o clasa particularizata de RuntimeException
            throw new RuntimeException();
        }
    }
}
