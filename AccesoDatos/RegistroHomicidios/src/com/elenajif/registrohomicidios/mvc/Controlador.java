package com.elenajif.registrohomicidios.mvc;

import com.elenajif.registrohomicidios.base.Homicida;
import com.elenajif.registrohomicidios.base.Victima;
import com.elenajif.registrohomicidios.util.Util;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.util.List;


public class Controlador implements ActionListener, ListSelectionListener, WindowListener{
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        anadirActionListeners(this);
        anadirListSeletionListeners(this);
        anadirWindowListeners(this);

        //En el momento en que haga la vista visible,
        // se dispara el evento WindowEvent, que es capturado por el listener WindowOpened
        vista.frame.setVisible(true);
    }

    /**
     * Vinculo un listener con los elementos graficos
     * @param listener el listener
     */
    private void anadirActionListeners(ActionListener listener){
        vista.altaHomicidaBtn.addActionListener(listener);
        vista.altaVictimaBtn.addActionListener(listener);
        vista.eliminarHomicidaBtn.addActionListener(listener);
        vista.eliminarVictimaBtn.addActionListener(listener);
        vista.modificarHomicidaBtn.addActionListener(listener);
        vista.modificarVictimaBtn.addActionListener(listener);
        vista.mostrarGraficoBtn.addActionListener(listener);
        vista.mysqlItem.addActionListener(listener);
        vista.postgreItem.addActionListener(listener);
    }

    /**
     * Vinculo un listener con los elementos graficos
     * @param listener el listener
     */
    private void anadirListSeletionListeners(ListSelectionListener listener){
        vista.listVictimas.addListSelectionListener(listener);
        vista.listHomicidas.addListSelectionListener(listener);
        vista.listVictimasHomicida.addListSelectionListener(listener);
    }

    /**
     * Vinculo un listener con los elementos graficos
     * @param listener el listener
     */
    public void anadirWindowListeners(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    /**
     * Vinculo un listemer con los elementos graficos
     */
    private void anadirChangeListeners(ChangeListener listener){

    }

    /**
     * Método que responde a un evento ActionEvent sobre los botones
     * @param e el evento lanzado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "AltaVictima":{
                modelo.altaVictima(vista.nombreTxt.getText(), vista.hombreRB.isSelected(), vista.datePicker.getDate(), vista.causaMuerteTxt.getText(), (Homicida) vista.dcbmHomicidaVictima.getSelectedItem());

            }
                break;
            case "ModificarVictima":{
                if(!vista.listVictimas.isSelectionEmpty()){
                    Victima victima = vista.listVictimas.getSelectedValue();

                    modificarVictima(victima);
                    modelo.modificarVictima(victima);


                }else{
                    Util.mensajeError("Debes seleccionar alguna victima");
                }
            }
            break;
            case "EliminarVictima":{
                if(!vista.listVictimas.isSelectionEmpty()) {
                    Victima victima = vista.listVictimas.getSelectedValue();
                    vista.dlmVictimas.removeElement(victima);
                    modelo.eliminarVictima(victima);

                }else{
                    Util.mensajeError("Debes seleccionar alguna victima");
                }
            }
            break;
            case "AltaHomicida":{
                modelo.altaHomicida(vista.apodoTxt.getText(), vista.armaTxt.getText(), vista.asesinoSerieCBox.isSelected(),Integer.parseInt(vista.tiempoCarcelTxt.getText()));

            }
            break;
            case "ModificarHomicida":{
                if(!vista.listHomicidas.isSelectionEmpty()){

                    Homicida homicida = vista.listHomicidas.getSelectedValue();
                    modificarHomicida(homicida);
                    modelo.modificarHomicida(homicida);

                }else{
                    Util.mensajeError("Debes seleccionar algun homicida");
                }
            }
            break;
            case "EliminarHomicida":{
                if(!vista.listHomicidas.isSelectionEmpty()) {
                    Homicida homicida = vista.listHomicidas.getSelectedValue();
                    vista.dlmHocimidas.removeElement(homicida);
                    modelo.eliminarHomicida(homicida);

                }else{
                    Util.mensajeError("Debes seleccionar algun homicida");
                }
            }
            break;
            case "MostrarGrafico":{
                mostrarGraficoEstadisticas();
            }
            break;

            case "mysql":{
                modelo.desconectar();
                //Me conecto a mysql
                modelo.conectar(true);
            }
            break;

            case "postgre":{
                modelo.desconectar();
                //Me conecto a postgreSQL (debo tener la base de datos en postgre)
                modelo.conectar(false);
            }
            break;
        }
        listarVictimas();
        listarHomicidasComboBox();
        listarHomicidas();
    }



    /**
     * Modifico los datos de la victima a partir de los datos de los de los campos de texto
     * @param victima la victima seleccionada
     */
    private void modificarVictima(Victima victima) {
        victima.setNombre(vista.nombreTxt.getText());
        victima.setEsHombre(vista.hombreRB.isSelected());
        victima.setCausaMuerte(vista.causaMuerteTxt.getText());
        victima.setFechaDefuncion(Date.valueOf(vista.datePicker.getDate()));

        //Las relaciones siempre se deben modificar desde el lado padre de la relacion (Homicida)
        //Si la victima tiene un homicida, elimino a la victima de la lista de victimas del homicida
        if(victima.getHomicida() != null) {
            victima.getHomicida().removeVictima(victima);
        }
        //Si he seleccionado un homicida en el cbox, anado a su victima
        Homicida homicidaSeleccionado = (Homicida)vista.dcbmHomicidaVictima.getSelectedItem();
        if(homicidaSeleccionado != null){
            homicidaSeleccionado.addVictima(victima);
        }
    }

    /**
     * Modifico al homicida a partir de los datos de los campos del homicida
     * @param homicida el homicida seleccionado
     */
    private void modificarHomicida(Homicida homicida){
        homicida.setApodo(vista.apodoTxt.getText());
        homicida.setAnnosCarcel(Integer.parseInt(vista.tiempoCarcelTxt.getText()));
        homicida.setArma(vista.armaTxt.getText());
        homicida.setAsesinoSerie(vista.asesinoSerieCBox.isSelected());
    }

    /**
     * Listo las victimas en el JList de victimas
     */
    private void listarVictimas(){
        vista.dlmVictimas.clear();
        List<Victima> lista = modelo.getVictimas();
        for(Victima victima : lista){
            vista.dlmVictimas.addElement(victima);
        }
    }
    /**
     * Listo las victimas del homicida seleccionado en el JList de victimas de un homicida
     */
    private void listarVictimasHomicida(){
        vista.dlmVictimasHomicida.clear();

        for(Victima victima : vista.listHomicidas.getSelectedValue().getVictimas()){
            vista.dlmVictimasHomicida.addElement(victima);
        }
    }

    /**
     * Listo los homicidas en el JList de homicidas
     */
    private void listarHomicidas(){
        vista.dlmVictimasHomicida.clear();
        vista.dlmHocimidas.clear();
        List<Homicida> lista = modelo.getHomicidas();
        for(Homicida homicida : lista){
            vista.dlmHocimidas.addElement(homicida);
        }
    }

    /**
     * Listo los homicidas en el ComboBox de homicidas
     */
    private void listarHomicidasComboBox(){
       vista.dcbmHomicidaVictima.removeAllElements();
       vista.dcbmHomicidaVictima.addElement(null);
       List<Homicida> lista = modelo.getHomicidas() ;
       for(Homicida homicida : lista){
           vista.dcbmHomicidaVictima.addElement(homicida);
       }
    }

    /**
     * Metodo que responde a las selecciones en los 3 JLists
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            if (e.getSource() == vista.listVictimas) {
                mostrarDatosVictima();
            } else if (e.getSource() == vista.listHomicidas) {
                mostrarDatosHomicida();
            } else if (e.getSource() == vista.listVictimasHomicida) {
                seleccionarVictimaHomicida();
            }
        }
    }

    /**
     * Metodo que me selecciona la Victima en el JList de victimas
     */
    private void seleccionarVictimaHomicida() {
        if(!vista.listVictimasHomicida.isSelectionEmpty()){
            Victima victima = vista.listVictimasHomicida.getSelectedValue();
            vista.tabbedPane1.setSelectedIndex(0);
            vista.listVictimas.setSelectedValue(victima, true);
            mostrarDatosVictima();
        }
    }

    /**
     * Metodo que me muestra los datos en los campos de texto, del homicida seleccionado en el JList
     */
    private void mostrarDatosHomicida() {
        if(!vista.listHomicidas.isSelectionEmpty()){
            Homicida homicida = vista.listHomicidas.getSelectedValue();
            vista.apodoTxt.setText(homicida.getApodo());
            vista.tiempoCarcelTxt.setText(String.valueOf(homicida.getAnnosCarcel()));
            vista.asesinoSerieCBox.setSelected(homicida.isAsesinoSerie());
            vista.armaTxt.setText(homicida.getArma());
            listarVictimasHomicida();
        }
    }

    /**
     * Metodo que me muestra los datos en los campos de texto, de la victima seleccionada en el JList
     */
    private void mostrarDatosVictima() {
        if(!vista.listVictimas.isSelectionEmpty()){
            Victima victima = vista.listVictimas.getSelectedValue();
            vista.nombreTxt.setText(victima.getNombre());
            vista.hombreRB.setSelected(victima.isEsHombre());
            vista.causaMuerteTxt.setText(victima.getCausaMuerte());
            vista.datePicker.setDate(victima.getFechaDefuncion().toLocalDate());
            vista.cbHomicidaVictima.setSelectedItem(victima.getHomicida());
        }
    }

    /**
     * Me muestra un gráfico usando las librerias JFreeChart
     */
    private void mostrarGraficoEstadisticas() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(Homicida homicida : modelo.getHomicidas()){
            if(!homicida.getVictimas().isEmpty()) {
                dataset.setValue(homicida.getApodo(), homicida.getVictimas().size());
            }
        }
        JFreeChart chart = ChartFactory.createPieChart("Victimas por homicida", dataset, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        JDialog dialog = new JDialog();
        dialog.setContentPane(panel);
        dialog.setTitle("Grafico");
        dialog.setModal(true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Cuando se abre la vista, conecto hibernate y listo los diferentes elementos
     * @param e
     */
    @Override
    public void windowOpened(WindowEvent e) {
        //Le indico que estoy conectando con mysql
        modelo.conectar(true);
        listarVictimas();
        listarHomicidas();
        listarHomicidasComboBox();
    }

    /**
     * Cuando se cierra la vista, desconecto hibernate
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e) {
        modelo.desconectar();
    }


    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


}
