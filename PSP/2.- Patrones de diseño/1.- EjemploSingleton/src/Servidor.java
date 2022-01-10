public class Servidor {
    private static Servidor instanciaUnica = new Servidor();

    private Servidor() {}

    public static Servidor getInstance() {
        return instanciaUnica;
    }
}