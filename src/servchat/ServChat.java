
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
import javax.swing.JOptionPane;

public class ServChat extends Thread{

        public static String text;
        public static String cadComprob="cerrar";
        static int puerto=5555;
        public static int numComp;
    
    public static void main(String[] args) throws IOException {
        
       
            ServerSocket serverSocket=new ServerSocket();
            
            
            InetSocketAddress addr=new InetSocketAddress("localhost",puerto);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            
                        
            Socket newSocket= serverSocket.accept();
                                    
            System.out.println("Conexion recibida");
            
            

            BufferedReader is = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
            
            
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(newSocket.getOutputStream()));
           
            text=is.readLine();
            System.out.println("Mensaje que envia el cliente :"+ text);
            
            text = JOptionPane.showInputDialog("(S)Escribe un mensaje");
            os.write(text);
            
            if (text.equals(cadComprob)){
                numComp=5;
            }
            
            if (numComp==5){
                System.out.println("LOS TEXTOS COINCIDEN");
            newSocket.close();     
            serverSocket.close();
            }
       
    }
}