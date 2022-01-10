package com.example.infoamigos21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infoamigos21.bd.DataBase;
import com.example.infoamigos21.util.Producto;
import com.example.infoamigos21.util.ProductoAdapter;

import java.util.ArrayList;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
Button anyadir;
Button bPrecio;
Button bDetalle;
DataBase db;
ArrayList<Producto> listaProductos;
ListView lvLista;
Producto productoSeleccionado;
TextView info;
ProductoAdapter adapter;
SharedPreferences prefs;
EditText tDeuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);
        info=findViewById(R.id.tMainInfo);
        db=new DataBase(this);

        tDeuda=findViewById(R.id.tMainDeuda);

        anyadir=findViewById(R.id.bMainAnyadir);
        anyadir.setOnClickListener(this);
        bPrecio =findViewById(R.id.bMainDeuda);
        bPrecio.setOnClickListener(this);
        bDetalle=findViewById(R.id.bMainDetalle);
        bDetalle.setOnClickListener(this);

        listaProductos =new ArrayList<Producto>();


        lvLista=findViewById(R.id.lvMain);


        lvLista.setOnItemLongClickListener(this);
        lvLista.setOnItemClickListener(this);
        registerForContextMenu(lvLista);


    }




    @Override
    protected void onResume() {
        super.onResume();
        if(prefs.getBoolean("morosos", false)){
            listaProductos =db.getProductosD();
        }
        else{
        listaProductos =db.getProductos();}
        adapter=new ProductoAdapter(this, listaProductos);
        lvLista.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch(v.getId()){
            case R.id.lvMain:
        getMenuInflater().inflate(R.menu.menu_contextual,menu);
        break;}
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bMainAnyadir:
                Intent i=new Intent(this, AddActivity.class);
                startActivity(i);
                break;
            case R.id.bMainDeuda:
                Float deuda=Float.parseFloat(tDeuda.getText().toString());
                listaProductos =db.getPrecios(deuda);
        adapter=new ProductoAdapter(this, listaProductos);
        lvLista.setAdapter(adapter);
                break;
            case R.id.bMainDetalle:
                Intent intent=new Intent(this, DetailActivity.class);
                productoSeleccionado.putExtraAmigo(intent);
                startActivity(intent);


        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        productoSeleccionado = listaProductos.get(position);
        info.setText(productoSeleccionado.getId()+ productoSeleccionado.getNombreProducto());

        return false;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       switch(item.getItemId()){
           case R.id.menu_borrar:
               db.eliminarProducto(productoSeleccionado);
              onResume();
               break;
           case R.id.menu_detalle:
               break;
       }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_configuracion:
                    Intent i=new Intent(this, PreferencesActivity.class);
                    startActivity(i);
                break;
                case R.id.menu_about:
                    Toast.makeText(this,"Aplicación desarrollada por Jaime Pardo",Toast.LENGTH_LONG).show();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemLongClick(parent, view,position, id);
    }
}