
package servchat;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class ServChat extends Thread{

        String text;
        String cadComprob="cerrar";
        static int puerto=5555;
        
        
    public void run(){
        
        
        
        try{
            

            ServerSocket serverSocket=new ServerSocket();
            
            
            

            InetSocketAddress addr=new InetSocketAddress("localhost",puerto);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            
                        
            Socket newSocket= serverSocket.accept();
            
            new ServChat().start();
            
            System.out.println("Conexion recibida");

            BufferedReader is = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
            
            
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(newSocket.getOutputStream()));
           
            text=is.readLine();
            System.out.println("Mensaje que llega :"+ text);
            
            
            if (text.equals(cadComprob)){
                 newSocket.close();     
                serverSocket.close();
            }
            
             

           
            }catch (IOException e) {
            }
    }
        
    
    public static void main(String[] args) {
        
       new ServChat().start();
        
       
    }
}