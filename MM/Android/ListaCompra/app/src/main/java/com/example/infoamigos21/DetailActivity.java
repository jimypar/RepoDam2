package com.example.infoamigos21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
TextView tNombre, tPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i=getIntent();
        tNombre=findViewById(R.id.tDetailNombre);
        tPrecio =findViewById(R.id.tDetailDeuda);
        tNombre.setText(i.getStringExtra("NOMBRE"));
        tPrecio.setText(""+i.getFloatExtra("PRECIO",0));

    }
}