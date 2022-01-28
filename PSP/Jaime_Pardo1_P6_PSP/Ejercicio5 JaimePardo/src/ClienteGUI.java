
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGUI extends JFrame implements ActionListener,WindowListener {

    private Cliente cliente;
    JButton b00,b01,b02,b10,b11,b12,b20,b21,b22;
    JButton[][] botones = new JButton[3][3];

    //Constructor del GUI del cliente con los parametros de la posicion de la pestaña
    ClienteGUI(int x,int y, String nombre) {

        super(nombre);

        JPanel panel = new JPanel(new GridLayout(3,3));


        b00 = new JButton();
        b00.setActionCommand("00");
        botones[0][0] = b00;
        b01 = new JButton();
        b01.setActionCommand("01");
        botones[0][1] = b01;
        b02 = new JButton();
        b02.setActionCommand("02");
        botones[0][2] = b02;
        b10 = new JButton();
        b10.setActionCommand("10");
        botones[1][0] = b10;
        b11 = new JButton();
        b11.setActionCommand("11");
        botones[1][1] = b11;
        b12 = new JButton();
        b12.setActionCommand("12");
        botones[1][2] = b12;
        b20 = new JButton();
        b20.setActionCommand("20");
        botones[2][0] = b20;
        b21 = new JButton();
        b21.setActionCommand("21");
        botones[2][1] = b21;
        b22 = new JButton();
        b22.setActionCommand("22");
        botones[2][2] = b22;
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

        Gson gson = new Gson();


        String comando = e.getActionCommand();

        switch (comando){
            case "00":
                cliente.enviarMensaje(gson.toJson(new Mensaje(0,0)));
                break;
            case "01":
                cliente.enviarMensaje(gson.toJson(new Mensaje(0,1)));
                break;
            case "02":
                cliente.enviarMensaje(gson.toJson(new Mensaje(0,2)));
                break;
            case "10":
                cliente.enviarMensaje(gson.toJson(new Mensaje(1,0)));
                break;
            case "11":
                cliente.enviarMensaje(gson.toJson(new Mensaje(1,1)));
                break;
            case "12":
                cliente.enviarMensaje(gson.toJson(new Mensaje(1,2)));
                break;
            case "20":
                cliente.enviarMensaje(gson.toJson(new Mensaje(2,0)));
                break;
            case "21":
                cliente.enviarMensaje(gson.toJson(new Mensaje(2,1)));
                break;
            case "22":
                cliente.enviarMensaje(gson.toJson(new Mensaje(2,2)));
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

        Gson gson = new Gson();

        Mensaje m = gson.fromJson(str, Mensaje.class);

        String ficha = "";
        if (m.getId()==1) {
            ficha = "X";
        }else {
            ficha = "O";
        }

        botones[m.getPosX()][m.getPosY()].setText(ficha);

        if (m.isVictoria()){
                JOptionPane.showMessageDialog(null,"EL JUGADOR "+m.getId()+" HA GANADO");
                System.exit(0);
        }

        if (m.isEmpate()){
                JOptionPane.showMessageDialog(null,"EMPATE");
                System.exit(0);
        }
    }

}
