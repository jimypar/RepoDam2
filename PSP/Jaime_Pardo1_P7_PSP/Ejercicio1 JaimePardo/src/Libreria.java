import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Libreria {

    public static String hashear(String text){

        MessageDigest md = null;
        String password = text;
        //Usamos SHA 512
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        //Se realiza el Hashing
        byte[] mb = md.digest();
        //Se muestra por pantalla
        return Hex.encodeHex(mb).toString();

    }

    public static boolean verificarHash(String text, String hash){

        String contraseña = hashear(text);

        if (contraseña.equals(hash)){
            return true;
        }else {
            return false;
        }

    }



}
