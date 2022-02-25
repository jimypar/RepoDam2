package com.elenajif.vehiculosHibernateElena.gui;

import com.elenajif.vehiculosHibernateElena.Coche;
import com.elenajif.vehiculosHibernateElena.Propietario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAM on 04/02/2022.
 */
public class Modelo {
    SessionFactory sessionFactory;

    public void desconectar() {
        //cierra la factoria de sesiones
        if (sessionFactory !=null && sessionFactory.isOpen())
            sessionFactory.close();
    }

    public void conectar() {
        Configuration configuracion = new Configuration();
        //cargo el fichero de configuracion
        configuracion.configure("hibernate.cfg.xml");
        //indico las clases mapeadas
        configuracion.addAnnotatedClass(Coche.class);
        configuracion.addAnnotatedClass(Propietario.class);
        //creamos un objeto ServiceRegistry a partir de los parametros
        //de configuracion
        //esta clase se usa para gestionar y proveer acceso a servicios
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();
        //finalmente creamos un objeto sessionFactory a partir de la configuracion
        //y del registro de servicios
        sessionFactory=configuracion.buildSessionFactory(ssr);
    }

    public void altaCoche(Coche nuevoCoche) {
        //obtengo una sesion a partir de la factoria de sesiones
        //creo un objeto que está solicitando una sesion
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoCoche);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificar(Coche cocheSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(cocheSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrar(Coche cocheBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(cocheBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Coche> getCoches() {
        Session sesion = sessionFactory.openSession();
        //el from lleva el nombre de la clase
        Query query = sesion.createQuery("FROM Coche");
        ArrayList<Coche> lista =(ArrayList<Coche>)query.getResultList();
        sesion.close();
        return lista;
    }

    public ArrayList<Propietario> getPropietarios() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Propietario");
        ArrayList<Propietario> lista =(ArrayList<Propietario>)query.getResultList();
        sesion.close();
        return lista;
    }

    public ArrayList<Coche> getCochesPropietario(Propietario propietarioSeleccionado) {
        Session sesion = sessionFactory.openSession();
        //select estandar de sql
        //select campo from tabla where campos
        //si tiene un parametro
        //select campo from tabla where campos=parametro
        //en hibernate se ponen clases y parametros
        //Coche es clase propietario es clase y prop es parametro
        Query query = sesion.createQuery("FROM Coche WHERE propietario=:prop");
        query.setParameter("prop",propietarioSeleccionado);
        ArrayList<Coche> lista =(ArrayList<Coche>)query.getResultList();
        sesion.close();
        return lista;
    }

}
