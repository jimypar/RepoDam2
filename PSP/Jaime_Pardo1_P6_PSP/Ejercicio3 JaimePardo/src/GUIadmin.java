import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUIadmin extends JFrame implements ActionListener, WindowListener {

    private JPanel panel;
    private JLabel usuario;
    private JButton consulta,modificar,eliminar, entrenador, jugador, estadio;
    private JTextField busqueda, res1,res2,res3,res4;
    private JLabel resultado1,resultado2,resultado3,resultado4;
    private Cliente cliente;

    //Constructor de la clase GUI de un administrador.
    GUIadmin(Cliente cliente) {
        super("Cliente");

        this.cliente = cliente;

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.insets = new Insets(3,3,3,3);

        config.gridx = 1;
        config.gridy = 0;
        config.fill = GridBagConstraints.BOTH;

        usuario = new JLabel("Estas registrado como Admin");
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
        panel.add(modificar,config);

        config.gridx = 2;
        config.gridy = 1;

        eliminar = new JButton("Eliminar");
        eliminar.addActionListener(this);
        panel.add(eliminar,config);

        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 3;

        busqueda = new JTextField();
        panel.add(busqueda,config);

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

        vaciarCampos();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300,200,500 , 500);
        setVisible(true);

    }


    //Clase que recibe el ActionListener y segun el boton que sea pulsado
    //se realiza una consulta u otra.
    @Override
    public void actionPerformed(ActionEvent e) {

        //Se instancia el objeto GSON
        Gson gson = new Gson();
        String json;
        Mensaje m;

        switch (e.getActionCommand()){
            case "Consultar":
                entrenador.setText("Buscar entrenador");
                jugador.setText("Buscar jugador");
                estadio.setText("Buscar estadio");
                break;
            case "Modificar":
                entrenador.setText("Modificar entrenador");
                jugador.setText("Modificar jugador");
                estadio.setText("Modificar estadio");
                break;
            case "Eliminar":
                entrenador.setText("Eliminar entrenador");
                jugador.setText("Eliminar jugador");
                estadio.setText("Eliminar estadio");
                break;
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
            case "Modificar entrenador":
                Entrenador en = new Entrenador(res1.getText(),res2.getText(),res3.getText());
                json = gson.toJson(en);
                m = new Mensaje(2,1,json,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Modificar jugador":
                Jugador j = new Jugador(res1.getText(),res2.getText(),res3.getText(),res4.getText());
                json = gson.toJson(j);
                m = new Mensaje(2,2,json,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Modificar estadio":
                Estadio es = new Estadio(res1.getText(),res2.getText());
                json = gson.toJson(es);
                m = new Mensaje(2,3,json,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Eliminar entrenador":
                m = new Mensaje(3,1,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Eliminar jugador":
                m = new Mensaje(3,2,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
            case "Eliminar estadio":
                m = new Mensaje(3,3,busqueda.getText());
                cliente.enviarMensaje(gson.toJson(m));
                System.out.println("Consulta: "+busqueda.getText());
                break;
        }

    }

    //Metodo que recibe los resultados de las consultas.
    public void recibir(String str) {

        Gson gson = new Gson();

        String[] partes = str.split("_");

        System.out.println("longitud "+ partes.length);

        if (partes[0].equals("2")){

            Jugador j = gson.fromJson(partes[1], Jugador.class);

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

        }else if (partes[0].equals("1")){

            Entrenador e = gson.fromJson(partes[1], Entrenador.class);

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

        }else if (partes[0].equals("3")){

            Estadio e = gson.fromJson(partes[1], Estadio.class);

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

    //Metodo que vacia los campos.
    private void vaciarCampos() {
        resultado1.setText("");
        resultado2.setText("");
        resultado3.setText("");
        resultado4.setText("");
        res1.setText("");
        res2.setText("");
        res3.setText("");
        res4.setText("");
        resultado1.setVisible(false);
        resultado2.setVisible(false);
        resultado3.setVisible(false);
        resultado4.setVisible(false);
        res1.setVisible(false);
        res2.setVisible(false);
        res3.setVisible(false);
        res4.setVisible(false);

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