import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ServidorGUI extends JFrame implements ActionListener, WindowListener {

    private JButton iniciar;
    private JTextArea chat;
    private Servidor servidor;

    ServidorGUI() {
        super("SERVIDOR");
        servidor = null;
        JPanel north = new JPanel();
        iniciar = new JButton("Iniciar");
        iniciar.addActionListener(this);
        north.add(iniciar);
        add(north, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout());
        chat = new JTextArea(80,80);
        chat.setEditable(false);
        mostrar("CHAT DEL SERVIDOR\n");
        center.add(new JScrollPane(chat));
        add(center);

        addWindowListener(this);
        setSize(400, 600);
        setVisible(true);
    }

    void mostrar(String str) {
        chat.append(str);
        chat.setCaretPosition(chat.getText().length() - 1);
    }

    public void actionPerformed(ActionEvent e) {
        if(servidor != null) {
            servidor.cerrarServer();
            servidor = null;
            iniciar.setText("Iniciar");
            return;
        }

        servidor = new Servidor(4444, this);
        new HiloServer().start();
        iniciar.setText("Cerrar");
    }

    public static void main(String[] arg) {
        new ServidorGUI();
    }

    public void windowClosing(WindowEvent e) {
        if(servidor != null) {
            try {
                servidor.cerrarServer();
            }
            catch(Exception eClose) {
            }
            servidor = null;
        }
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}


    class HiloServer extends Thread {
        public void run() {
            servidor.start();
            iniciar.setText("Iniciar");
            mostrar("Servidor cerrado\n");
            servidor = null;
        }
    }

}
