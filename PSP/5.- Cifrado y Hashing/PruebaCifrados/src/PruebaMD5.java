import java.math.BigInteger;
import java.security.MessageDigest;

public class PruebaMD5 {

    public static void main(String[] args) throws Exception {

        //String que contiene ejemplo de contraseña
        String contrasena = "contraseña";

        //Se crea el objeto MessageDigest para hacer hashing de MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Se cifra convirtiendolo en un array de bytes
        byte[] messageDigest = md.digest(contrasena.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        //Se convierte a String el texto cifrado
        String hashtext = number.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        System.out.println(hashtext);
    }
}
