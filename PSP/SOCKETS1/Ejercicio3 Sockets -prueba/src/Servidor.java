

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JTextField campo1,nick,ip;
    private JTextArea campochat;
    private JButton miboton;

    public MarcoServidor(){

        nick = new JTextField(5);

        add(nick);

        JLabel texto=new JLabel("CHAT");

        add(texto);

        ip = new JTextField(8);

        add(ip);

        campochat = new JTextArea(12,20);

        add(campochat);

        campo1=new JTextField(20);

        add(campo1);

        miboton=new JButton("Enviar");

        EnviaTexto evento = new EnviaTexto();

        miboton.addActionListener(evento);

        add(miboton);

        Thread hilo = new Thread(this);

        hilo.start();

    }

    @Override
    public void run() {

        try {
            ServerSocket servidor_cliente = new ServerSocket(9090);
            Socket cliente;
            PaqueteEnvio paqueteRecibido;

            while (true){
                cliente=servidor_cliente.accept();
                ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido= (PaqueteEnvio) flujoentrada.readObject();
                campochat.append("\n"+paqueteRecibido.getNick()+": "+paqueteRecibido.getMensaje());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private class EnviaTexto implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                Socket socket = new Socket("localhost",4444);
                PaqueteEnvio datos = new PaqueteEnvio();

                datos.setNick(nick.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(campo1.getText());

                ObjectOutputStream paquete_datos = new ObjectOutputStream(socket.getOutputStream());
                paquete_datos.writeObject(datos);
                socket.close();

                //DataOutputStream flujo_salida = new DataOutputStream(socket.getOutputStream());
                //flujo_salida.writeUTF(campo1.getText());
                //flujo_salida.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}


