/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author CeachiBogdan
 */
public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public ServerThread(Socket socket) throws Exception {
      this.socket = socket;
      this.out = new PrintWriter(socket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
    }
    
    @Override
    public void run() {
        // pentur client
       try { 
        while(true) {
            // se asteapta primirea unui mesaj
            String message = receiveMessage();
            
            //trimit catre toti
            ClientsContainer.sendMessageToAll(message);
            
        }
       }catch(Exception e) {
           e.printStackTrace();
       }
        
        
    }
    
    public void sendMessage(String message) {
        out.println(message);
    }
    
    public String receiveMessage() throws IOException {
        return in.readLine();
        
    }
    
    
}
