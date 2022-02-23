public class MensajeInicioSesion {

    private int tipoconsulta;
    private String usuario;
    private String password;

    public MensajeInicioSesion(int tipoconsulta, String usuario, String password) {
        this.tipoconsulta = tipoconsulta;
        this.usuario = usuario;
        this.password = password;
    }

    public MensajeInicioSesion(Integer tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
    }


    public int getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(int tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
