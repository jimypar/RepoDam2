package com.example.infoamigos;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infoamigos.bd.Database;
import com.example.infoamigos.util.Amigo;
import com.example.infoamigos.util.AmigoAdapter;
import com.example.infoamigos.util.DetailActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    Button add,deuda,detalle;
    private Database db;
    ArrayList<Amigo> listaAmigos;
    ListView lvLista;
    Amigo amigoSeleccionado;
    TextView info;
    EditText tDeuda;
    AmigoAdapter adapter;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.bMainAdd);
        add.setOnClickListener(this);

        info = findViewById(R.id.tMainInfo);

        db = new Database(this);

        listaAmigos = new ArrayList<>();
        lvLista= findViewById(R.id.lvListMain);

        //lvLista.setAdapter(new ArrayAdapter<Amigo>(
             //                   this, android.R.layout.simple_list_item_1,listaAmigos));

        lvLista.setOnItemLongClickListener(this);
        lvLista.setOnItemClickListener(this);
        registerForContextMenu(lvLista);

        tDeuda = findViewById(R.id.tMainDeuda);
        deuda = findViewById(R.id.bDeudaMain);
        deuda.setOnClickListener(this);
        detalle = findViewById(R.id.bMainDetalle);
        detalle.setOnClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){

            case R.id.lvListMain:
                getMenuInflater().inflate(R.menu.menu_contextual,menu);
                break;
        }


        getMenuInflater().inflate(R.menu.menu_contextual,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bMainAdd:
                Intent i = new Intent(this, AddActivity.class);
                startActivity(i);
                break;
            case R.id.bDeudaMain:
                if (deuda.getText()!=null) {
                    Float deuda = Float.parseFloat(tDeuda.getText().toString());
                    listaAmigos = db.getDeudores(deuda);
                    adapter = new AmigoAdapter(this, listaAmigos);
                    lvLista.setAdapter(adapter);
                }
                break;
            case R.id.bMainDetalle:
                Intent intent = new Intent(this, DetailActivity.class);
                amigoSeleccionado.putExtraAmigo(intent);
                startActivity(intent);
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("morosos",false)){
            listaAmigos=db.getAmigos();
        }else{
            listaAmigos = db.getAmigos();
        }

        adapter = new AmigoAdapter(this,listaAmigos);
        lvLista.setAdapter(adapter);
        //listaAmigos=db.getAmigos();
        //lvLista.setAdapter(new ArrayAdapter<Amigo>(
        //        this, android.R.layout.simple_list_item_1,listaAmigos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        amigoSeleccionado = listaAmigos.get(i);
        info.setText(amigoSeleccionado.getId()+" - "+amigoSeleccionado.getNombreApellidos());


        return false;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_borrar:
                db.eliminarAmigo(amigoSeleccionado);
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
                Intent i = new Intent(this,PreferencesActivity.class);
                startActivity(i);
                break;
            case R.id.menu_about:
                Toast.makeText(this,"Aplicacion desarrollada por Jaime", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        onItemLongClick(adapterView,view,i,l);

    }
}