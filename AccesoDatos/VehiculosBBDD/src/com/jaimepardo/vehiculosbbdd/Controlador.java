package com.jaimepardo.recambiosbbddmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador implements ActionListener{

    private Modelo modelo;
    private Vista vista;

    public Controlador(Vista vista, Modelo modelo) throws SQLException {

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
                    modelo.insertar(vista.txtMatricula.getText(), vista.txtMarca.getText(), vista.txtPotencia.getText(),vista.dateTimePicker.getDateTimePermissive());
                    cargarFilas(modelo.consultarCoches());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;

            case "Listar":{
                try {
                    modelo.consultarCoches();
                    cargarFilas(modelo.consultarCoches());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
            case "Eliminar":{
                try {
                    modelo.eliminar(vista.txtMatricula.getText());
                    cargarFilas(modelo.consultarCoches());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
        }
    }

    private void cargarFilas(ResultSet consultarCoches) throws SQLException {

        Object[] fila = new Object[5];
        vista.dtm.setRowCount(0);

        while (consultarCoches.next()){
            fila[0] = consultarCoches.getObject(1);
            fila[1] = consultarCoches.getObject(2);
            fila[2] = consultarCoches.getObject(3);
            fila[3] = consultarCoches.getObject(4);
            fila[4] = consultarCoches.getObject(5);

            vista.dtm.addRow(fila);
        }

    }
}
