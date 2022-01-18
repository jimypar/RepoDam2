import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUIusuario extends JFrame implements ActionListener, WindowListener {

    private JPanel panel;
    private JLabel usuario;
    private JButton consulta,modificar,eliminar,buscarEntrenador, buscarJugador, buscarEstadio;
    private JTextField busqueda;
    private JLabel resultado1,resultado2,resultado3;
    private Cliente cliente;

    //Constructor de la clase GUI de un cliente.
    GUIusuario(Cliente cliente) {
        super("Cliente");

        this.cliente = cliente;

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 1;
        config.gridy = 0;
        config.fill = GridBagConstraints.BOTH;

        usuario = new JLabel("Estas registrado como Usuario");
        panel.add(usuario,config);

        config.gridx = 0;
        config.gridy = 1;

        consulta = new JButton("Consultar");
        consulta.addActionListener(this);
        panel.add(consulta,config);

        config.gridx = 1;
        config.gridy = 1;

        modificar = new JButton("Modificar");
        modificar.addActionListener(this);
        modificar.setEnabled(false);
        panel.add(modificar,config);

        config.gridx = 2;
        config.gridy = 1;

        eliminar = new JButton("Eliminar");
        eliminar.addActionListener(this);
        eliminar.setEnabled(false);
        panel.add(eliminar,config);

        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 3;

        busqueda = new JTextField();
        panel.add(busqueda,config);

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 1;

        buscarEntrenador = new JButton("Buscar entrenador");
        buscarEntrenador.addActionListener(this);
        panel.add(buscarEntrenador,config);

        config.gridx = 1;
        config.gridy = 3;

        buscarJugador = new JButton("Buscar jugador");
        buscarJugador.addActionListener(this);
        panel.add(buscarJugador,config);

        config.gridx = 2;
        config.gridy = 3;

        buscarEstadio = new JButton("Buscar estadio");
        buscarEstadio.addActionListener(this);
        panel.add(buscarEstadio,config);

        config.gridx = 0;
        config.gridy = 4;
        config.gridwidth = 3;
        config.fill = GridBagConstraints.BOTH;

        resultado1 = new JLabel();
        panel.add(resultado1,config);

        config.gridx = 0;
        config.gridy = 5;

        resultado2 = new JLabel();
        panel.add(resultado2,config);

        config.gridx = 0;
        config.gridy = 6;

        resultado3 = new JLabel();
        panel.add(resultado3,config);

        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300,200,500 , 500);
        setVisible(true);

    }

    //Clase que recibe el ActionListener y segun el boton que sea pulsado
    //se realiza una consulta u otra.
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "Buscar entrenador":
                cliente.enviarMensaje("1:1:"+busqueda.getText());
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Buscar jugador":
                cliente.enviarMensaje("1:2:"+busqueda.getText());
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Buscar estadio":
                cliente.enviarMensaje("1:3:"+busqueda.getText());
                System.out.println("Consulta: "+busqueda.getText());
                break;
        }

    }

    //Metodo que recibe los resultados de las consultas.
    public void recibir(String str) {

        String[] partes = str.split(":");

        resultado1.setText("");
        resultado2.setText("");
        resultado3.setText("");
        resultado1.setText(partes[0]+":"+partes[1]);
        resultado2.setText(partes[2]+":"+partes[3]);
        resultado3.setText(partes[4]+":"+partes[5]);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


}