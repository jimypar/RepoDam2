package com.example.infoamigos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.infoamigos.bd.Database;
import com.example.infoamigos.util.Amigo;

import java.util.zip.Inflater;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
ImageView iv;
final int CARGA_IMAGEN = 42;
private Database db;
Button botonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        iv = findViewById(R.id.iAdd);
        iv.setOnClickListener(this);
        db = new Database(this);

        botonAdd = findViewById(R.id.bAddOk);
        botonAdd.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.iAdd):
                Intent intent = new Intent(this, ImageActivity.class);
                startActivityForResult(intent, CARGA_IMAGEN);
                break;

            case (R.id.bAddOk):
                Amigo amigo = new Amigo();
                EditText editNombre=findViewById(R.id.tAddNombre);
                amigo.setNombreApellidos(editNombre.getText().toString());
                EditText editTlf=findViewById(R.id.tAddEmail);
                amigo.setTlf(editTlf.getText().toString());
                EditText editTlfMovil=findViewById(R.id.tAddMovil);
                amigo.setTlfMovil(editTlfMovil.getText().toString());
                EditText editDeuda = findViewById(R.id.tAddDeuda);
                amigo.setDeudas(Float.valueOf(editDeuda.getText().toString()));
                amigo.setFoto(((BitmapDrawable)iv.getDrawable()).getBitmap());
                db.nuevoAmigo(amigo);
                finish();
                break;


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK && requestCode==CARGA_IMAGEN && data!=null){
            int resultado = data.getExtras().getInt("Imagen_selecionada");
            switch (resultado){
                case 1: iv.setImageDrawable(getResources().getDrawable(R.drawable.d));
                break;
                case 2: iv.setImageDrawable(getResources().getDrawable(R.drawable.d1));
                    break;
                case 3: iv.setImageDrawable(getResources().getDrawable(R.drawable.d2));
                    break;
                case 4: iv.setImageDrawable(getResources().getDrawable(R.drawable.d3));
                    break;
                case 5: iv.setImageDrawable(getResources().getDrawable(R.drawable.d4));
                    break;
                case 6: iv.setImageDrawable(getResources().getDrawable(R.drawable.d5));
                    break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {



        return false;
    }
}