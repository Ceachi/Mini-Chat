package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author CeachiBogdan
 */
public class ClientsContainer {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static List<ServerThread> clients = new ArrayList<>();

    public static synchronized void addClient(ServerThread client) {
        lock.writeLock().lock();
        try {
            clients.add(client);
        } finally {
            // pe finally ca sa ma asigur ca nu ramane vreodata blocak lockul
            lock.writeLock().unlock();
        }
    }

    public static synchronized void sendMessageToAll(String mesaj) {
        // trimitem mesajul catre toti clientii
// am inbunatatit performanta scotand sincronizarea
        try {
            lock.readLock().lock();
            clients.forEach((c) -> c.sendMessage(mesaj));
        } finally {
            lock.readLock().unlock();
        }
    }
}
