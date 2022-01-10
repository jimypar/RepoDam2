package com.jaimepardo.recambiosbbddmvc;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Modelo {
    private Connection conexion;

    public void conectar() throws SQLException {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/recambiosJaimePardo", "root", "");
        } catch (SQLException e) {
            int ventanaYesNo = JOptionPane.showConfirmDialog(null, "¿Deseas crear la base?", "Base no encontrada",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (ventanaYesNo == 0) {
                crearBase();
                conectar();
                crearTabla();
                JOptionPane.showMessageDialog(null, "BASE CREADA");
            }else {
                System.exit(0);
            }
        }
    }

    private void crearBase() throws SQLException {

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307", "root", "");
            Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE IF NOT EXISTS recambiosJaimePardo";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void crearTabla() throws SQLException {
        String consulta = "CREATE TABLE coches(id int auto_increment primary key,matricula VARCHAR(20) not null UNIQUE,marca VARCHAR(40) not null,potencia INTEGER,fecha_publicacion TIMESTAMP)";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(consulta);
        sentencia.executeUpdate();
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void insertar(String matricula, String marca, String potencia, LocalDateTime fechaAlta) throws SQLException {
        String consulta = "INSERT INTO coches(matricula, marca, potencia, fecha_publicacion) VALUES(?,?,?,?)";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, matricula);
        sentencia.setString(2, marca);
        sentencia.setString(3, potencia);
        sentencia.setTimestamp(4, Timestamp.valueOf(fechaAlta));

        sentencia.executeUpdate();

        if( sentencia == null ){
            sentencia.close();
        }
    }

    public void eliminar(String matricula) throws SQLException {
        String consulta = "DELETE FROM coches WHERE matricula = ?";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, matricula);
        sentencia.executeUpdate();

        if( sentencia == null ){
            sentencia.close();
        }
    }

    public ResultSet consultarCoches() throws SQLException {
        String consulta = "SELECT * FROM coches";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        sentencia = conexion.prepareStatement(consulta);
        resultado = sentencia.executeQuery();


        return resultado;
    }
}
