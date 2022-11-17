import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    static final int PUERTO = 5000;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        System.out.println("Inicializando servidor");
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("\t [OK]");
            int idSesion = 0;
            while (true) {
                Socket socket;
                socket = serverSocket.accept();
                System.out.println("Nueva conexión entrante: " + socket);
                (new ServidorMultiParlante(socket, idSesion)).start();
                idSesion++;
            }
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}