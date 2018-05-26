package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Cand se incearca initializarea, creez ServerSocket
 *
 * @author CeachiBogdan
 */
public class ServerEngine {

    public static final int PORT = 444;
    ServerSocket serverSocket;

    private ServerEngine() {
        try {
            serverSocket = new ServerSocket(PORT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final class SingletonHolder {

        private static final ServerEngine INSTANCE = new ServerEngine();
    }

    public static ServerEngine getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ServerThread connectClient() throws IOException, Exception {
        // un client se conecteaza
        Socket socket = serverSocket.accept();

        //creem un thread pt el si il pornim
        ServerThread st = new ServerThread(socket);
        st.start();
        
        
        return st;
    }
}
