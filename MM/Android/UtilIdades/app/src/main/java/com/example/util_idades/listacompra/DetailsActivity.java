package com.example.util_idades.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.util_idades.R;

public class DetailsActivity extends AppCompatActivity {
    TextView tNombre, tCantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i=getIntent();
        tNombre=findViewById(R.id.tDetailNombre);
        tCantidad =findViewById(R.id.tDetailCantidad);
        tNombre.setText(i.getStringExtra("NOMBRE"));
        tCantidad.setText(""+i.getIntExtra("CANTIDAD",0));

    }
}