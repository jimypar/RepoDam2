package com.example.infoamigos21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{
ImageView iv1, iv2, iv3, iv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        iv1=findViewById(R.id.ivImage1);
        iv2=findViewById(R.id.ivImage2);
        iv3=findViewById(R.id.ivImage3);
        iv4=findViewById(R.id.ivImage4);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i=1;
        switch(v.getId()){
            case R.id.ivImage1:
                i=1;
                break;
            case R.id.ivImage2: i=2;
            break;
            case R.id.ivImage3: i=3;
            break;
            case R.id.ivImage4: i=4;
            break;
        }
        Intent intent=getIntent();
        intent.putExtra("Imagen_seleccionada", i);
        this.setResult(RESULT_OK, intent);
        finish();

    }
}