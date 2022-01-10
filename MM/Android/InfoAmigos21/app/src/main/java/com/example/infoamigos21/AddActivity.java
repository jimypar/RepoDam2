package com.example.infoamigos21;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.infoamigos21.bd.DataBase;
import com.example.infoamigos21.util.Amigo;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
ImageView iv;
Button botonAnyadir;
DataBase db;
final int CARGAIMAGEN=42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db=new DataBase(this);

        iv=findViewById(R.id.iAdd);
        iv.setOnClickListener(this);
        botonAnyadir=findViewById(R.id.bAddOk);
        botonAnyadir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iAdd:
                Intent intent=new Intent(this,ImageActivity.class);
                startActivityForResult(intent,CARGAIMAGEN);
                break;
            case R.id.bAddOk:
                Amigo amigo=new Amigo();
                EditText editNombre=findViewById(R.id.tAddNombre);
                amigo.setNombreApellidos(editNombre.getText().toString());
                EditText editEmail=findViewById(R.id.tAddEmail);
                amigo.setEmail(editEmail.getText().toString());
                EditText editTlf=findViewById(R.id.tAddTlfn);
                amigo.setTlf(editTlf.getText().toString());
                EditText editMovil=findViewById(R.id.tAddMvl);
                amigo.setTlfMovil(editMovil.getText().toString());
                EditText editDeuda=findViewById(R.id.tAddDeuda);
                amigo.setDeudas(Float.valueOf(editDeuda.getText().toString()));

                amigo.setFoto(((BitmapDrawable)iv.getDrawable()).getBitmap());
                db.nuevoAmigo(amigo);
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
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.iv1));
                    break;
                case 2:iv.setImageDrawable(getResources().getDrawable(R.drawable.iv2));
                    break;
                case 3: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv3));
                    break;
                case 4: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv4));
                    break;


            }
        }
    }
}