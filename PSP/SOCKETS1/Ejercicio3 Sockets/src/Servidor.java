

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor  {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoServidor mimarco=new MarcoServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class MarcoServidor extends JFrame implements Runnable{

    public MarcoServidor(){

        setBounds(1200,300,280,350);

        JPanel milamina= new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto=new JTextArea();

        milamina.add(areatexto,BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

        Thread hilo = new Thread(this);
        hilo.start();

    }

    private	JTextArea areatexto;

    @Override
    public void run() {

        try {
            ServerSocket server = new ServerSocket(4444);
            String nick,ip,mensaje;
            PaqueteEnvio paquete_recibido;

            while (true) {

                Socket socket = server.accept();
                ObjectInputStream paquete_datos = new ObjectInputStream(socket.getInputStream());
                paquete_recibido= (PaqueteEnvio) paquete_datos.readObject();
                nick=paquete_recibido.getNick();
                ip=paquete_recibido.getIp();
                mensaje=paquete_recibido.getMensaje();

                areatexto.append("\n"+nick+": "+ mensaje+ " para: "+ip);

                Socket enviaDestinatario= new Socket(ip,9090);

                ObjectOutputStream paqueteReenvio= new ObjectOutputStream(enviaDestinatario.getOutputStream());

                paqueteReenvio.writeObject(paquete_recibido);

                paqueteReenvio.close();

                enviaDestinatario.close();

                socket.close();

                //DataInputStream flujo_entrada = new DataInputStream(socket.getInputStream());
                //String mensaje = flujo_entrada.readUTF();
                //areatexto.append("\n Mensaje: " + mensaje);
                //socket.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
