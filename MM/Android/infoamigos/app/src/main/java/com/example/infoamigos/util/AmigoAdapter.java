package com.example.infoamigos.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.infoamigos.R;

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
    public Object getItem(int i) {
        return listaAmigos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaAmigos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view==null){
            view=inflater.inflate(R.layout.fila,null);
            holder=new ViewHolder();
            holder.foto=view.findViewById(R.id.iFila);
            holder.nombre=view.findViewById(R.id.tFilaNombre);
            holder.fijo=view.findViewById(R.id.tFilaTlf);
            holder.movil=view.findViewById(R.id.tFilaMovil);
            view.setTag(holder);
        } else {
            holder= (ViewHolder) view.getTag();
        }
        Amigo amigo= listaAmigos.get(i);
        holder.foto.setImageBitmap(amigo.getFoto());
        holder.nombre.setText(amigo.getNombreApellidos());
        holder.fijo.setText(amigo.getTlf());
        holder.movil.setText(amigo.getTlfMovil());

        return view;
    }
}
