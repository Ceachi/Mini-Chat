
package Client;

import javax.swing.JOptionPane;

/**
 *
 * @author CeachiBogdan
 */
public class Client {
    
    public static void main(String[] args) {
        String nume = JOptionPane.showInputDialog("NUME: ");
        MainFrame frame = new MainFrame(nume);
        frame.startDisplayThread();
        
        
    }
}
