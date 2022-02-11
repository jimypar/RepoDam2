
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String contraseña = "jaimePardo47";

        String hash = Libreria.hashear(contraseña);

        System.out.println(hash);

        System.out.println(Libreria.verificarHash(contraseña,hash));


    }
}
