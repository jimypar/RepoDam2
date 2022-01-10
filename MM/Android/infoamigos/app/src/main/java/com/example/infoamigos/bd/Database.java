package com.example.infoamigos.bd;


import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.infoamigos.util.Amigo;
import com.example.infoamigos.util.Util;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION=1;

    public Database(Context contexto) {
        super(contexto, Constantes.BASE_DATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Constantes.TABLA_AMIGOS + "(" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantes.NOMBRE + " TEXT, " +
                Constantes.EMAIL + " TEXT, " +
                Constantes.TLF + " TEXT, " +
                Constantes.MOVIL + " TEXT, " +
                Constantes.DEUDAS + " REAL, " +
                Constantes.FOTO + " BLOB)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_AMIGOS);
        onCreate(db);

    }

    public void nuevoAmigo(Amigo amigo){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constantes.NOMBRE,amigo.getNombreApellidos());
        values.put(Constantes.EMAIL,amigo.getEmail());
        values.put(Constantes.TLF,amigo.getTlf());
        values.put(Constantes.MOVIL,amigo.getTlfMovil());
        values.put(Constantes.DEUDAS,amigo.getDeudas());
        values.put(Constantes.FOTO, Util.getBytes(amigo.getFoto()));
        db.insertOrThrow(Constantes.TABLA_AMIGOS,null,values);
        db.close();

    }

    public void eliminarAmigo(Amigo amigo){

        SQLiteDatabase db = getWritableDatabase();
        String [] argumentos = new String[]{String .valueOf(amigo.getId())};
        db.delete(Constantes.TABLA_AMIGOS,"_id= ?",argumentos);
        db.close();
    }

    public ArrayList<Amigo> getAmigos(){

        final String[] SELECT ={_ID,Constantes.NOMBRE,Constantes.EMAIL,Constantes.TLF,
                                Constantes.MOVIL,Constantes.DEUDAS,Constantes.FOTO};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constantes.TABLA_AMIGOS,SELECT,null,null,null,null,Constantes.NOMBRE);
        ArrayList<Amigo> listaAmigos = new ArrayList<>();
        Amigo amigo = null;
        while (cursor.moveToNext()){
            amigo = new Amigo();
            amigo.setId(cursor.getLong(0));
            amigo.setNombreApellidos(cursor.getString(1));
            amigo.setEmail(cursor.getString(2));
            amigo.setTlf(cursor.getString(3));
            amigo.setTlfMovil(cursor.getString(4));
            amigo.setDeudas(cursor.getFloat(5));
            amigo.setFoto(Util.getBitmap(cursor.getBlob(6)));

            listaAmigos.add(amigo);
        }

        cursor.close();
        db.close();
        return listaAmigos;
    }

    public ArrayList<Amigo> getAmigosDeuda(){

        final String[] SELECT ={_ID,Constantes.NOMBRE,Constantes.EMAIL,Constantes.TLF,
                Constantes.MOVIL,Constantes.DEUDAS,Constantes.FOTO};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constantes.TABLA_AMIGOS,SELECT,null,null,null,null,Constantes.DEUDAS);
        ArrayList<Amigo> listaAmigos = new ArrayList<>();
        Amigo amigo = null;
        while (cursor.moveToNext()){
            amigo = new Amigo();
            amigo.setId(cursor.getLong(0));
            amigo.setNombreApellidos(cursor.getString(1));
            amigo.setEmail(cursor.getString(2));
            amigo.setTlf(cursor.getString(3));
            amigo.setTlfMovil(cursor.getString(4));
            amigo.setDeudas(cursor.getFloat(5));
            amigo.setFoto(Util.getBitmap(cursor.getBlob(6)));

            listaAmigos.add(amigo);
        }

        cursor.close();
        db.close();
        return listaAmigos;
    }

    public ArrayList<Amigo> getDeudores(float deuda){

        final String[] SELECT ={_ID,Constantes.NOMBRE,Constantes.EMAIL,Constantes.TLF,
                Constantes.MOVIL,Constantes.DEUDAS,Constantes.FOTO};
        SQLiteDatabase db = getReadableDatabase();
        String[] args= {""+deuda};
        Cursor cursor = db.query(Constantes.TABLA_AMIGOS,SELECT,Constantes.DEUDAS +"> ?",args,null,null,Constantes.NOMBRE);
        ArrayList<Amigo> listaAmigos = new ArrayList<>();
        Amigo amigo = null;
        while (cursor.moveToNext()){
            amigo = new Amigo();
            amigo.setId(cursor.getLong(0));
            amigo.setNombreApellidos(cursor.getString(1));
            amigo.setEmail(cursor.getString(2));
            amigo.setTlf(cursor.getString(3));
            amigo.setTlfMovil(cursor.getString(4));
            amigo.setDeudas(cursor.getFloat(5));
            amigo.setFoto(Util.getBitmap(cursor.getBlob(6)));

            listaAmigos.add(amigo);
        }

        cursor.close();
        db.close();
        return listaAmigos;
    }
}
