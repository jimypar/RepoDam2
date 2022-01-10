package com.example.util_idades.listacompra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.util_idades.R;
import com.example.util_idades.listacompra.bbdd.BaseDatosProductos;
import com.example.util_idades.listacompra.util.Producto;
import com.example.util_idades.listacompra.util.ProductoAdapter;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    Button anyadir,bDetalle;
    ImageButton bRecargar;
    BaseDatosProductos db;
    ArrayList<Producto> listaProductos;
    ListView lvLista;
    Producto productoSeleccionado;
    ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        db = new BaseDatosProductos(this);

        anyadir = findViewById(R.id.bMainAnyadir);
        anyadir.setOnClickListener(this);
        bDetalle = findViewById(R.id.bMainComprar);
        bDetalle.setOnClickListener(this);
        bRecargar = findViewById(R.id.bRecargar);
        bRecargar.setOnClickListener(this);

        listaProductos = new ArrayList<Producto>();


        lvLista = findViewById(R.id.lvMain);


        lvLista.setOnItemLongClickListener(this);
        lvLista.setOnItemClickListener(this);
        registerForContextMenu(lvLista);

    }


    @Override
    protected void onResume() {
        super.onResume();
        listaProductos = db.getProductos();
        adapter = new ProductoAdapter(this, listaProductos);
        lvLista.setAdapter(adapter);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.lvMain:
                getMenuInflater().inflate(R.menu.menu_contextual, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        productoSeleccionado = listaProductos.get(position);
        return false;
    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_borrar:
                db.eliminarProducto(productoSeleccionado);
                productoSeleccionado = null;
                onResume();
                break;
        }

        return super.onContextItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        productoSeleccionado = listaProductos.get(position);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bMainAnyadir:
                Intent i = new Intent(this, AddActivity.class);
                startActivity(i);
                break;
            case R.id.bMainComprar:
                if (productoSeleccionado != null) {
                    if (productoSeleccionado.getComprado()==0){
                        productoSeleccionado.setComprado(1);
                        db.actualizarProducto(productoSeleccionado);
                        onResume();
                    }else {
                        Context context = getApplicationContext();
                        Toast.makeText(context, "Ya has comprado esto", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "No hay un producto seleccionado", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bRecargar:
                for (Producto producto:listaProductos) {
                    producto.setComprado(0);
                    db.actualizarProducto(producto);
                }
                onResume();
                break;
        }

    }
}