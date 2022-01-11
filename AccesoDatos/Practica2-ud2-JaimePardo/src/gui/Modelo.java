package gui;

import com.sun.corba.se.impl.orb.PrefixParserAction;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by DAM on 13/12/2021.
 */
public class Modelo {
    private String ip;
    private String user;
    private String password;
    private String adminPassword;

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public Modelo() {
        getPropValues();
    }

    private Connection conexion;

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallerJaimePardo", "root", "");
        } catch (SQLException e) {
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallerJaimePardo", "root", "");
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
            e.printStackTrace();
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

    void desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insertarCliente(String dni, String nombre, String apellidos, String email, String telefono) {
        String sentenciaSql = "INSERT INTO cliente (dni, nombre, apellidos, email, telefono)" +
                "VALUES (?,?,?,?,?)";

        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellidos);
            sentencia.setString(4, email);
            sentencia.setString(5, telefono);
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

    void insertarMecanico(String nombre, String apellidos, String telefono) {
        String sentenciaSql = "INSERT INTO mecanico(nombre, apellidos, telefono) VALUES (?, ?, ?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, telefono);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void insertarCoche(String tipo, String matricula, String marca, LocalDate fechaAlta, String cliente) {
        String sentenciaSql = "INSERT INTO libros (tipo, matricula, marca, fecha_alta, id_cliente) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        int idCliente = Integer.valueOf(cliente.split(" ")[0]);

        //TODO: Asignar IDs a mecanico y recambio

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, tipo);
            sentencia.setString(2, matricula);
            sentencia.setString(3, marca);
            sentencia.setDate(4, Date.valueOf(fechaAlta));
            sentencia.setInt(5, idCliente);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarCliente(String dni, String nombre, String apellidos, String email, String telefono, int idCliente) {
        String sentenciaSql = "UPDATE autores SET dni=?,nombre=?, apellidos=?, email=?, telefono=?" +
                "WHERE id=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellidos);
            sentencia.setString(4, email);
            sentencia.setString(5, telefono);
            sentencia.setInt(6, idCliente);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarMecanico(String nombre, String apellidos, String telefono, int idMecanico) {

        String sentenciaSql = "UPDATE editoriales SET nombre = ?, apellidos = ?, telefono = ?" +
                "WHERE id = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, telefono);
            sentencia.setInt(4, idMecanico);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarCoche(String tipo, String matricula, String marca, LocalDate fechaAlta, String cliente, int idCoche) {

        String sentenciaSql = "UPDATE coche SET tipo = ?, matricula = ?, marca = ?, fecha_alta = ?, cliente = ? WHERE id = ?";
        PreparedStatement sentencia = null;

        int idCliente = Integer.valueOf(cliente.split(" ")[0]);//TODO: Asignar IDs a mecanico y recambio

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, tipo);
            sentencia.setString(2, matricula);
            sentencia.setString(3, marca);
            sentencia.setDate(4, Date.valueOf(fechaAlta));
            sentencia.setInt(5, idCliente);
            sentencia.setInt(6, idCoche);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void borrarMecanico(int idMecanico) {
        String sentenciaSql = "DELETE FROM mecanico WHERE id=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idMecanico);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void borrarCliente(int idCliente) {
        String sentenciaSql = "DELETE FROM cliente WHERE id = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idCliente);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void borrarCoche(int idCoche) {
        String sentenciaSql = "DELETE FROM coche WHERE id = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idCoche);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    ResultSet consultarMecanico() throws SQLException {
        String sentenciaSql = "SELECT concat(id) AS 'ID', concat(nombre) AS 'Nombre mecanico', " +
                "concat(apellidos) AS 'Apellidos', concat(telefono) AS 'Teléfono' FROM mecanico";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarCliente() throws SQLException {
        String sentenciaSql = "SELECT concat(id) AS 'ID', concat(dni) AS 'DNI', concat(nombre) AS 'Nombre', concat(apellidos) AS 'Apellidos', " +
                "concat(email) AS 'Email', concat(telefono) AS 'Teléfono' FROM cliente";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarCoche() throws SQLException {
        String sentenciaSql = "SELECT concat(co.id) AS 'ID', concat(co.tipo) AS 'Tipo', concat(co.matricula) AS 'Matricula', " +
                "concat(co.marca) AS 'Marca', " +
                "concat(co.fecha_alta) AS 'Alta', " +
                "concat(cl.id, ' - ', cl.apellidos, ', ', cl.nombre) AS 'Cliente' FROM coche AS co " +
                "INNER JOIN cliente AS cl ON cl.id = co.id_cliente";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    //usamos los datos del cuadro de dialogo
    private void getPropValues() {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
            ip = prop.getProperty("ip");
            user = prop.getProperty("user");
            password = prop.getProperty("pass");
            adminPassword = prop.getProperty("admin");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    void setPropValues(String ip, String user, String pass, String adminPass) {
        try {
            Properties prop = new Properties();
            prop.setProperty("ip", ip);
            prop.setProperty("user", user);
            prop.setProperty("pass", pass);
            prop.setProperty("admin", adminPass);
            OutputStream out = new FileOutputStream("config.properties");
            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ip=ip;
        this.user=user;
        this.password=pass;
        this.adminPassword=adminPass;
    }

    //comprobaciones llamando a funciones de sql
    public boolean cocheMatriculaYaExiste(String matricula) {
        String consulta="SELECT existeMatricula(?)";
        PreparedStatement function;
        boolean matExists=false;
        try {
            function=conexion.prepareStatement(consulta);
            function.setString(1,matricula);
            ResultSet rs =function.executeQuery();
            rs.next();
            matExists=rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matExists;
    }

    public boolean dniClienteYaExiste(String dni) {
        String dniConsulta = "SELECT existeDniCliente(?)";
        PreparedStatement function;
        boolean nameExists = false;
        try {
            function = conexion.prepareStatement(dniConsulta);
            function.setString(1, dni);
            ResultSet rs = function.executeQuery();
            rs.next();

            nameExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameExists;
    }


}
