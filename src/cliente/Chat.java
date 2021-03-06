package cliente;

import java.net.*;
import static javax.swing.JOptionPane.*;
import java.io.PrintStream;
import java.awt.event.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Chat extends javax.swing.JFrame {

    private String nome;
    private String IP;
    private int PORT;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    
    
    public Chat(String nome,String IP,int PORT) {
        
        initComponents();
        
        this.nome = nome;
        this.IP = IP;
        this.PORT = PORT;
        
        try{
            s = new Socket(IP,PORT);
        }
        catch(IOException e){
            
            showMessageDialog(null,"Não se conectou ao servidor","",ERROR_MESSAGE);
            System.exit(0);
        }
        
        Thread();
        
    }

    private void Thread(){
        
        Thread t = new Thread(new Runnable(){
        
            String mensagem;
            
            @Override
            public void run(){
                
                try{
                    
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);
                    
                    while((mensagem = br.readLine()) != null){
                        
                       TextView.setText(TextView.getText() + mensagem + "\n");
                        
                    }
                    
                }
                catch(IOException e){
                    
                    showMessageDialog(null, "Erro na conexão com o servidor", "", ERROR_MESSAGE);
                    
                }
                
                
                
            }
        
        });
        
        t.start();
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextView = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        TextInput = new javax.swing.JTextField();
        Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaChat");
        setLocation(new java.awt.Point(500, 50));
        setPreferredSize(new java.awt.Dimension(400, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(240, 240, 245));

        TextView.setEditable(false);
        TextView.setColumns(20);
        TextView.setRows(5);
        TextView.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(TextView);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-small.png"))); // NOI18N

        TextInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextInputKeyPressed(evt);
            }
        });

        Button.setText("Enviar");
        Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TextInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Button, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(TextInput, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonActionPerformed
        
        String menssagem = nome+"disse: ";
           
        try{
            PrintStream ps = new PrintStream(s.getOutputStream());
            menssagem = TextInput.getText();
            
            ps.println(menssagem);
            ps.flush();
            
            TextInput.setText("");
        }
        catch(IOException ex){
            showMessageDialog(null,"Não conseguiu enviar a menssagem","",ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_ButtonActionPerformed

    private void TextInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextInputKeyPressed
     
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
        String menssagem;
          
        try{
            PrintStream ps = new PrintStream(s.getOutputStream());
            menssagem = nome + ": " + TextInput.getText();
            
            ps.println(menssagem);
            ps.flush();
            
            TextInput.setText("");
        }
        catch(IOException ex){
            showMessageDialog(null,"Não conseguiu enviar a menssagem","",ERROR_MESSAGE);
        }
        
        }
        
    }//GEN-LAST:event_TextInputKeyPressed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button;
    private javax.swing.JTextField TextInput;
    private javax.swing.JTextArea TextView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
