import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends JFrame{

    private int puerto;
    private String host;
    private Socket socket;

    public Cliente(String host, int puerto) {
        this.puerto = puerto;
		this.host = host;
    }


    public void conectar() throws IOException {
        socket = new Socket(host, puerto);

        DataInputStream entrada = new DataInputStream(socket.getInputStream());



        Scanner scan = new Scanner(System.in);

    }

    private void recibir(DataInputStream entrada) throws IOException {
        String recibida;
        recibida = entrada.readLine();

    }

    private void enviar(PrintStream salida,String mensaje) {
        salida.println(mensaje);
    }




    public void main(String[] args) throws IOException {
        Cliente c = new Cliente("localhost",4444);
        PrintStream salida = new PrintStream(socket.getOutputStream());
        c.conectar();
        setVentana();
        iniciarComponentes(salida);

    }

    private void iniciarComponentes(PrintStream salida) {
        JPanel panel = new JPanel();

        JTextArea txtChat = new JTextArea();
        panel.add(txtChat);

        JTextArea txtMensaje = new JTextArea();
        panel.add(txtMensaje);

        JButton botonEnviar = new JButton();
        botonEnviar.setText("Enviar");
        panel.add(botonEnviar);

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviar(salida,txtMensaje.getText());
            }
        });

    }

    private void setVentana() {

        this.setTitle("Cliente");
        this.setBounds(320, 500, 320, 500);
        this.setDefaultCloseOperation(3);
        
    }
    
    


}
