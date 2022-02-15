package libreria;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;


/**
 *
 * Clase que seencarga de fifrar texto utilizando el algoritmo libreria.RSA
 *
 */
public class RSA {

    public PrivateKey PrivateKey = null;
    public PublicKey PublicKey = null;


    /**
     * Constructor vacio de la clase
     */
    public RSA() {}


    /**
     * Método destinado a generar la clave privada que será utilizada
     * en el algoritmo libreria.RSA
     *
     * @param key String que representa la clave almacenada en el fichero
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public void setPrivateKeyString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encodedPrivateKey = stringToBytes(key);

        //Se instancia el KeyFactory para obtener una contraseña valida para libreria.RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        java.security.PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        //Se asigna la clave privada
        this.PrivateKey = privateKey;
    }


    /**
     * Método destinado a generar la clave pública que será utilizada
     * en el algoritmo libreria.RSA
     * @param key String que representa la clave almacenada en el fichero
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public void setPublicKeyString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException{
        byte[] encodedPublicKey = stringToBytes(key);

        //Se instancia el KeyFactory para obtener una contraseña valida para libreria.RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        //Se asigna la clave publica
        this.PublicKey = publicKey;
    }


    /**
     * Método que retorna la clave privada
     *
     * @return String que representa la clave privada utilizada en el algoritmo
     */
    public String getPrivateKeyString(){
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(this.PrivateKey.getEncoded());
        return bytesToString(pkcs8EncodedKeySpec.getEncoded());
    }


    /**
     * Método que retorna la clave privada
     *
     * @return String que representa la clave publica utilizada en el algoritmo
     */
    public String getPublicKeyString(){
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(this.PublicKey.getEncoded());
        return bytesToString(x509EncodedKeySpec.getEncoded());
    }


    /**
     * Método que se encarga de generar la clave pública y privada que serán
     * utilizadas en el algoritmo
     *
     * @param size int que representa el número de bytes que se utilizarán para
     *             crear las contraseñas de cifrado
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public void genKeyPair(int size) throws NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
        //Se crea el KeyPairGenerator con el número de bytes introducido por el usuario
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        //Se crean KeyPair que generará las contraseñas
        KeyPair kp = kpg.genKeyPair();

        //Se crea la clave pública
        PublicKey publicKey = kp.getPublic();
        //Se crea la clave privada
        PrivateKey privateKey = kp.getPrivate();

        //Se asignan las claves
        this.PrivateKey = privateKey;
        this.PublicKey = publicKey;
    }


    /**
     * Método que se encarga de cifrar un texto mediante el algoritmo RSA
     *
     * @param plain String que representa el texto que se desea cifrar mediante libreria.RSA
     * @return String que representa el texto cifrado mediante algoritmo libreria.RSA
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeySpecException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     */
    public String Encrypt(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, UnsupportedEncodingException, NoSuchProviderException {

        byte[] encryptedBytes;
        //Se indica que se va a cifrar utilizando libreria.RSA
        Cipher cipher = Cipher.getInstance("RSA");
        //Se indica que se va a cifrar, y para ello se utiliza la clave publica
        cipher.init(Cipher.ENCRYPT_MODE, this.PublicKey);
        encryptedBytes = cipher.doFinal(plain.getBytes());
        //Se retorna el texto cifrado
        return bytesToString(encryptedBytes);
    }


    /**
     * Método que se encarga de desencriptar un texto cifrado mediante libreria.RSA
     *
     * @param result String que representa el texto que se desea
     *               desencriptar mediante la utilización del algoritmo
     *               libreria.RSA
     * @return String que representa el texto desencriptado
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String Decrypt(String result) throws NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] decryptedBytes;
        //Se indica que se va a utilizar el algoritmo libreria.RSA
        Cipher cipher = Cipher.getInstance("RSA");
        //Se indica que se va a desencriptar, y para ello se utiliza la clave privada
        cipher.init(Cipher.DECRYPT_MODE, this.PrivateKey);
        //se desencripta
        decryptedBytes = cipher.doFinal(stringToBytes(result));
        //Se retorna el texto desencritado
        return new String(decryptedBytes);
    }


    /**
     * Método que convierte un array de bytes a String
     * @param b array de bytes
     *
     * @return String que representa el array de bytes convertido en String
     */
    public String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }


    /**
     * Método que convierte un String en un array de bytes
     *
     * @param s String que representa el String que se desea convertir en un
     *          array de bytes
     *
     * @return Array de bytes que representa el String convertido
     */
    public byte[] stringToBytes(String s) {
        byte[] b2 = new BigInteger(s, 36).toByteArray();
        return Arrays.copyOfRange(b2, 1, b2.length);
    }


    /**
     * Método que se encarga de guardar en un fichero la clave privada
     *
     * @param path String que representa la ruta del fichero en la que
     *             se desea almacenar la clave privada
     *
     * @throws IOException
     */
    public void saveToDiskPrivateKey(String path) throws IOException {
        try {
            //Se crea el fichero que va  utilizar en la ruta indicada
            File directorio = new File(path);
            directorio.createNewFile();
            //Se escribe el contenido en el fichero
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            out.write(this.getPrivateKeyString());
            //Se fuerza la salida en el fichero
            out.flush();
            //Se cierra el fichero
            out.close();
        } catch (Exception e) {
            System.err.println("Excepcion: " + e.getMessage());
        }
    }


    /**
     * Método que se encarga de guardar en un fichero la clave pública
     *
     * @param path String que representa la ruta del fichero en la que
     *             se desea almacenar la clave pública
     *
     * @throws IOException
     */
    public void saveToDiskPublicKey(String path) {
        try {
            //Se crea el fichero que va  utilizar en la ruta indicada
            File directorio = new File(path);
            directorio.createNewFile();
            //Se escribe el contenido en el fichero
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            out.write(this.getPublicKeyString());
            //Se fuerza la salida en el fichero
            out.flush();
            //Se cierra el fichero
            out.close();
        } catch (Exception e) {
            System.err.println("Excepcion: " + e.getMessage());
        }
    }


    /**
     * Método que obtiene la clave pública que se ha almacenado con anterioridad
     * en un fichero
     *
     * @param path String que representa la ruta del fichero que contiene la clave
     *             pública que se utilizará en el algoritmo
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public void openFromDiskPublicKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //Abre y lee el fichero
        String content = this.readFileAsString(path);
        //asigna la clave pública
        this.setPublicKeyString(content);
    }


    /**
     * Método que obtiene la clave privada que se ha almacenado con anterioridad
     * en un fichero
     *
     * @param path String que representa la ruta del fichero que contiene la clave
     *      *             pública que se utilizará en el algoritmo
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public void openFromDiskPrivateKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //Abre y lee el fichero
        String content = this.readFileAsString(path);
        //asigna la clave pública
        this.setPrivateKeyString(content);
    }


    /**
     * Método que lee el contenido de un fichero, retornando un string con todo
     * el contenido del mismo
     *
     * @param filePath String que representa la ruta del fichero que se desea leer
     * @return String que representa el contenido del fichero indicado
     * @throws IOException
     */
    private String readFileAsString(String filePath) throws IOException {
        //Se crea la estructura para leer del fichero
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        //Se lee el contenido de todo el fichero
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            //Se almacena el contenido en el String
            fileData.append(readData);
        }
        //Se cierra el fichero
        reader.close();
        //Se retorna el String
        return fileData.toString();
    }
}
