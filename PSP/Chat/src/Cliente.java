import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

public class Cliente extends JFrame{

    private int puerto;
    private String host;
    private Socket socket;
    DataInputStream entrada;
    PrintStream salida;
    JTextArea escribirDatos;
    JTextArea mostrarDatos;



    public Cliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }

    public void ventana() {
        this.setBounds ( 500, 100, 300, 650 );
        this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
    }
    public void componentes(){
        JPanel p1 = new JPanel (  );
        Button b1 = new Button ( "enviar" );
        escribirDatos= new JTextArea ( );
        mostrarDatos = new JTextArea (  );
        Dimension d = new Dimension ( 250,500 );
        Dimension d2 = new Dimension(250,50);
        mostrarDatos.setEditable(false);
        escribirDatos.setPreferredSize ( d2 );
        mostrarDatos.setPreferredSize ( d );
        b1.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                escribir ( salida );
                escribirDatos.setText(null);
            }
        } );
        p1.add ( b1 );
        p1.add ( mostrarDatos );
        p1.add ( escribirDatos );

        this.getContentPane ().add ( p1 );
    }
    public void conectar() throws IOException {
        socket = new Socket ( host, puerto );


        // Declaramos un DataInputStream para leer los datos
        entrada = new DataInputStream ( socket.getInputStream () );

        // Declaramos un DataOutputStream para escribir los datos
        salida = new PrintStream ( socket.getOutputStream () );

        for (int j = 0; j >= 0; j++) {
            leer ( entrada );
        }


        //Se cierra en canal de entrada
        entrada.close ();
        //Se cierra el canal de salida
        salida.close ();
        //Se cierra el socket
        socket.close ();
    }


    public static void main(String[] args) throws IOException {
        Cliente c = new Cliente ( "localhost", 4444 );
        c.ventana ();
        c.componentes ();
        c.setVisible ( true );
        c.conectar ();

    }

    public void leer(DataInputStream entrada) throws IOException {
        String recibida;
        recibida = entrada.readLine ();
        mostrarDatos.setText (mostrarDatos.getText()+ "\n" + recibida );
    }

    public void escribir(PrintStream salida) {

        String mensaje = escribirDatos.getText ();
        mostrarDatos.setText (mostrarDatos.getText() + "\n Tu: "+ escribirDatos.getText() );
        salida.println ( mensaje );
    }


}
