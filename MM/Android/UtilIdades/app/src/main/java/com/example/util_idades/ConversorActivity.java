package com.example.util_idades;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;
import java.text.DecimalFormat;

public class ConversorActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

    private Button divisa1,divisa2,convertir;
    private EditText inputText;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        divisa1 = findViewById(R.id.bCash1);
        divisa2 = findViewById(R.id.bCash2);
        convertir = findViewById(R.id.bConvertir);

        inputText = findViewById(R.id.tInput1);
        outputText = findViewById(R.id.tOutput1);

        inputText.setOnKeyListener(this);

        divisa1.setOnClickListener(this);
        divisa2.setOnClickListener(this);
        convertir.setOnClickListener(this);
        divisa1.setTag(0);
        divisa2.setTag(0);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.bCash1):

                final int estadoDivisa1 = (Integer) view.getTag();
                switch (estadoDivisa1){
                    case 0:
                        divisa1.setText(R.string.dolares);
                        view.setTag(1);
                        break;
                    case 1:
                        divisa1.setText(R.string.libra);
                        view.setTag(2);
                        break;
                    case 2:
                        divisa1.setText(R.string.euros);
                        view.setTag(0);
                        break;
                }
                conversor();
                break;

            case (R.id.bCash2):

                final int estadoDivisa2 = (Integer) view.getTag();
                switch (estadoDivisa2){
                    case 0:
                        divisa2.setText(R.string.dolares);
                        view.setTag(1);
                        break;
                    case 1:
                        divisa2.setText(R.string.libra);
                        view.setTag(2);
                        break;
                    case 2:
                        divisa2.setText(R.string.euros);
                        view.setTag(0);
                        break;
                }
                conversor();
                break;

            case (R.id.bConvertir):

                conversor();

                break;



        }

    }

    private void conversor() {

        if (!inputText.getText().toString().equals("")){

            if (divisa1.getTag().equals(0)){
                if (divisa2.getTag().equals(0)){
                    outputText.setText(inputText.getText()+"€");
                }
                if (divisa2.getTag().equals(1)){
                    outputText.setText(convertir(inputText.getText(),1.16,"$"));
                }
                if (divisa2.getTag().equals(2)){
                    outputText.setText(convertir(inputText.getText(),0.85,"£"));
                }
            }

            if (divisa1.getTag().equals(1)){
                if (divisa2.getTag().equals(0)){
                    outputText.setText(convertir(inputText.getText(),0.86,"€"));
                }
                if (divisa2.getTag().equals(1)){
                    outputText.setText(inputText.getText()+"$");
                }
                if (divisa2.getTag().equals(2)){
                    //Error
                    outputText.setText(convertir(inputText.getText(),0.73,"£"));
                }
            }

            if (divisa1.getTag().equals(2)){
                if (divisa2.getTag().equals(0)){
                    outputText.setText(convertir(inputText.getText(),1.18,"€"));
                }
                if (divisa2.getTag().equals(1)){
                    //Error
                    outputText.setText(convertir(inputText.getText(),1.36,"$"));
                }
                if (divisa2.getTag().equals(2)){
                    outputText.setText(inputText.getText()+"£");
                }
            }


        }
    }

    private String convertir(Editable text, double v,String signo) {

        DecimalFormat df = new DecimalFormat("0.00");

        return String.valueOf(df.format(Integer.valueOf(String.valueOf(text))*v))+String.valueOf(signo);

    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        conversor();

        return false;
    }
}