package com.example.infoamigos21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
TextView tNombre, tEmail, tMovil, tDeuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i=getIntent();
        tNombre=findViewById(R.id.tDetailNombre);
        tEmail=findViewById(R.id.tDetailEmail);
        tMovil=findViewById(R.id.tDetailMovil);
        tDeuda=findViewById(R.id.tDetailDeuda);
        tNombre.setText(i.getStringExtra("NOMBRE"));
        tEmail.setText(i.getStringExtra("EMAIL"));
        tMovil.setText(i.getStringExtra("MOVIL"));
        tDeuda.setText(""+i.getFloatExtra("DEUDA",0));

    }
}