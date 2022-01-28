public class Mensaje {

    private int id;
    private int posX;
    private int posY;
    private boolean victoria;
    private boolean empate;


    public Mensaje(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Mensaje(int id, boolean victoria, boolean empate) {
        this.id = id;
        this.victoria = victoria;
        this.empate = empate;
    }

    public boolean isEmpate() {
        return empate;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public boolean isVictoria() {
        return victoria;
    }



    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
