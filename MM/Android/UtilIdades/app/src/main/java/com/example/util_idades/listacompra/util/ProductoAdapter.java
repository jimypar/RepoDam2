package com.example.util_idades.listacompra.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.util_idades.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> listaProductos;
    private LayoutInflater inflater;

    static class ViewHolder{
        ImageView foto;
        TextView nombre;
        TextView cantidad;
        LinearLayout fondo;
    }

    public ProductoAdapter(Activity context, ArrayList<Producto> listaProductos){
        this.context=context;
        this.listaProductos = listaProductos;
        this.inflater= LayoutInflater.from(context);
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
            holder.cantidad =convertView.findViewById(R.id.tFilaCantidad);
            holder.fondo =convertView.findViewById(R.id.filaLayout);
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        Producto producto = listaProductos.get(position);

        holder.foto.setImageBitmap(producto.getFoto());
        holder.nombre.setText(producto.getNombreProducto());
        holder.cantidad.setText(String.valueOf(producto.getCantidad()));
        if (producto.getComprado()==1){
            holder.fondo.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.roundc));
        }else {
            holder.fondo.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.round));
        }
        return convertView;
    }
}
