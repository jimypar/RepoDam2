package com.example.util_idades.suscripciones.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.util_idades.R;

import java.util.ArrayList;

public class SuscripcionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Suscripcion> listaSuscripciones;
    private LayoutInflater inflater;

    static class ViewHolder{
        ImageView foto;
        TextView nombre;
    }

    public SuscripcionAdapter(Activity context, ArrayList<Suscripcion> listaSuscripciones){
        this.context=context;
        this.listaSuscripciones = listaSuscripciones;
        this.inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaSuscripciones.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSuscripciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.filasus,null);
            holder=new ViewHolder();
            holder.foto=convertView.findViewById(R.id.iFilasus);
            holder.nombre=convertView.findViewById(R.id.tFilaNombresus);
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        Suscripcion suscripcion = listaSuscripciones.get(position);

        holder.foto.setImageBitmap(suscripcion.getFoto());
        holder.nombre.setText(suscripcion.getNombreSuscripcion());
        return convertView;
    }
}
