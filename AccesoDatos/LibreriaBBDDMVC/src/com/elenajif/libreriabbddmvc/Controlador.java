package com.elenajif.libreriabbddmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PROFESOR on 16/11/2018.
 */
public class Controlador implements ActionListener{

    private Modelo modelo;
    private Vista vista;

    public Controlador(Vista vista, Modelo modelo){

        this.vista = vista;
        this.modelo = modelo;
        anadirActionListeners(this);

        modelo.conectar();

    }


    private void anadirActionListeners(ActionListener listener){
        vista.eliminarBtn.addActionListener(listener);
        vista.insertarBtn.addActionListener(listener);
        vista.listarBtn.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch(comando){

            case "Insertar":{
                System.out.println("entro en insertar");
                try {
                    modelo.insertar(vista.txtIsbn.getText(), vista.txtTitulo.getText(), vista.txtAutor.getText(),vista.dateTimePicker.getDateTimePermissive());
                    cargarFilas(modelo.consultarLibros());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;

            case "Listar":{
                try {
                    modelo.consultarLibros();
                    cargarFilas(modelo.consultarLibros());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
            case "Eliminar":{
                try {
                    modelo.eliminar(vista.txtIsbn.getText());
                    cargarFilas(modelo.consultarLibros());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
        }
    }

    private void cargarFilas(ResultSet consultarLibros) throws SQLException {

        Object[] fila = new Object[4];
        vista.dtm.setRowCount(0);

        while (consultarLibros.next()){
            fila[0] = consultarLibros.getObject(1);
            fila[1] = consultarLibros.getObject(2);
            fila[2] = consultarLibros.getObject(3);
            fila[3] = consultarLibros.getObject(4);

            vista.dtm.addRow(fila);
        }

    }
}
