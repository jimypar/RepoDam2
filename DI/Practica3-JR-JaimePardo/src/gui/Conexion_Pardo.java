package gui;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Created by DAM on 13/12/2021.
 */
public class Conexion_Pardo {

    public Conexion_Pardo(){}

    private Connection conexion;

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jrJaimePardo", "root", "");
        } catch (SQLException e) {
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
                PreparedStatement statement = null;

                String code = leerFichero();
                String[] query = code.split("--");
                for (String aQuery : query) {
                    statement = conexion.prepareStatement(aQuery);
                    statement.executeUpdate();
                }
                assert statement != null;
                statement.close();
            } catch (SQLException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String leerFichero() throws IOException {
        //basedatos_java no tiene delimitador
        //StringBuilder es dinamica
        try (BufferedReader reader = new BufferedReader(new FileReader("basedatos_java.sql"))) {
            String linea;
            StringBuilder stringBuilder = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        }
    }


    void insertarAutor(String dni, String nombre, String apellidos, String direccion, String ciudad, Integer telefono) {
        String sentenciaSql = "INSERT INTO cliente (dni, nombre, apellidos, direccion, ciudad, telefono)" +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellidos);
            sentencia.setString(4, direccion);
            sentencia.setString(5, ciudad);
            sentencia.setInt(6, telefono);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    ResultSet consultarAutor() throws SQLException {
        String sentenciaSql = "SELECT concat(id_cliente) AS 'ID', concat(dni) AS 'DNI', concat(nombre) AS 'Nombre', concat(apellidos) AS 'Apellidos', " +
                "concat(direccion) AS 'Direccion',concat(ciudad) AS 'Ciudad', concat(telefono) AS 'Telefono' FROM cliente";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

}
