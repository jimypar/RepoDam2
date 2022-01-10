package com.example.util_idades;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.util_idades.listacompra.ListaActivity;
import com.example.util_idades.suscripciones.SusActivity;
import com.example.util_idades.wifizgz.MapaActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button calculator,conversor,conecta4,listaCompra,suscripciones,mapa;
    SharedPreferences prefs;
    Boolean usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main);

        calculator = findViewById(R.id.bMain1);
        calculator.setOnClickListener(this);
        conversor = findViewById(R.id.bMain2);
        conversor.setOnClickListener(this);
        listaCompra = findViewById(R.id.bMain3);
        listaCompra.setOnClickListener(this);
        conecta4 = findViewById(R.id.bMain4);
        conecta4.setOnClickListener(this);
        suscripciones = findViewById(R.id.bMain5);
        suscripciones.setOnClickListener(this);
        mapa = findViewById(R.id.bMain6);
        mapa.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("switchModo",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        if (prefs.getBoolean("showUsername",false)){
            usuario=false;
        }else {
            prefs.getString("username","usuario");
            usuario=true;
        }


    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent();

        switch (view.getId()){

            case(R.id.bMain1):
                i = new Intent(this, CalculadoraActivity.class);
                startActivity(i);
                break;

            case(R.id.bMain2):
                i = new Intent(this, ConversorActivity.class);
                startActivity(i);
                break;

            case(R.id.bMain3):
                i = new Intent(this, ListaActivity.class);
                startActivity(i);
                break;

            case(R.id.bMain4):
                i = new Intent(this, Conecta4Activity.class);
                startActivity(i);
                break;

            case(R.id.bMain5):
                i = new Intent(this, SusActivity.class);
                startActivity(i);
                break;

            case(R.id.bMain6):
                i = new Intent(this, MapaActivity.class);
                startActivity(i);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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

}