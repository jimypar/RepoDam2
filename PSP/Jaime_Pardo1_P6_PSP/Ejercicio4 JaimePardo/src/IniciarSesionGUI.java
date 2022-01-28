import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class IniciarSesionGUI extends JFrame implements ActionListener,WindowListener {

    private JLabel usuario,password;
    private JTextField txtUser,txtPassword;
    private JPanel panel;
    private Cliente cliente;
    private GUIusuario guiu = null;
    private GUIadmin guia= null;
    private boolean conectado;

    //Constructor de la pestaña de iniciar sesion
    private IniciarSesionGUI() {

        super("Cliente");

        panel = new JPanel(new GridLayout(2,2));

        usuario = new JLabel();
        usuario.setText("Usuario : ");
        panel.add(usuario);

        txtUser = new JTextField();
        txtUser.addActionListener(this);
        panel.add(txtUser);

        password = new JLabel();
        password.setText("Contraseña: ");
        panel.add(password);

        txtPassword = new JTextField();
        txtPassword.addActionListener(this);
        panel.add(txtPassword);

        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300,200,300,100);
        setVisible(true);

        //Inicia el cliente con el host, la ip y esta GUI.
        cliente = new Cliente("localhost", 4444, this);
        cliente.iniciar();

    }

    //Metodo que muestra en el GUI el resultado del servidor
    //Si se ha conectado se le redirige a otro gui.
    void recibir(String str) {

        if (conectado){

            try {
                guiu.recibir(str);
            }catch (Exception e){}
            try {
                guia.recibir(str);
            }catch (Exception e){}

        }else {
            if (str.equals("-1")){
                JOptionPane.showMessageDialog(null,"Usuario incorrecto","Usuario incorrecto",JOptionPane.ERROR_MESSAGE);
            }else if (str.equals("0")){
                JOptionPane.showMessageDialog(null,"Sesion iniciada","Usuario correcto",JOptionPane.INFORMATION_MESSAGE);
                conectado = true;
                this.dispose();
                guiu = new GUIusuario(cliente);
            }else if ((str.equals("1"))){
                JOptionPane.showMessageDialog(null,"Sesion iniciada","Usuario correcto",JOptionPane.INFORMATION_MESSAGE);
                conectado=true;
                this.dispose();
                guia = new GUIadmin(cliente);
            }
        }

    }

    //Cuando se hace click al enter se envia la info al server.
    public void actionPerformed(ActionEvent e) {

        cliente.enviarMensaje(txtUser.getText()+":"+txtPassword.getText());

    }

    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}


    //Clase principal que crea la ventana.
    public static void main(String[] args) {
       new IniciarSesionGUI();
    }

}
