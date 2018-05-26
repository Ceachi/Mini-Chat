package Client;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

/**
 * Observer Pattern = ma ajuta sa mentin decuplat fereastra noastra este un
 * listener ( a se vedea Display Thread)
 *
 * => o sa implementeze ClientMessagListener care o sa apeleze messageReceived
 * ce afiseaza mesajul primit
 *
 * @author CeachiBogdan
 */
public class MainFrame extends javax.swing.JFrame implements ClientMessageListener {

    private String nume;
    private DisplayThread displayThread;

    public MainFrame(String nume) {

        this.nume = nume;
        setTitle("NUME : " + nume);
        initComponents();

        jTextArea1.setEditable(false);
        jButton1.addActionListener(ev -> sendMessage());

        displayThread = new DisplayThread();

        // mutam fereastra ca ascultator pentur acest thread
        displayThread.addClientMessageListener(this);

        // vreau sa si primesc mesaje
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void sendMessage() {
        String mesaj = jTextArea2.getText();
        // trimit mesaj

        ConnectionController.getInstance().sendMessage(nume + " " + mesaj);
        jTextArea2.setText(null);
    }

    public void startDisplayThread() {
        displayThread.start();
    }

    // pune mesajul primit in text Area
    @Override
    public void messageReceived(String message) {
        try {
            // invokeandWait = pune operatia asta
            // intr-o coada care se executa pe firul grafic de executie
            // el ne spune ca trec mai departe doar dupa ce s-a executat din coada acest task
            //daca nu vreau sa astept dupa el,
            // apelez invokeLater (care trece mai departe si nu tine cont
            // cand se va executa pe acel fir grafic acea operatie  in fucntie de ce vreau
            
            SwingUtilities.invokeAndWait(() -> jTextArea1.append(message + "\n"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("SEND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

}
