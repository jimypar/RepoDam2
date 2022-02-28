package com.practicaUD4.gui;

import com.practicaUD4.base.Pescado;
import com.practicaUD4.base.Fruta;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Clase Controlador
 * @author
 * @see java.awt.event.ActionListener
 * @see java.awt.event.KeyListener
 * @see javax.swing.event.ListSelectionListener
 */
public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    //Campos
    Vista vista;
    Modelo modelo;

    /**
     * Constructor de Controlador
     * @param vista de tipo Vista
     * @param modelo de tipo Modelo
     */
    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        inicializar();
    }

    /**
     * Método inicializar listeners
     */
    private void inicializar() {
        addActionListeners(this);
        addKeyListeners(this);
        addListSelectionListeners(this);
        modelo.conectar();
        listarFrutas(modelo.getFrutas());
        listarPescados(modelo.getPescados());
    }

    /**
     * Método addActionListeners(), añado los listeners a los botones
     * @param listener de tipo ActionListener
     */
    private void addActionListeners(ActionListener listener){
        vista.btnNuevo.addActionListener(listener);
        vista.btnModificar.addActionListener(listener);
        vista.btnEliminar.addActionListener(listener);
        vista.btnEliminarPescado.addActionListener(listener);
        vista.btnModificarPescado.addActionListener(listener);
        vista.btnNuevoPescado.addActionListener(listener);

    }

    /**
     * Método addListSelectionListeners(), añadir listeners a los JLists
     * @param listener de ListSelectionListener
     */
    private void addListSelectionListeners(ListSelectionListener listener){
        vista.listFrutas.addListSelectionListener(listener);
        vista.listPescado.addListSelectionListener(listener);
    }

    /**
     * Método addKeyListeners(), añadir listeners a los campos de buscar
     * @param listener de KeyListener
     */
    private void addKeyListeners(KeyListener listener){
        vista.txtBuscar.addKeyListener(listener);
        vista.txtBuscarPescado.addKeyListener(listener);
    }

    /**
     * Método actionPerformed(), implementado por ActionListener, donde asignas las acciones a los botones
     * @param e de ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        Fruta unaFruta;
        Pescado unPescado;
        switch (comando){
            case "Nuevo":
                unaFruta = new Fruta();
                modificarFrutaFromCampos(unaFruta);
                modelo.guardarFruta(unaFruta);
                listarFrutas(modelo.getFrutas());
                break;
            case "Modificar":
                unaFruta = (Fruta) vista.listFrutas.getSelectedValue();
                modificarFrutaFromCampos(unaFruta);
                modelo.modificarFruta(unaFruta);

                listarFrutas(modelo.getFrutas());
                break;
            case "Eliminar":
                unaFruta = (Fruta) vista.listFrutas.getSelectedValue();
                modelo.borrarFruta(unaFruta);
                listarFrutas(modelo.getFrutas());
                break;
            case "Nuevo Pescado":
                unPescado = new Pescado();
                modificarPescadoFromCampos(unPescado);
                modelo.guardarPescado(unPescado);
                listarPescados(modelo.getPescados());
                break;
            case "Modificar Pescado":
                unPescado = (Pescado) vista.listPescado.getSelectedValue();
                modificarPescadoFromCampos(unPescado);
                modelo.modificarPescado(unPescado);

                listarPescados(modelo.getPescados());
                break;
            case "Eliminar Pescado":
                unPescado = (Pescado) vista.listPescado.getSelectedValue();
                modelo.borrarPescado(unPescado);
                listarPescados(modelo.getPescados());
                break;
        }
    }

    /**
     * Método listarFrutas(), listo las frutas
     * @param lista de List<Fruta>
     */
    private void listarFrutas(List<Fruta> lista){
        vista.dlmFrutas.clear();
        for (Fruta fruta : lista){
            vista.dlmFrutas.addElement(fruta);
        }
    }
    /**
     * Método listarPescados(), listo los pescados
     * @param lista de List<Pescado>
     */
    private void listarPescados(List<Pescado> lista){
        vista.dlmPescado.clear();
        for (Pescado pescado : lista){
            vista.dlmPescado.addElement(pescado);
        }
    }

    /**
     * Método modificarFrutaFromCampos(), para modificar los datos
     * @param unaFruta de tipo Fruta
     */
    private void modificarFrutaFromCampos(Fruta unaFruta) {
        unaFruta.setNombre(vista.txtNombre.getText());
        unaFruta.setMarca(vista.txtMarca.getText());
        unaFruta.setPesoNeto(Double.parseDouble(vista.txtPesoNeto.getText()));
        unaFruta.setFechaCaducidad(vista.dateCaducidad.getDate());
    }
    /**
     * Método modificarPescadoFromCampos(), para modificar los datos
     * @param unPescado de tipo Pescado
     */
    private void modificarPescadoFromCampos(Pescado unPescado) {
        unPescado.setNombre(vista.txtNombrePescado.getText());
        unPescado.setMarca(vista.txtMarcaPescado.getText());
        unPescado.setPesoNeto(Double.parseDouble(vista.txtPesoPescado.getText()));
        unPescado.setFechaCaducidad(vista.dateCaducidadPescado.getDate());
    }

    /**
     * Método valueChanged(), que al pinchar en un registro, me muestre sus datos en sus campos
     * @param e de ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            Fruta unaFruta = (Fruta) vista.listFrutas.getSelectedValue();
            vista.txtNombre.setText(unaFruta.getNombre());
            vista.txtMarca.setText(unaFruta.getMarca());
            vista.txtPesoNeto.setText(String.valueOf(unaFruta.getPesoNeto()));
            vista.dateCaducidad.setDate(unaFruta.getFechaCaducidad());
        }
        if(e.getValueIsAdjusting()){
            Pescado unPescado = (Pescado) vista.listPescado.getSelectedValue();
            vista.txtNombrePescado.setText(unPescado.getNombre());
            vista.txtMarcaPescado.setText(unPescado.getMarca());
            vista.txtPesoPescado.setText(String.valueOf(unPescado.getPesoNeto()));
            vista.dateCaducidad.setDate(unPescado.getFechaCaducidad());
        }
    }

    /**
     * Método keyReleased(), para listar según lo que buscas
     * @param e de KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vista.txtBuscar){
            listarFrutas(modelo.getFrutas(vista.txtBuscar.getText()));
        }
        if(e.getSource() == vista.txtBuscarPescado){
            listarPescados(modelo.getPescado(vista.txtBuscarPescado.getText()));
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }




}
