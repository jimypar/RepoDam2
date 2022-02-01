import com.google.gson.Gson;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

public class BaseDatos {
    private Connection conexion;

    //Metodo que conecta con la base de datos y envia un mensaje de error si falla.
    public boolean conectar(){
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/laliga", "root", "");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR");
            return false;
        }
    }

    //Metodo para desconectarse de la base de datos
    public void desconectar() throws SQLException {
        conexion.close();
    }

    //Metodo que consulta si un usuario existe o no y devuelve si es admin o no
    public int consultarUsuario(String usuario, String password){
        try {
            String SQL = "SELECT * FROM usuario";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                if (rs.getString("email").equals(usuario)){
                    if (rs.getString("password").equals(password)){
                        return rs.getInt("es_administrador");
                    }
                }
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    //Metodo que consulta los entrenadores segun su nombre.
    public String consultarEntrenador(String nombre) {

        //Crea el GSON
        Gson gson = new Gson();

        //Crea un objeto del tipo de la consulta
        Entrenador o = new Entrenador();

        try {
            String SQL = "SELECT * FROM entrenador WHERE nombre=?";
            PreparedStatement stmt = conexion.prepareStatement(SQL);
            stmt.setString(1,nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                o.setNombre(rs.getString("nombre"));
                o.setNacionalidad(rs.getString("nacionalidad"));
                o.setEquipo(rs.getString("idEquipo"));
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String json = gson.toJson(o);

        return json;
    }

    //Metodo que consulta los jugadores segun su nombre.
    public String consultarJugador(String nombre) {

        Gson gson = new Gson();

        //Crea un objeto del tipo de la consulta
        Jugador o = new Jugador();

        try {
            String SQL = "SELECT * FROM jugador WHERE nombre=?";
            PreparedStatement stmt = conexion.prepareStatement(SQL);
            stmt.setString(1,nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                o.setNombre(rs.getString("nombre"));
                o.setNacionalidad(rs.getString("nacionalidad"));
                o.setEquipo(rs.getString("idEquipo"));
                o.setPosicion(rs.getString("posicion"));
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String json = gson.toJson(o);

        return json;
    }

    //Metodo que consulta los estadios segun su nombre.
    public String consultarEstadio(String nombre) {

        //Crea el GSON
        Gson gson = new Gson();

        //Crea un objeto del tipo de la consulta
        Estadio o = new Estadio();

        try {
            String SQL = "SELECT * FROM estadio WHERE nombre=?";
            PreparedStatement stmt = conexion.prepareStatement(SQL);
            stmt.setString(1,nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                o.setNombre(rs.getString("nombre"));
                o.setCiudad(rs.getString("ciudad"));
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String json = gson.toJson(o);

        return json;
    }

    //Metodo para modificar los datos de un entrenador.
    public void modificarEntrenador(String nombre, String json) {

        //Crea el  GSON
        Gson gson = new Gson();

        //Crea un objeto del tipo de consulta y le inserta los datos del JSON.
        Entrenador en = gson.fromJson(json, Entrenador.class);

        try {
            PreparedStatement st = conexion.prepareStatement("UPDATE entrenador SET nombre = ?, nacionalidad = ?, idEquipo = ? WHERE nombre = ?");
            st.setString(4, nombre);
            st.setString(1, en.getNombre());
            st.setString(2, en.getNacionalidad());
            st.setString(3, en.getEquipo());
            st.executeUpdate();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para modificar los datos de un estadio.
    public void modificarEstadio(String nombre, String json) {

        //Crea el GSON
        Gson gson = new Gson();
        //Crea un objeto del tipo de consulta y le inserta los datos del JSON.
        Estadio es = gson.fromJson(json, Estadio.class);

        try {
            PreparedStatement st = conexion.prepareStatement("UPDATE estadio SET nombre = ?, ciudad = ? WHERE nombre = ?");

            st.setString(1, es.getNombre());
            st.setString(2, es.getCiudad());
            st.setString(3, nombre);
            st.executeUpdate();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para modificar los datos de un jugador.
    public void modificarJugador(String nombre, String json) {

        //Crea el GSON
        Gson gson = new Gson();
        //Crea un objeto del tipo de consulta y le inserta los datos del JSON.
        Jugador j = gson.fromJson(json, Jugador.class);

        try {
            PreparedStatement st = conexion.prepareStatement("UPDATE jugador SET nombre = ?, nacionalidad = ?, idEquipo = ?, posicion = ? WHERE nombre = ?");
            st.setString(5, nombre);
            st.setString(1, j.getNombre());
            st.setString(2, j.getNacionalidad());
            st.setString(3, j.getEquipo());
            st.setString(4, j.getPosicion());
            st.executeUpdate();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para eliminar los datos de un entrenador.
    public void eliminarEntrenador(String nombre) {

        try {
            String SQL = "DELETE entrenador.* FROM entrenador WHERE nombre=?";
            PreparedStatement stmt = conexion.prepareStatement(SQL);
            stmt.setString(1,nombre);
            stmt.executeUpdate();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar los datos de un estadio.
    public void eliminarEstadio(String nombre) {

        try {
            String SQL = "DELETE estadio.* FROM estadio WHERE nombre=?";
            PreparedStatement stmt = conexion.prepareStatement(SQL);
            stmt.setString(1,nombre);
            stmt.executeUpdate();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar los datos de un jugador.
    public void eliminarJugador(String nombre) {

        try {
            String SQL = "DELETE jugador.* FROM jugador WHERE nombre=?";
            PreparedStatement stmt = conexion.prepareStatement(SQL);
            stmt.setString(1,nombre);
            stmt.executeUpdate();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
