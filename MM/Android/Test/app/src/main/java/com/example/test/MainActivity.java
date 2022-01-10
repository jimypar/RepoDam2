package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button botonPrimero;
Button botonEmail;
Button botonSiguiente;
TextView etiqueta;
EditText email;
private final int ELEGIR_NUMERO=45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonPrimero=findViewById(R.id.bMainBotonPrimero);
        botonEmail=findViewById(R.id.bMainEmail);
        botonSiguiente=findViewById(R.id.bMainSiguiente);
        etiqueta=findViewById(R.id.tMainEtiqueta);
        email=findViewById(R.id.tMainEmail);
        botonPrimero.setOnClickListener(this);
        botonEmail.setOnClickListener(this);
        botonSiguiente.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    switch(v.getId()){
        case R.id.bMainBotonPrimero:
            etiqueta.setText("Has pulsado algo!");
            break;
        case R.id.bMainEmail:
            etiqueta.setText(email.getText());
            break;
        case R.id.bMainSiguiente:
            Intent intent=new Intent(this,SecondActivity.class);
            startActivityForResult(intent,ELEGIR_NUMERO);
            break;
    }

    }


    /*@Override
    protected void onResume() {
        super.onResume();
        etiqueta.setText("Has vuelto!");

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        etiqueta.setText("tooonto");
        if(requestCode==ELEGIR_NUMERO && resultCode==RESULT_OK && data!=null){
            float numero=data.getExtras().getFloat("numeroElegido");
            etiqueta.setText(Float.toString(numero));
        }

    }
}
