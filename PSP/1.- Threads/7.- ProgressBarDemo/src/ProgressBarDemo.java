/**
 * @author Teru
 *
 * Ejemplo de Progress Bar, que va actualizando la barra de progreso según
 * simula la realización de una tarea
 *
 *
 */

package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.Random;

public class ProgressBarDemo extends JPanel
        implements ActionListener,
        PropertyChangeListener {

    private JProgressBar progressBar;
    private JButton startButton;
    private JTextArea taskOutput;
    private Task task;

    class Task extends SwingWorker<Void, Void> {
        /*
         *  Tarea que se ejecuta ne segundo plano y que será la encargada de
        modificar la Progress Bar
        */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            //Se inicializa a cero
            setProgress(0);
            //Hasta que la PB sea menor que 100
            while (progress < 100) {
                //Se duerme durante un segundo
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
                //Se hace un progreso aleatorio
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }

        /*
         * Funciones que se realizan cuando finaliza la progressBar
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            startButton.setEnabled(true);
            setCursor(null); //turn off the wait cursor
            taskOutput.append("Done!\n");
        }
    }

    /**
     * Contructor de la clase
     */
    public ProgressBarDemo() {
        super(new BorderLayout());

        //Se crea la IU
        startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(this);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }

    /**
     * Método invocado cuando se pulsa el boton de Start.
     */
    public void actionPerformed(ActionEvent evt) {
        startButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    /**
     * Metodo invocado cuando  cuando se produce un cambio en el progreso
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            taskOutput.append(String.format(
                    "Completed %d%% of task.\n", task.getProgress()));
        }
    }


    /**
     * Se crea y se muestra la interfaz de usuarios
     *
     */
    private static void createAndShowGUI() {
        //Se crea el JFrame
        JFrame frame = new JFrame("ProgressBarDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Se crea el Panel
        JComponent newContentPane = new ProgressBarDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Se muestra la ventana
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Método para probar la ProgressBar
     *
     */
    public static void main(String[] args) {
        //Se lanza la interfaz de usuario de la PB
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
