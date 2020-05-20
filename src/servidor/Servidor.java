package servidor;

import java.net.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.PrintStream;

public class Servidor {
    
    public static void main(String[] args){
        
        ArrayList<PrintStream> clientes = new ArrayList<>();
        
        try{
            ServerSocket server = new ServerSocket(5000);
            Socket socket;
            
            while(true){
                socket = server.accept();
                
                //Guarda o endereço do cliente
                clientes.add(new PrintStream(socket.getOutputStream()));
                
                Mensagem mensagem = new Mensagem(socket,clientes);
                
            }
            
        }
        catch(IOException ex){
            System.err.println("[ERROR]: "+ex.getMessage());
        }
        
    }
    
    
}
