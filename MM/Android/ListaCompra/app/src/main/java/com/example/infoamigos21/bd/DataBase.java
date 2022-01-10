package com.example.infoamigos21.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.infoamigos21.util.Producto;
import com.example.infoamigos21.util.Util;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class DataBase extends SQLiteOpenHelper {

    private static final int VERSION=1;

    public DataBase(Context contexto){
        super(contexto, Constantes.BASE_DATOS,null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Constantes.TABLA_PRODUCTOS +" ("+_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constantes.NOMBRE+" TEXT, "+
                Constantes.PRECIO + " REAL, "+
                Constantes.FOTO+ " BLOB) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_PRODUCTOS);
        onCreate(db);
    }

    public void addProducto(Producto producto){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Constantes.NOMBRE, producto.getNombreProducto());
        values.put(Constantes.PRECIO, producto.getPrecio());
        values.put(Constantes.FOTO, Util.getBytes(producto.getFoto()));
        db.insertOrThrow(Constantes.TABLA_PRODUCTOS,null,values);
        db.close();
    }

    public void eliminarProducto(Producto producto){
        SQLiteDatabase db=getWritableDatabase();
        String[] argumentos=new String[]{String.valueOf(producto.getId())};
        db.delete(Constantes.TABLA_PRODUCTOS, "_id=?", argumentos);
        db.close();
    }


    public ArrayList<Producto> getProductos(){
        ArrayList<Producto> listaProductos =new ArrayList<Producto>();
        Producto producto =null;

        final String[] SELECT={_ID,Constantes.NOMBRE,Constantes.PRECIO, Constantes.FOTO};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Constantes.TABLA_PRODUCTOS,SELECT,null, null, null,null,Constantes.NOMBRE);

        while(cursor.moveToNext()){
            producto =new Producto();
            producto.setId(cursor.getLong(0));
            producto.setNombreProducto(cursor.getString(1));
            producto.setPrecio(cursor.getFloat(2));
            producto.setFoto(Util.getBitmap(cursor.getBlob(3)));
            listaProductos.add(producto);

        }
        cursor.close();
        db.close();
        return listaProductos;

    }

    public ArrayList<Producto> getProductosD(){
        ArrayList<Producto> listaProductos =new ArrayList<Producto>();
        Producto producto =null;

        final String[] SELECT={_ID,Constantes.NOMBRE,Constantes.PRECIO, Constantes.FOTO};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Constantes.TABLA_PRODUCTOS,SELECT,null, null, null,null,Constantes.PRECIO);

        while(cursor.moveToNext()){
            producto =new Producto();
            producto.setId(cursor.getLong(0));
            producto.setNombreProducto(cursor.getString(1));
            producto.setPrecio(cursor.getFloat(2));
            producto.setFoto(Util.getBitmap(cursor.getBlob(3)));
            listaProductos.add(producto);

        }
        cursor.close();
        db.close();
        return listaProductos;

    }

    public ArrayList<Producto> getPrecios(float deuda){
        ArrayList<Producto> listaProductos =new ArrayList<Producto>();
        Producto producto =null;

        final String[] SELECT={_ID,Constantes.NOMBRE,Constantes.PRECIO, Constantes.FOTO};
        SQLiteDatabase db=getReadableDatabase();
        String[] args={""+deuda};
        Cursor cursor=db.query(Constantes.TABLA_PRODUCTOS,SELECT,Constantes.PRECIO +" > ?", args, null,null,Constantes.NOMBRE);

        while(cursor.moveToNext()){
            producto =new Producto();
            producto.setId(cursor.getLong(0));
            producto.setNombreProducto(cursor.getString(1));
            producto.setPrecio(cursor.getFloat(2));
            producto.setFoto(Util.getBitmap(cursor.getBlob(3)));
            listaProductos.add(producto);

        }
        cursor.close();
        db.close();
        return listaProductos;

    }
}
