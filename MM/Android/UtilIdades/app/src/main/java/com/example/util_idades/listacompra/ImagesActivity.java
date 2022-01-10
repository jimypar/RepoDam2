package com.example.util_idades.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.util_idades.R;

public class ImagesActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7,iv8, iv9, iv10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        iv1=findViewById(R.id.ivImage1);
        iv2=findViewById(R.id.ivImage2);
        iv3=findViewById(R.id.ivImage3);
        iv4=findViewById(R.id.ivImage4);
        iv5=findViewById(R.id.ivImage5);
        iv6=findViewById(R.id.ivImage6);
        iv7=findViewById(R.id.ivImage7);
        iv8=findViewById(R.id.ivImage8);
        iv9=findViewById(R.id.ivImage9);
        iv10=findViewById(R.id.ivImage10);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
        iv9.setOnClickListener(this);
        iv10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i=1;
        switch(v.getId()){
            case R.id.ivImage1: i=1;
                break;
            case R.id.ivImage2: i=2;
                break;
            case R.id.ivImage3: i=3;
                break;
            case R.id.ivImage4: i=4;
                break;
            case R.id.ivImage5: i=5;
                break;
            case R.id.ivImage6: i=6;
                break;
            case R.id.ivImage7: i=7;
                break;
            case R.id.ivImage8: i=8;
                break;
            case R.id.ivImage9: i=9;
                break;
            case R.id.ivImage10: i=10;
                break;
        }
        Intent intent=getIntent();
        intent.putExtra("Imagen_seleccionada", i);
        this.setResult(RESULT_OK, intent);
        finish();

    }
}