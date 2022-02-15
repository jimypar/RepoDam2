import libreria.Libreria;

public class Main {
    public static void main(String[] args) throws Exception {

        String contraseña = "jaimePardo47";

        String hash = Libreria.hashear(contraseña);

        System.out.println(Libreria.verificarHash(contraseña,hash));

        String encriptado = Libreria.encriptar(contraseña);

        System.out.println(Libreria.desencriptar(encriptado));

    }
}
