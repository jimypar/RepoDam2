import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUIusuario extends JFrame implements ActionListener, WindowListener {

    private JPanel panel;
    private JLabel usuario;
    private JButton consulta,modificar,eliminar,entrenador, jugador, estadio;
    private JTextField busqueda, res1,res2,res3,res4;
    private JLabel resultado1,resultado2,resultado3,resultado4;
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

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 1;

        entrenador = new JButton("Buscar entrenador");
        entrenador.addActionListener(this);
        panel.add(entrenador,config);

        config.gridx = 1;
        config.gridy = 3;

        jugador = new JButton("Buscar jugador");
        jugador.addActionListener(this);
        panel.add(jugador,config);

        config.gridx = 2;
        config.gridy = 3;

        estadio = new JButton("Buscar estadio");
        estadio.addActionListener(this);
        panel.add(estadio,config);

        config.gridx = 0;
        config.gridy = 4;

        resultado1 = new JLabel();
        panel.add(resultado1,config);

        config.gridx = 1;

        res1 = new JTextField();
        panel.add(res1,config);

        config.gridx = 0;
        config.gridy = 5;

        resultado2 = new JLabel();
        panel.add(resultado2,config);

        config.gridx = 1;

        res2 = new JTextField();
        panel.add(res2,config);

        config.gridx = 0;
        config.gridy = 6;

        resultado3 = new JLabel();
        panel.add(resultado3,config);

        config.gridx = 1;

        res3 = new JTextField();
        panel.add(res3,config);

        config.gridx = 0;
        config.gridy = 7;

        resultado4 = new JLabel();
        panel.add(resultado4,config);

        config.gridx = 1;

        res4 = new JTextField();
        panel.add(res4,config);

        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300,200,500 , 500);
        setVisible(true);

    }

    //Clase que recibe el ActionListener y segun el boton que sea pulsado
    //se realiza una consulta u otra.
    @Override
    public void actionPerformed(ActionEvent e) {

        //Crea el GSON
        Gson gson = new Gson();
        String json;
        Mensaje m;


        //Segun el boton pulsado crea un mensaje en formato JSON
        switch (e.getActionCommand()){
            case "Buscar entrenador":
                m = new Mensaje(1,1,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Buscar jugador":
                m = new Mensaje(1,2,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Buscar estadio":
                m = new Mensaje(1,3,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
        }

    }

    //Metodo que recibe los resultados de las consultas.
    public void recibir(String str) {

        //Se crea el GSON
        Gson gson = new Gson();

        //Se crea el mensaje del json
        Mensaje m = gson.fromJson(str, Mensaje.class);

        //Segun el tipo de mensaje se muestran los datos
        if (m.getTipoObjeto()==2){

            Jugador j = gson.fromJson(m.getConsulta(), Jugador.class);

            vaciarCampos();
            resultado1.setVisible(true);
            resultado2.setVisible(true);
            resultado3.setVisible(true);
            resultado4.setVisible(true);
            res1.setVisible(true);
            res2.setVisible(true);
            res3.setVisible(true);
            res4.setVisible(true);
            resultado1.setText("Nombre:");
            resultado2.setText("Nacionalidad:");
            resultado3.setText("Equipo:");
            resultado4.setText("Posicion:");
            res1.setText(j.getNombre());
            res2.setText(j.getNacionalidad());
            res3.setText(j.getEquipo());
            res4.setText(j.getPosicion());

        }else if (m.getTipoObjeto()==1){

            Entrenador e = gson.fromJson(m.getConsulta(), Entrenador.class);

            vaciarCampos();
            resultado1.setVisible(true);
            resultado2.setVisible(true);
            resultado3.setVisible(true);
            res1.setVisible(true);
            res2.setVisible(true);
            res3.setVisible(true);
            resultado1.setText("Nombre:");
            resultado2.setText("Nacionalidad:");
            resultado3.setText("Equipo:");
            res1.setText(e.getNombre());
            res2.setText(e.getNacionalidad());
            res3.setText(e.getEquipo());

        }else if (m.getTipoObjeto()==3){

            Estadio e = gson.fromJson(m.getConsulta(), Estadio.class);

            vaciarCampos();
            resultado1.setVisible(true);
            resultado2.setVisible(true);
            res1.setVisible(true);
            res2.setVisible(true);
            resultado1.setText("Nombre:");
            resultado2.setText("Ciudad:");
            res1.setText(e.getNombre());
            res2.setText(e.getCiudad());

        }else {
            vaciarCampos();
            resultado1.setVisible(true);
            resultado1.setText("No se ha encontrado");
        }



    }

    private void vaciarCampos() {

        resultado1.setText("");
        resultado2.setText("");
        resultado3.setText("");
        resultado4.setText("");
        res1.setText("");
        res2.setText("");
        res3.setText("");
        res4.setText("");

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