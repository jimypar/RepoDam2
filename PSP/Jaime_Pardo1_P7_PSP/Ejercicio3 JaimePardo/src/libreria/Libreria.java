package libreria;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Libreria {

    public static String encriptar(String text) throws Exception {

        //Instanciamos la clase
        RSA rsa = new RSA();

        //Generamos un par de claves
        //Admite claves de 512, 1024, 2048 y 4096 bits
        rsa.genKeyPair(4096);

        String file_private = "tmp/rsa.pri/rsa.pri";
        String file_public = "tmp/rsa.pub/.pub";

        //Las guardamos asi podemos usarlas despues
        //a lo largo del tiempo
        rsa.saveToDiskPrivateKey("tmp/rsa.pri/rsa.pri");
        rsa.saveToDiskPublicKey("tmp/rsa.pub/rsa.pub");

        //Ciframos y e imprimimos, el texto cifrado
        //es devuelto en la variable secure
        String secure = rsa.Encrypt(text);

        return secure;

    }
    public static String desencriptar(String text) throws Exception {


        //A modo de ejemplo creamos otra clase rsa
        RSA rsa = new RSA();

        rsa.openFromDiskPrivateKey("tmp/rsa.pri/rsa.pri");
        rsa.openFromDiskPublicKey("tmp/rsa.pub/rsa.pub");

        //Le pasamos el texto cifrado (secure) y nos
        //es devuelto el texto ya descifrado (unsecure)
        String unsecure = rsa.Decrypt(text);

        return unsecure;

    }

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
        return Base64.getEncoder().encodeToString(mb);

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
