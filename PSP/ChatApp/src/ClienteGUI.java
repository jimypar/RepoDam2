import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGUI extends JFrame implements ActionListener, WindowListener {

    private JLabel label;
    private JTextField txtUsuario,txtMensaje;
    private JButton entrar, salir, conectados;
    private JTextArea txtChat;
    private boolean conectado;
    private Cliente cliente;

    ClienteGUI() {

        super("Cliente");

        JPanel northPanel = new JPanel(new GridLayout());

        label = new JLabel("Introduce el nombre de usuario", SwingConstants.CENTER);
        northPanel.add(label);
        txtUsuario = new JTextField("Usuario");
        txtUsuario.setBackground(Color.WHITE);
        northPanel.add(txtUsuario);
        add(northPanel, BorderLayout.NORTH);

        txtChat = new JTextArea("CHAT\n", 80, 80);
        JPanel centerPanel = new JPanel(new GridLayout());
        centerPanel.add(new JScrollPane(txtChat));
        txtChat.setEditable(false);
        add(centerPanel, BorderLayout.CENTER);


        txtMensaje = new JTextField();
        txtMensaje.setEditable(false);
        entrar = new JButton("Entrar");
        entrar.addActionListener(this);
        salir = new JButton("Desconectar");
        salir.addActionListener(this);
        salir.setEnabled(false);
        conectados = new JButton("Ver conectados");
        conectados.addActionListener(this);
        conectados.setEnabled(false);


        JPanel panelMensaje = new JPanel(new GridLayout());;
        JPanel panelBotones = new JPanel(new GridLayout(1,3));

        JPanel panelSur = new JPanel(new GridLayout(2,1));

        panelMensaje.add(txtMensaje);
        panelBotones.add(entrar);
        panelBotones.add(salir);
        panelBotones.add(conectados);

        panelSur.add(panelMensaje);
        panelSur.add(panelBotones);


        add(panelSur, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        txtUsuario.requestFocus();

    }

    void append(String str) {
        txtChat.append(str);
        txtChat.setCaretPosition(txtChat.getText().length() - 1);
    }

    void falloConexion() {
        entrar.setEnabled(true);
        salir.setEnabled(false);
        conectados.setEnabled(false);
        label.setText("Introduce el nombre de usuario");
        txtUsuario.setText("Usuario");
        txtUsuario.removeActionListener(this);
        conectado = false;
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if(o == salir) {
            cliente.enviarMensaje(new Mensaje(Mensaje.SALIR, ""));
            txtUsuario.setEditable(true);
            txtMensaje.setEditable(false);
            return;
        }
        if(o == conectados) {
            cliente.enviarMensaje(new Mensaje(Mensaje.CONECTADOS, ""));
            return;
        }

        if(conectado) {
            cliente.enviarMensaje(new Mensaje(Mensaje.MENSAJE, txtMensaje.getText()));
            txtMensaje.setText("");
            return;
        }


        if(o == entrar) {
            String username = txtUsuario.getText().trim();
            if(username.length() == 0)
                return;

            cliente = new Cliente("localhost", 4444, username, this);
            if(!cliente.iniciar())
                return;
            label.setText("Introduce un mensaje");
            conectado = true;

            entrar.setEnabled(false);
            salir.setEnabled(true);
            conectados.setEnabled(true);
            txtUsuario.setEditable(false);
            txtMensaje.setEditable(true);
            txtMensaje.addActionListener(this);
            txtMensaje.requestFocus();
        }

    }

    public void windowClosing(WindowEvent e) {
        cliente.enviarMensaje(new Mensaje(Mensaje.SALIR, ""));
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
