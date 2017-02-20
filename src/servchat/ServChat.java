
package servchat;




import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

public class ServChat extends Thread{

        static int puerto=5555;
        
    public static void main(String[] args) throws IOException {
        /*
        Creamos el socket servidor, le asignamos el puerto y la direccion (localhost), realizamos el bind y 
        aceptamos la conexion entrante y le llamamos newSocket
        */
        System.out.println("Creando socket servidor");
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress addr = new InetSocketAddress("localhost", puerto);
        serverSocket.bind(addr);
        Socket newSocket = serverSocket.accept();
        System.out.println("Conexion recibida");

        /*
        Cambiamos los flujos de lectura y escritura a PrintStream(escritura) y DataInputStream(lectura)
        De esta forma el cliente permanecerá abierto con el bucle que explicaremos más adelante y no se cerrará al escribir el mensaje
        */
        PrintStream os = new PrintStream(new BufferedOutputStream(newSocket.getOutputStream()));
        DataInputStream is = new DataInputStream(new BufferedInputStream(newSocket.getInputStream()));
        /*
        Creamos una variable String llamada entradaCli que recogerá el mensaje que nos envió el cliente
        Si el mensaje es distinto de null, es decir, si hay algo escrito, entrará en el bucle while y nos imprimira por pantalla dicho mensaje
        Dentro del propio bucle while hay otra bucle, esta vez if en el que, si el mensaje del cliente es igual a "cerrar" realiza un break
        Con este break saldra del bucle while y por lo tanto, se cerrará el programa
        En caso de que no sea "cerrar" nos pedirá escribir un mensaje que se enviará al cliente
        */
        String entradaCli;
        while((entradaCli=is.readLine())!=null){
            System.out.println("Mensaje del cliente: "+entradaCli);
                if(entradaCli.equals("cerrar")){
                    break;
                }
            String mensajeServ = JOptionPane.showInputDialog("(SE)Introduzca el mensaje ");
            os.println(mensajeServ);
            System.out.println("Mensaje del servidor: "+mensajeServ);
            os.flush();
         }
       
        os.close();
        is.close();
        
        newSocket.close();
        serverSocket.close();

        System.out.println("Terminado");

    }

}