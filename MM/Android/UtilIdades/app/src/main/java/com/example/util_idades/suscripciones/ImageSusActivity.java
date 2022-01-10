package com.example.util_idades.suscripciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.util_idades.R;

public class ImageSusActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7,iv8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sus);
        iv1=findViewById(R.id.ivImage1sus);
        iv2=findViewById(R.id.ivImage2sus);
        iv3=findViewById(R.id.ivImage3sus);
        iv4=findViewById(R.id.ivImage4sus);
        iv5=findViewById(R.id.ivImage5sus);
        iv6=findViewById(R.id.ivImage6sus);
        iv7=findViewById(R.id.ivImage7sus);
        iv8=findViewById(R.id.ivImage8sus);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i=1;
        switch(v.getId()){
            case R.id.ivImage1sus: i=1;
                break;
            case R.id.ivImage2sus: i=2;
                break;
            case R.id.ivImage3sus: i=3;
                break;
            case R.id.ivImage4sus: i=4;
                break;
            case R.id.ivImage5sus: i=5;
                break;
            case R.id.ivImage6sus: i=6;
                break;
            case R.id.ivImage7sus: i=7;
                break;
            case R.id.ivImage8sus: i=8;
                break;
        }
        Intent intent=getIntent();
        intent.putExtra("Imagen_seleccionada", i);
        this.setResult(RESULT_OK, intent);
        finish();

    }
}