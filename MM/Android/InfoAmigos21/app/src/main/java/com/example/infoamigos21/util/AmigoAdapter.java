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

public class AmigoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Amigo> listaAmigos;
    private LayoutInflater inflater;

    static class ViewHolder{
        ImageView foto;
        TextView nombre;
        TextView fijo;
        TextView movil;
    }



    public AmigoAdapter(Activity context, ArrayList<Amigo> listaAmigos){
        this.context=context;
        this.listaAmigos=listaAmigos;
        this.inflater=LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return listaAmigos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAmigos.get(position);
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
            holder.fijo=convertView.findViewById(R.id.tFilaTlf);
            holder.movil=convertView.findViewById(R.id.tFilaMovil);
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        Amigo amigo=listaAmigos.get(position);

        holder.foto.setImageBitmap(amigo.getFoto());
        holder.nombre.setText(amigo.getNombreApellidos());
        holder.fijo.setText(amigo.getTlf());
        holder.movil.setText(amigo.getTlfMovil());
        return convertView;
    }
}
