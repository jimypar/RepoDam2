import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class PruebaSHA {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        //Se crea el objeto MessageDigest
        MessageDigest md = null;
        String password = "contrasena";


        //SHA-512
        md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());
        //Se realiza el Hashing
        byte[] mb = md.digest();
        //Se muestra por pantalla
        System.out.println(Hex.encodeHex(mb));


        //SHA-384
        md = MessageDigest.getInstance("SHA-384");
        md.update(password.getBytes());
        //Se realiza el Hashing
        mb = md.digest();
        //Se muestra por pantalla
        System.out.println(Hex.encodeHex(mb));


        //SHA-1
        md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());
        //Se realiza el Hashing
        mb = md.digest();
        //Se muestra por pantalla
        System.out.println(Hex.encodeHex(mb));
    }
}
