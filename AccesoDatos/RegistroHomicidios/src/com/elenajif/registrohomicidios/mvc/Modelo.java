package com.elenajif.registrohomicidios.mvc;

import com.elenajif.registrohomicidios.base.Homicida;
import com.elenajif.registrohomicidios.base.Victima;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;



import java.time.LocalDate;
import java.util.List;

public class Modelo {

    private SessionFactory factoria;
    private Session sesion;

    public void conectar(boolean mysql){
        Configuration configuracion = new Configuration();

        if(mysql) {
            //Cargo el fichero Hibernate.cfg.xml para conectar con mysql
            configuracion.configure();
        }else {
            //Para conectar con postgresql
            configuracion.configure("hibernatePostgre.cfg.xml");
        }

        configuracion.addAnnotatedClass(Homicida.class);
        configuracion.addAnnotatedClass(Victima.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuracion.getProperties()).build();
        factoria = configuracion.buildSessionFactory(serviceRegistry);

        //Creo una sesion a partir de la factoria
        sesion = factoria.openSession();
    }

    public void desconectar(){
        if(sesion != null){
            sesion.close();
        }
        if(factoria != null){
            factoria.close();
        }
    }

    public void altaVictima(String nombre, boolean esHombre, LocalDate fechaDefuncion, String causaMuerte, Homicida homicida){
        Victima victima = new Victima(nombre, esHombre, fechaDefuncion, causaMuerte, homicida);
        sesion.beginTransaction();
        sesion.saveOrUpdate(victima);
        sesion.getTransaction().commit();
    }

    public void modificarVictima(Victima victima){
        sesion.beginTransaction();
        sesion.saveOrUpdate(victima);
        sesion.getTransaction().commit();
    }

    public void eliminarVictima(Victima victima){
        sesion.beginTransaction();
        sesion.delete(victima);
        sesion.getTransaction().commit();
    }
    public void altaHomicida(String apodo, String arma, boolean asesinoSerie, int annosCarcel){
        Homicida homicida = new Homicida(apodo, arma, asesinoSerie, annosCarcel);
        sesion.beginTransaction();
        sesion.saveOrUpdate(homicida);
        sesion.getTransaction().commit();
    }

    public void modificarHomicida(Homicida homicida){
        sesion.beginTransaction();
        sesion.saveOrUpdate(homicida);
        sesion.getTransaction().commit();
    }

    public void eliminarHomicida(Homicida homicida){
        sesion.beginTransaction();
        //Si quiero solo eliminar al homicida sin perder las victimas
        homicida.removeAllVictimas();
        sesion.delete(homicida);
        sesion.getTransaction().commit();
    }

    public List<Homicida> getHomicidas() {
        Query query = sesion.createQuery("FROM Homicida");
        List<Homicida> lista = query.list();
        return lista;
    }

    public List<Victima> getVictimas(){
        Query query = sesion.createQuery("FROM Victima");
        List<Victima> lista = query.list();
        return lista;
    }
}
