package Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Am facut un Observer Pattern firul de executie devine subiect,
 *
 * subiectul are o lista de ascultatori
 *
 * @author CeachiBogdan
 */


/*
- asteapta sa primeasca mesaje
- cand primeste mesaj, notifica ca prin mesaj
- dar nu stie unde sa il duca, doar ne spune HEy, vedeti ca am primit mesajul!
- oricine asculta este un listener
*/
public class DisplayThread extends Thread {

    // avem o lista de ascultatori    
    List<ClientMessageListener> listeners;

    public DisplayThread() {
        listeners = new ArrayList<>();
    }

    public void run() {
        while (true) {
            String message = ConnectionController.getInstance().receiveMessage();
            notifyAllListeners(message);
        }
    }
    
    
    
    public void addClientMessageListener(ClientMessageListener client) {
        listeners.add(client);
    }
    
    private void notifyAllListeners(String message) {
        // parcurgem toti ascultatorii care exista
        // si vreau sa le apelez comportamentul notificat
        
        listeners.forEach(c -> c.messageReceived(message));
    }
}
