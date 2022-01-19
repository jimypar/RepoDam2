public class Mensaje {

    private int tipoconsulta;
    private int tipoObjeto;
    private String consulta;
    private String busqueda;

    public Mensaje(int tipoconsulta, int tipoObjeto, String consulta, String busqueda) {
        this.tipoconsulta = tipoconsulta;
        this.tipoObjeto = tipoObjeto;
        this.consulta = consulta;
        this.busqueda = busqueda;
    }

    public Mensaje(int tipoconsulta, int tipoObjeto, String consulta) {
        this.tipoconsulta = tipoconsulta;
        this.tipoObjeto = tipoObjeto;
        this.consulta = consulta;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public int getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(int tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
    }

    public int getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(int tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
}
