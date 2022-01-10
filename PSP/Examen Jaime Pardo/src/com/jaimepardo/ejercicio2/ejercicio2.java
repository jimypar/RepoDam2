package com.jaimepardo.ejercicio2;


public class ejercicio2 {

    public static void main(String[] args) {

        double vueltasRedBulo = 0;
        double vueltasMariMerche = 0;
        double vueltasFuelarre = 0;
        int pos = 1;
        int km = 99;

        do {

            RedBulo c1 = new RedBulo();
            MariMerche c2 = new MariMerche();
            Fuelarre c3 = new Fuelarre();

            c1.start();
            c2.start();
            c3.start();

            while (c1.isAlive() || c2.isAlive() || c3.isAlive()) {
            }

            vueltasRedBulo += c1.getKm();
            vueltasMariMerche += c2.getKm();
            vueltasFuelarre += c3.getKm();

            if (vueltasRedBulo >= km && vueltasRedBulo <= km+2) {
                System.out.println(pos+" Redbulo");
                pos++;
            }
            if (vueltasMariMerche == km) {
                System.out.println(pos+" MariMerche");
                pos++;
            }
            if (vueltasFuelarre >= km && vueltasFuelarre <= km+0.5) {
                System.out.println(pos+" Fuelarre");
                pos++;
            }

        }while (vueltasRedBulo<=km || vueltasMariMerche <=km || vueltasFuelarre<=km);

    }

}
