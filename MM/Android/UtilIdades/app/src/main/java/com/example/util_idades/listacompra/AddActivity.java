package com.example.util_idades.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.util_idades.R;
import com.example.util_idades.listacompra.bbdd.BaseDatosProductos;
import com.example.util_idades.listacompra.util.Producto;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView iv;
    Button botonAnyadir;
    ImageButton bAdd, bRest;
    EditText tCant;
    BaseDatosProductos db;
    final int CARGAIMAGEN=42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db=new BaseDatosProductos(this);

        iv=findViewById(R.id.iAdd);
        iv.setOnClickListener(this);
        botonAnyadir=findViewById(R.id.bAddOk);
        botonAnyadir.setOnClickListener(this);
        bAdd=findViewById(R.id.bAdd);
        bRest=findViewById(R.id.bRest);
        tCant=findViewById(R.id.tAddCantidad);
        bAdd.setOnClickListener(this);
        bRest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iAdd:
                Intent intent=new Intent(this,ImagesActivity.class);
                startActivityForResult(intent,CARGAIMAGEN);
                break;
            case R.id.bAddOk:
                Producto producto =new Producto();
                EditText editNombre=findViewById(R.id.tAddNombre);
                producto.setNombreProducto(editNombre.getText().toString());
                EditText editCantidad=findViewById(R.id.tAddCantidad);
                producto.setCantidad(Integer.valueOf(editCantidad.getText().toString()));
                producto.setFoto(((BitmapDrawable)iv.getDrawable()).getBitmap());
                db.addProducto(producto);
                finish();
                break;
            case R.id.bAdd:
                tCant.setText(String.valueOf(Integer.parseInt(String.valueOf((tCant.getText())))+1));
                break;
            case R.id.bRest:
                if (Integer.parseInt(String.valueOf((tCant.getText())))!=1){
                    tCant.setText(String.valueOf(Integer.parseInt(String.valueOf((tCant.getText())))-1));
                }
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
                case 5: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv5));
                    break;
                case 6: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv6));
                    break;
                case 7: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv7));
                    break;
                case 8: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv8));
                    break;
                case 9: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv9));
                    break;
                case 10: iv.setImageDrawable(getResources().getDrawable(R.drawable.iv10));
                    break;


            }
        }
    }
}