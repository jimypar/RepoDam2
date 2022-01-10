package Ejercicio3.gui;

import Ejercicio3.Ejercicio3;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;




public class Termostato extends Observable {
    private JFrame frame;
    private JSlider sliderTermostato;
    private JLabel termoTxt;
    private JPanel panel;
    private static Termostato termostato;

    //Singleton del termostato
    public static Termostato getSingletoInstance(){
        if (termostato == null){
            termostato = new Termostato();
        }
        else {
            System.out.println("No se puede crear el objeto");
        }

        return termostato;
    }


    public Termostato() {
        frame = new JFrame("Termostato");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //añado listener del mouse en el termostato cuando se suelta y envia la notificacion
        sliderTermostato.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int temp = sliderTermostato.getValue();
                termoTxt.setText(String.valueOf(temp));
                setChanged();
                notifyObservers(temp);
            }
        });

    }

    public static void main(String[] args) {

        Termostato termostato = new Termostato();

    }


}
