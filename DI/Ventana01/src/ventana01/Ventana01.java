package ventana01;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Ventana01 extends JFrame {

    public Ventana01() {
        setVentana();
        iniciarComponentes();
    }

    // Caracteristicas de la ventana
    private void setVentana() {
        this.setSize(400, 400);
        this.setTitle("Hola Mundo");
        this.setMaximumSize(new Dimension(600, 600));
        this.setMinimumSize(new Dimension(200, 200));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Anular layOut de la Ventana
        this.setLayout(null);
    }
    private void iniciarComponentes() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setSize(400,400);
        //Anular layout del marco
        panel.setLayout(null);        
        // SwingConstants lo que hace es posicionar el texto de una etiqueta y lo orienta es decir, 
        //lo centra lo pone a la izqueirda,derecha.. peero dentro de los margenes de la etiqueta no de la ventana
        //El texto que le mandas al constructor no saldrá si hay un setText aplicado a esta etiqueta
        JLabel etiqueta = new JLabel("Hola que ase",SwingConstants.CENTER);
        etiqueta.setText("Hola");
        //Posicionar y establecer un tamaño de la etiqueta
        etiqueta.setBounds(10,10,200,30);
        //Cambiar color de la letra
        etiqueta.setForeground(Color.WHITE);
        //Cambiar color de fondo de la etiqueta
        etiqueta.setBackground(Color.RED);
        //Para quitar el fondo transparente que viene por defecto de la etiqueta
        etiqueta.setOpaque(true);
        etiqueta.setFont(new Font("times new roman",Font.ITALIC,20));
        File fuente = new File("miletra.ttf");
        try {
            //El .deriveFont lo puedes poner a continuacion del parentesis
            Font font=Font.createFont(Font.TRUETYPE_FONT, fuente)/*.deriveFont(30f)*/;
            Font sizeFont = font.deriveFont(30f);
            etiqueta.setFont(sizeFont);
        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Insertar imagen en una etiqueta
        JLabel imagen = new JLabel();
        //Leer la imagen que queremos utilizar
        ImageIcon emoticono = new ImageIcon("ElPan.png");
        //Establezco tamaño de la imagen
        imagen.setBounds(100,100,250,250);
        //relaciono la etiqueta con la imagen 
        imagen.setIcon(new ImageIcon(emoticono.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_SMOOTH)));
        //Crear un combo box(Menu desplegable)
        JComboBox combo = new JComboBox();
        String[] fontName = Toolkit.getDefaultToolkit().getFontList();
        int j= fontName.length;
        for(int i=0;i<j;i++) {
            combo.addItem(fontName[i]);
        }
        combo.setBounds(1,2,100,30);
        
        panel.add(combo);
        
        panel.add(imagen);
        
        panel.add(etiqueta);
        JLabel etiqueta1 = new JLabel();
        etiqueta1.setText("Que");
        
        etiqueta1.setBounds(10,30,300,30);
        panel.add(etiqueta1);
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setText("Tal");
        etiqueta2.setBounds(10,50,400,30);
        panel.add(etiqueta2);
        
        JButton boton = new JButton();
        boton.setBounds(10,60,50,40);
        boton.setIcon(new ImageIcon(emoticono.getImage().getScaledInstance(boton.getWidth(),boton.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(boton);
        
        //Cada vez que creas un componente en este caso panel, hay que asociarlo con la ventana, para ello se usa getContentPane().add
        this.getContentPane().add(panel);
        
    }

    public static void main(String[] args) {
        Ventana01 v = new Ventana01();
        // El pack adapta la ventana a todos los componentes que tiene,es decir si solo hay 1 componente en este caso una etiqueta la ventana será enana
        //v.pack();
        v.setVisible(true);

    }

}