import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class IniciarSesionGUI extends JFrame implements ActionListener,WindowListener {

    private JLabel usuario,password;
    private JButton entrar,registrar;
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
        panel.add(txtUser);

        password = new JLabel();
        password.setText("Contraseña: ");
        panel.add(password);

        txtPassword = new JTextField();
        panel.add(txtPassword);

        entrar = new JButton("Entrar");
        entrar.addActionListener(this);
        panel.add(entrar);

        registrar = new JButton("Registrar");
        registrar.addActionListener(this);
        panel.add(registrar);

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
            Gson gson = new Gson();
            MensajeInicioSesion m =  gson.fromJson(str,MensajeInicioSesion.class);
            switch (m.getTipoconsulta()){
                case -1:
                    JOptionPane.showMessageDialog(null,"Usuario incorrecto","Usuario incorrecto",JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null,"Sesion iniciada","Usuario correcto",JOptionPane.INFORMATION_MESSAGE);
                    conectado = true;
                    this.dispose();
                    guiu = new GUIusuario(cliente);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null,"Sesion iniciada","Usuario correcto",JOptionPane.INFORMATION_MESSAGE);
                    conectado=true;
                    this.dispose();
                    guia = new GUIadmin(cliente);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"Usuario Creado","Usuario creado",JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }

    }

    //Cuando se hace click al enter se envia la info al server.
    public void actionPerformed(ActionEvent e) {

        Gson gson = new Gson();
        MensajeInicioSesion m;

        switch (e.getActionCommand()){
            case "Entrar":
                m = new MensajeInicioSesion(1,txtUser.getText(),txtPassword.getText());
                cliente.enviarMensaje(gson.toJson(m));
                break;
            case "Registrar":
                m = new MensajeInicioSesion(2,txtUser.getText(),txtPassword.getText());
                cliente.enviarMensaje(gson.toJson(m));
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


    //Clase principal que crea la ventana.
    public static void main(String[] args) {
       new IniciarSesionGUI();
    }

}
