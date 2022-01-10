package com.example.util_idades.suscripciones;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.util_idades.R;
import com.example.util_idades.suscripciones.bbdd.BaseDatosSuscripciones;
import com.example.util_idades.suscripciones.util.Suscripcion;
import com.example.util_idades.suscripciones.util.SuscripcionAdapter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SusActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    SharedPreferences prefs;
    Button anyadir;
    TextView tiempo, username;
    BaseDatosSuscripciones db;
    ArrayList<Suscripcion> listaSuscripciones;
    ListView lvLista;
    Suscripcion suscripcionSeleccionado;
    SuscripcionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_sus);
        db = new BaseDatosSuscripciones(this);
        anyadir = findViewById(R.id.bMainAnyadirsus);
        anyadir.setOnClickListener(this);

        listaSuscripciones = new ArrayList<Suscripcion>();
        tiempo=findViewById(R.id.txtTiempoRestante);
        lvLista = findViewById(R.id.lvMainsus);
        username = findViewById(R.id.tvUsuario);

        lvLista.setOnItemClickListener(this);
        lvLista.setOnItemLongClickListener(this);

        registerForContextMenu(lvLista);

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("showUsername",false)){
            username.setVisibility(View.VISIBLE);
            username.setText("Usuario: "+prefs.getString("username","usuario"));
        }else {
            username.setVisibility(View.INVISIBLE);
        }

        listaSuscripciones = db.getSuscripciones();
        adapter = new SuscripcionAdapter(this, listaSuscripciones);
        lvLista.setAdapter(adapter);



    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.lvMainsus:
                getMenuInflater().inflate(R.menu.menu_contextualsus, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        suscripcionSeleccionado=listaSuscripciones.get(position);
        return false;
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_borrar:
                db.eliminarSuscripcion(suscripcionSeleccionado);
                suscripcionSeleccionado = null;
                onResume();
                break;
            case R.id.add1m:
                suscripcionSeleccionado.setCaducidad(suscripcionSeleccionado.getCaducidad().plusMonths(1));
                db.eliminarSuscripcion(suscripcionSeleccionado);
                db.addSuscripcion(suscripcionSeleccionado);
                break;
            case R.id.add3m:
                suscripcionSeleccionado.setCaducidad(suscripcionSeleccionado.getCaducidad().plusMonths(3));
                db.eliminarSuscripcion(suscripcionSeleccionado);
                db.addSuscripcion(suscripcionSeleccionado);
                break;
            case R.id.add1a:
                suscripcionSeleccionado.setCaducidad(suscripcionSeleccionado.getCaducidad().plusYears(1));
                db.eliminarSuscripcion(suscripcionSeleccionado);
                db.addSuscripcion(suscripcionSeleccionado);
                break;
        }

        return super.onContextItemSelected(item);
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemLongClick(parent, view, position, id);

        suscripcionSeleccionado = listaSuscripciones.get(position);

        long time[] = getTime(LocalDateTime.now(),suscripcionSeleccionado.getCaducidad());

        tiempo.setText("Caduca en: ");

        if (time[4]<0) {
            tiempo.setText("Suscripcion agotada");
        }
        if (time[0]!=0){
            tiempo.setText(tiempo.getText().toString()+time[0]+"a ");
        }
        if (time[1]!=0){
            tiempo.setText(tiempo.getText().toString()+time[1]+"m ");
        }
        if (time[2]!=0){
            tiempo.setText(tiempo.getText().toString()+time[2]+"d ");
        }
        if (time[3]!=0){
            tiempo.setText(tiempo.getText().toString()+time[3]+"h ");
        }
        if (time[4]!=0){
            tiempo.setText(tiempo.getText().toString()+time[4]+"m ");
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bMainAnyadirsus:
                Intent i = new Intent(this, AddSusActivity.class);
                startActivity(i);
                break;
        }

    }


    private static long[] getTime(LocalDateTime tempDateTime, LocalDateTime toDateTime) {

        long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );

        return new long[]{years,months,days,hours,minutes};

    }


}