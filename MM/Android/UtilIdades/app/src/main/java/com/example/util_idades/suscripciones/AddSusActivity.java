package com.example.util_idades.suscripciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.util_idades.R;
import com.example.util_idades.suscripciones.bbdd.BaseDatosSuscripciones;
import com.example.util_idades.suscripciones.util.Suscripcion;

import java.time.LocalDateTime;

public class AddSusActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView iv;
    Button botonAnyadir;
    TextView tSuscripcion;
    RadioButton r1,r2,r3;
    LocalDateTime fechaactual, fechasuscripcion;
    BaseDatosSuscripciones db;
    final int CARGAIMAGEN=42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sus);

        db=new BaseDatosSuscripciones(this);
        fechaactual= LocalDateTime.now();

        iv=findViewById(R.id.iAddsus);
        iv.setOnClickListener(this);
        botonAnyadir=findViewById(R.id.bAddOksus);
        botonAnyadir.setOnClickListener(this);
        r1=findViewById(R.id.radioButton1);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton3);
        tSuscripcion=findViewById(R.id.tAddNombresus);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iAddsus:
                Intent intent=new Intent(this, ImageSusActivity.class);
                startActivityForResult(intent,CARGAIMAGEN);
                break;
            case R.id.bAddOksus:
                Suscripcion suscripcion =new Suscripcion();
                suscripcion.setNombreSuscripcion(tSuscripcion.getText().toString());
                if (r1.isChecked()){
                    suscripcion.setCaducidad(fechaactual.plusMonths(1));
                }else if (r2.isChecked()){
                    suscripcion.setCaducidad(fechaactual.plusMonths(3));
                }else if (r3.isChecked()){
                    suscripcion.setCaducidad(fechaactual.plusYears(1));
                }

                suscripcion.setFoto(((BitmapDrawable)iv.getDrawable()).getBitmap());
                db.addSuscripcion(suscripcion);
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==CARGAIMAGEN && data!=null){
            int resultado=data.getExtras().getInt("Imagen_seleccionada");
            switch(resultado){
                case 1:
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.iv1sus));
                    tSuscripcion.setText("NETFLIX");
                    break;
                case 2:iv.setImageDrawable(getResources().getDrawable(R.drawable.iv2sus));
                    tSuscripcion.setText("PRIME VIDEO");
                    break;
                case 3: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv3sus));
                    tSuscripcion.setText("HBO");
                    break;
                case 4: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv4sus));
                    tSuscripcion.setText("DAZN");
                    break;
                case 5: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv5sus));
                    tSuscripcion.setText("SPOTIFY");
                    break;
                case 6: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv6sus));
                    tSuscripcion.setText("APPLE MUSIC");
                    break;
                case 7: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv7sus));
                    tSuscripcion.setText("PLAYSTATION");
                    break;
                case 8: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv8sus));
                    tSuscripcion.setText("XBOX ");
                    break;
            }
        }
    }
}