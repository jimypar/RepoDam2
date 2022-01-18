import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGUI extends JFrame implements ActionListener,WindowListener {

    private JLabel lanno,lresult;
    private JTextField txtMensaje;
    private JLabel txtResult;
    private Cliente cliente;

    //Constructor de la clase clienteGUI que inicia el JFrame
    private ClienteGUI() {

        super("Cliente");

        JPanel panel = new JPanel(new GridLayout(2,2));

        lanno = new JLabel();
        lanno.setText("AÑO: ");
        panel.add(lanno);

        txtMensaje = new JTextField();
        txtMensaje.addActionListener(this);
        txtMensaje.requestFocus();
        panel.add(txtMensaje);

        lresult = new JLabel();
        lresult.setText("Incremento temperatura : ");
        panel.add(lresult);



        txtResult = new JLabel();
        panel.add(txtResult);

        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,75);
        setVisible(true);

        //Iniciar el cliente
        cliente = new Cliente("localhost", 4444, this);
        cliente.iniciar();

    }

    //Muestra el mensaje
    void append(String str) {
        txtResult.setText(leerJSON(str));
    }

    private String leerJSON(String str) {

        Gson gson = new Gson();
        Temperatura t = gson.fromJson(str, Temperatura.class);

        return t.getIncremento();

    }

    void falloConexion() {
        cliente.enviarMensaje("$$$SALIR$$$");
    }

    //Envia mensaje cuando le das a enter
    public void actionPerformed(ActionEvent e) {

        cliente.enviarMensaje(txtMensaje.getText());
        txtMensaje.setText("");

    }


    public void windowClosing(WindowEvent e) { cliente.enviarMensaje("$$$SALIR$$$");
    }

    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}


    public static void main(String[] args) {
        new ClienteGUI();
    }


}
