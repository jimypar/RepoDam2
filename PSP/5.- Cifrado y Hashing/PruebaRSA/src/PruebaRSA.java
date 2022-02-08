import java.io.File;



public class PruebaRSA {


    public static void main(String[] args) throws Exception {


        File miDir = new File (".");

        //Definimos un texto a cifrar
        String str = "Este es el texto a cifrar";

        System.out.println("\nTexto a cifrar:");
        System.out.println(str);

        //Instanciamos la clase
        RSA rsa = new RSA();

        //Generamos un par de claves
        //Admite claves de 512, 1024, 2048 y 4096 bits
        rsa.genKeyPair(512);


        String file_private = "C:\\tmp\\rsa.pri";
        String file_public = "C:\\tmp\\rsa.pub";

        //Las guardamos asi podemos usarlas despues
        //a lo largo del tiempo
        rsa.saveToDiskPrivateKey("C:\\tmp\\rsa.pri");
        rsa.saveToDiskPublicKey("C:\\tmp\\rsa.pub");

        //Ciframos y e imprimimos, el texto cifrado
        //es devuelto en la variable secure
        String secure = rsa.Encrypt(str);

        System.out.println("\nCifrado:");
        System.out.println(secure);



        //A modo de ejemplo creamos otra clase rsa
        RSA rsa2 = new RSA();



        System.out.println ("Directorio actual: " + miDir.getCanonicalPath()+"\\tmp\\rsa.pri");
        rsa2.openFromDiskPrivateKey("C:\\tmp\\rsa.pri");
        rsa2.openFromDiskPublicKey("C:\\tmp\\rsa.pub");

        //Le pasamos el texto cifrado (secure) y nos
        //es devuelto el texto ya descifrado (unsecure)
        String unsecure = rsa2.Decrypt(secure);

        //Imprimimos
        System.out.println("\nDescifrado:");
        System.out.println(unsecure);



    }


}
