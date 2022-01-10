package com.example.util_idades.listacompra.bbdd;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.util_idades.listacompra.util.Producto;
import com.example.util_idades.listacompra.util.Util;

import java.util.ArrayList;

public class BaseDatosProductos extends SQLiteOpenHelper {

    private static final int VERSION=3;

    public BaseDatosProductos(Context contexto){
        super(contexto, Constantes.BASE_DATOS,null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Constantes.TABLA_PRODUCTOS +" ("+_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constantes.NOMBRE+" TEXT, "+
                Constantes.CANTIDAD + " REAL, "+
                Constantes.COMPRADO + " REAL, "+
                Constantes.FOTO+ " BLOB) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_PRODUCTOS);
        onCreate(sqLiteDatabase);
    }

    public void addProducto(Producto producto){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Constantes.NOMBRE, producto.getNombreProducto());
        values.put(Constantes.CANTIDAD, producto.getCantidad());
        values.put(Constantes.COMPRADO, producto.getComprado());
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

    public void actualizarProducto(Producto producto){
        eliminarProducto(producto);
        addProducto(producto);
    }

    public ArrayList<Producto> getProductos(){
        ArrayList<Producto> listaProductos =new ArrayList<Producto>();
        Producto producto =null;

        final String[] SELECT={_ID,Constantes.NOMBRE,Constantes.CANTIDAD,Constantes.COMPRADO, Constantes.FOTO};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Constantes.TABLA_PRODUCTOS,SELECT,null, null, null,null,Constantes.COMPRADO);

        while(cursor.moveToNext()){
            producto =new Producto();
            producto.setId(cursor.getLong(0));
            producto.setNombreProducto(cursor.getString(1));
            producto.setCantidad(cursor.getInt(2));
            producto.setComprado(cursor.getInt(3));
            producto.setFoto(Util.getBitmap(cursor.getBlob(4)));
            listaProductos.add(producto);

        }
        cursor.close();
        db.close();
        return listaProductos;

    }


}
