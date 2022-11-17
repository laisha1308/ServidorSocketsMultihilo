import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorMultiParlante extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSesion;

    public ServidorMultiParlante(Socket socket, int id) {
        this.socket = socket;
        this.idSesion = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            Logger.getLogger(ServidorMultiParlante.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void desconectar() {
        try {
            socket.close();
        } catch (IOException e) {
            Logger.getLogger(ServidorMultiParlante.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        String accion = "";
        try {
            accion = dis.readUTF();
            if(accion.equals("hola")) {
                System.out.println("El cliente con id " + this.idSesion + " saluda al servidor");
                dos.writeUTF("adios");
            }
        } catch (IOException e) {
            Logger.getLogger(ServidorMultiParlante.class.getName()).log(Level.SEVERE, null, e);
        }
        desconectar();
    }
}
