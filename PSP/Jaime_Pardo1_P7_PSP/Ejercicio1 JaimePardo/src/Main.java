import libreria.Libreria;

public class Main {
    public static void main(String[] args) throws Exception {

        //Creamos una contraseña
        String contraseña = "jaimePardo47";

        //Hasheamos la contraseña
        String hash = Libreria.hashear(contraseña);

        //Mostramos la contraseña hasehada
        System.out.println(hash);

        //Mostramos la comprobacion del hash verificado
        System.out.println(Libreria.verificarHash(contraseña,hash));

        //Ciframos la contraseña
        String encriptado = Libreria.encriptar(contraseña);

        //Mostramos la contraseña descifrada
        System.out.println(Libreria.desencriptar(encriptado));

    }
}
