package com.example.infoamigos.util;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.infoamigos.R;

public class DetailActivity extends AppCompatActivity {

TextView tNombre, tEmail, tFijo,tMovil, tDeuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i= getIntent();
        tNombre=findViewById(R.id.tNombreD);
        tEmail=findViewById(R.id.tEmailD);
        tFijo=findViewById(R.id.tFijoD);
        tMovil=findViewById(R.id.tMovilD);
        tDeuda=findViewById(R.id.tDeudaD);

        tNombre.setText(i.getStringExtra("NOMBRE"));
        tEmail.setText(i.getStringExtra("EMAIL"));
        tFijo.setText(i.getStringExtra("FIJO"));
        tMovil.setText(i.getStringExtra("MOVIL"));
        tDeuda.setText(""+i.getFloatExtra("DEUDA",0));


    }

}