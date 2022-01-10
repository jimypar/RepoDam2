
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGUI extends JFrame implements ActionListener,WindowListener {

    private Cliente cliente;
    JButton b00,b01,b02,b10,b11,b12,b20,b21,b22;

    //Constructor del GUI del cliente con los parametros de la posicion de la pestaña
    ClienteGUI(int x, int y, String nombre) {

        super(nombre);

        JPanel panel = new JPanel(new GridLayout(3,3));

        b00 = new JButton();
        b00.setActionCommand("00");
        b01 = new JButton();
        b01.setActionCommand("01");
        b02 = new JButton();
        b02.setActionCommand("02");
        b10 = new JButton();
        b10.setActionCommand("10");
        b11 = new JButton();
        b11.setActionCommand("11");
        b12 = new JButton();
        b12.setActionCommand("12");
        b20 = new JButton();
        b20.setActionCommand("20");
        b21 = new JButton();
        b21.setActionCommand("21");
        b22 = new JButton();
        b22.setActionCommand("22");
        panel.add(b00);
        panel.add(b01);
        panel.add(b02);
        panel.add(b10);
        panel.add(b11);
        panel.add(b12);
        panel.add(b20);
        panel.add(b21);
        panel.add(b22);

        b00.addActionListener(this);
        b01.addActionListener(this);
        b02.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);
        b12.addActionListener(this);
        b20.addActionListener(this);
        b21.addActionListener(this);
        b22.addActionListener(this);

        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(x,y,300,300);
        setVisible(true);

        //Se crea el cliente con el host,el puerto y el GUI y se inicia.
        cliente = new Cliente("localhost", 4444, this);
        cliente.iniciar();

    }

    //Metodo que comprueba si no esta vacio el mensaje recibido.
    void append(String str) {
        if (!str.equals("")) {
            rellenarTabla(str);
        }
    }

    //Metodo del actionListener que recibe el boton presionado y se lo envia al servidor
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando){
            case "00":
                cliente.enviarMensaje("00");
                break;
            case "01":
                cliente.enviarMensaje("01");
                break;
            case "02":
                cliente.enviarMensaje("02");
                break;
            case "10":
                cliente.enviarMensaje("10");
                break;
            case "11":
                cliente.enviarMensaje("11");
                break;
            case "12":
                cliente.enviarMensaje("12");
                break;
            case "20":
                cliente.enviarMensaje("20");
                break;
            case "21":
                cliente.enviarMensaje("21");
                break;
            case "22":
                cliente.enviarMensaje("22");
                break;
        }

    }


    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

    //Metodo que rellena la tabla segun el mensaje recibido
    private void rellenarTabla(String str) {

        String[] separador = str.split(":");

        System.out.println(separador[0]);
        System.out.println(separador[1]);

        String ficha = "";
        if (separador[0].equals("1")) {
            ficha = "X";
        }else {
            ficha = "O";
        }

        switch (separador[1]) {
            case "00":
                b00.setText(ficha);
                break;
            case "01":
                b01.setText(ficha);
                break;
            case "02":
                b02.setText(ficha);
                break;
            case "10":
                b10.setText(ficha);
                break;
            case "11":
                b11.setText(ficha);
                break;
            case "12":
                b12.setText(ficha);
                break;
            case "20":
                b20.setText(ficha);
                break;
            case "21":
                b21.setText(ficha);
                break;
            case "22":
                b22.setText(ficha);
                break;
            case "victoria":
                JOptionPane.showMessageDialog(null,"EL JUGADOR "+separador[0]+" HA GANADO");
                System.exit(0);
            case "empate":
                JOptionPane.showMessageDialog(null,"EMPATE");
                System.exit(0);
        }
    }

}
