package com.example.infoamigos21.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infoamigos21.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> listaProductos;
    private LayoutInflater inflater;

    static class ViewHolder{
        ImageView foto;
        TextView nombre;
        TextView precio;
    }



    public ProductoAdapter(Activity context, ArrayList<Producto> listaProductos){
        this.context=context;
        this.listaProductos = listaProductos;
        this.inflater=LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.fila,null);
            holder=new ViewHolder();
            holder.foto=convertView.findViewById(R.id.iFila);
            holder.nombre=convertView.findViewById(R.id.tFilaNombre);
            holder.precio=convertView.findViewById(R.id.tFilaPrecio);
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        Producto producto = listaProductos.get(position);

        holder.foto.setImageBitmap(producto.getFoto());
        holder.nombre.setText(producto.getNombreProducto());
        holder.precio.setText(String.valueOf(producto.getPrecio()));
        return convertView;
    }
}
