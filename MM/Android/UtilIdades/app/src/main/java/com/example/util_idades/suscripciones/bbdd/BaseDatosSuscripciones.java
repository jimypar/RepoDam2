package com.example.util_idades.suscripciones.bbdd;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.util_idades.suscripciones.util.Suscripcion;
import com.example.util_idades.suscripciones.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BaseDatosSuscripciones extends SQLiteOpenHelper {

    private static final int VERSION=2;

    public BaseDatosSuscripciones(Context contexto){
        super(contexto, Constantes.BASE_DATOS,null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Constantes.TABLA_SUSCRIPCIONES +" ("+_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constantes.NOMBRE+" TEXT, "+
                Constantes.FECHA + " TEXT, "+
                Constantes.FOTO+ " BLOB) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_SUSCRIPCIONES);
        onCreate(sqLiteDatabase);
    }

    public void addSuscripcion(Suscripcion suscripcion){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Constantes.NOMBRE, suscripcion.getNombreSuscripcion());
        values.put(Constantes.FECHA, String.valueOf(suscripcion.getCaducidad()));
        values.put(Constantes.FOTO, Util.getBytes(suscripcion.getFoto()));
        db.insertOrThrow(Constantes.TABLA_SUSCRIPCIONES,null,values);
        db.close();
    }

    public void eliminarSuscripcion(Suscripcion suscripcion){
        SQLiteDatabase db=getWritableDatabase();
        String[] argumentos=new String[]{String.valueOf(suscripcion.getId())};
        db.delete(Constantes.TABLA_SUSCRIPCIONES, "_id=?", argumentos);
        db.close();
    }


    public ArrayList<Suscripcion> getSuscripciones(){
        ArrayList<Suscripcion> listaSuscripciones =new ArrayList<Suscripcion>();
        Suscripcion suscripcion =null;

        final String[] SELECT={_ID,Constantes.NOMBRE,Constantes.FECHA, Constantes.FOTO};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Constantes.TABLA_SUSCRIPCIONES,SELECT,null, null, null,null,Constantes.NOMBRE);

        while(cursor.moveToNext()){
            suscripcion =new Suscripcion();
            suscripcion.setId(cursor.getLong(0));
            suscripcion.setNombreSuscripcion(cursor.getString(1));
            suscripcion.setCaducidad(LocalDateTime.parse(cursor.getString(2)));
            suscripcion.setFoto(Util.getBitmap(cursor.getBlob(3)));
            listaSuscripciones.add(suscripcion);

        }
        cursor.close();
        db.close();
        return listaSuscripciones;

    }

}
