package com.example.util_idades;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Conecta4Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton b00,b01,b02,b03,b04,b05,b06,b10,b11,b12,b13,b14,b15,b16,b20,b21,b22,b23,b24,b25,b26,b30,b31,b32,b33,b34,b35,b36,b40,b41,b42,b43,b44,b45,b46,b50,b51,b52,b53,b54,b55,b56,b60,b61,b62,b63,b64,b65,b66;
    private ImageButton[][] tablero = new ImageButton[6][7];
    private int jugador = 1;
    private TextView tGanador;
    private int turnos = 0;
    private boolean repetir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_conecta4);

        tGanador = findViewById(R.id.tWinner);
        tGanador.setVisibility(View.INVISIBLE);
        tGanador.setOnClickListener(this);

        iniciarBotones();

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.bA1:
            case R.id.bB1:
            case R.id.bC1:
            case R.id.bD1:
            case R.id.bE1:
            case R.id.bF1:
                setFila(0,jugador);
                break;
            case R.id.bA2:
            case R.id.bB2:
            case R.id.bC2:
            case R.id.bD2:
            case R.id.bE2:
            case R.id.bF2:
                setFila(1,jugador);
                break;
            case R.id.bA3:
            case R.id.bB3:
            case R.id.bC3:
            case R.id.bD3:
            case R.id.bE3:
            case R.id.bF3:
                setFila(2,jugador);
                break;
            case R.id.bA4:
            case R.id.bB4:
            case R.id.bC4:
            case R.id.bD4:
            case R.id.bE4:
            case R.id.bF4:
                setFila(3,jugador);
                break;
            case R.id.bA5:
            case R.id.bB5:
            case R.id.bC5:
            case R.id.bD5:
            case R.id.bE5:
            case R.id.bF5:
                setFila(4,jugador);
                break;
            case R.id.bA6:
            case R.id.bB6:
            case R.id.bC6:
            case R.id.bD6:
            case R.id.bE6:
            case R.id.bF6:
                setFila(5,jugador);
                break;
            case R.id.bA7:
            case R.id.bB7:
            case R.id.bC7:
            case R.id.bD7:
            case R.id.bE7:
            case R.id.bF7:
                setFila(6,jugador);
                break;

            case R.id.tWinner:
                Intent i = new Intent();
                i = new Intent(this, MainActivity.class);
                startActivity(i);

        }

    }

    private void setFila(int fila, int jugador) {

        String color = "greydot";
        if (jugador==1){
            color = "bluedot";
        }
        if (jugador==2){
            color = "reddot";
        }

        if (tablero[5][fila].getTag().equals(1) || tablero[5][fila].getTag().equals(2)){
            if (tablero[4][fila].getTag().equals(1) || tablero[4][fila].getTag().equals(2)){
                if (tablero[3][fila].getTag().equals(1)  || tablero[3][fila].getTag().equals(2)){
                    if (tablero[2][fila].getTag().equals(1)  || tablero[2][fila].getTag().equals(2)){
                        if (tablero[1][fila].getTag().equals(1)  || tablero[1][fila].getTag().equals(2)){
                            if (tablero[0][fila].getTag().equals(1)  || tablero[0][fila].getTag().equals(2)){
                                repetir = true;
                            }else{
                                tablero[0][fila].setTag(jugador);
                                tablero[0][fila].setImageResource(getResources().getIdentifier(color, "drawable", getPackageName()));
                                ganador(jugador);
                                repetir = false;
                            }
                        }else{
                            tablero[1][fila].setTag(jugador);
                            tablero[1][fila].setImageResource(getResources().getIdentifier(color, "drawable", getPackageName()));
                            ganador(jugador);
                            repetir = false;
                        }
                    }else{
                        tablero[2][fila].setTag(jugador);
                        tablero[2][fila].setImageResource(getResources().getIdentifier(color, "drawable", getPackageName()));
                        ganador(jugador);
                        repetir = false;
                    }
                }else{
                    tablero[3][fila].setTag(jugador);
                    tablero[3][fila].setImageResource(getResources().getIdentifier(color, "drawable", getPackageName()));
                    ganador(jugador);
                    repetir = false;
                }
            }else{
                tablero[4][fila].setTag(jugador);
                tablero[4][fila].setImageResource(getResources().getIdentifier(color, "drawable", getPackageName()));
                ganador(jugador);
                repetir = false;
            }
        }else{
            tablero[5][fila].setTag(jugador);
            tablero[5][fila].setImageResource(getResources().getIdentifier(color, "drawable", getPackageName()));
            ganador(jugador);
            repetir = false;
        }

        if (!repetir){
            if (jugador==1){
                this.jugador = 2;
            }
            if (jugador==2){
                this.jugador = 1;
            }
            turnos++;
        }


    }

    private void ganador(int jugador) {

        if (comprobar(this.jugador,tablero)){
            if (jugador==1){
                tGanador.setText(R.string.blueWins);
                tGanador.setVisibility(View.VISIBLE);
            }
            if (jugador==2){
                tGanador.setText(R.string.redWins);
                tGanador.setVisibility(View.VISIBLE);
            }
        }
        if (turnos == 41){
            tGanador.setText(R.string.tied);
            tGanador.setVisibility(View.VISIBLE);
        }

    }

    public static boolean comprobar(int jugador, ImageButton[][] tablero){
        //check for 4 across
        for(int x = 0; x<tablero.length; x++){
            for (int y = 0;y < tablero[0].length - 3;y++){
                if (tablero[x][y].getTag().equals(jugador)&&
                        tablero[x][y+1].getTag().equals(jugador) &&
                        tablero[x][y+2].getTag().equals(jugador) &&
                        tablero[x][y+3].getTag().equals(jugador)){
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int x = 0; x < tablero.length - 3; x++){
            for(int y = 0; y < tablero[0].length; y++){
                if (tablero[x][y].getTag().equals(jugador)   &&
                        tablero[x+1][y].getTag().equals(jugador) &&
                        tablero[x+2][y].getTag().equals(jugador) &&
                        tablero[x+3][y].getTag().equals(jugador)){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int x = 3; x < tablero.length; x++){
            for(int y = 0; y < tablero[0].length - 3; y++){
                if (tablero[x][y].getTag().equals(jugador)   &&
                        tablero[x-1][y+1].getTag().equals(jugador) &&
                        tablero[x-2][y+2].getTag().equals(jugador) &&
                        tablero[x-3][y+3].getTag().equals(jugador)){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int x = 0; x < tablero.length - 3; x++){
            for(int y = 0; y < tablero[0].length - 3; y++){
                if ( tablero[x][y].getTag().equals(jugador)   &&
                        tablero[x+1][y+1].getTag().equals(jugador) &&
                        tablero[x+2][y+2].getTag().equals(jugador)  &&
                        tablero[x+3][y+3].getTag().equals(jugador) ){
                    return true;
                }
            }
        }
        return false;
    }

    private void iniciarBotones() {

        b00 = findViewById(R.id.bA1);
        b01 = findViewById(R.id.bA2);
        b02 = findViewById(R.id.bA3);
        b03 = findViewById(R.id.bA4);
        b04 = findViewById(R.id.bA5);
        b05 = findViewById(R.id.bA6);
        b06 = findViewById(R.id.bA7);

        b00.setOnClickListener(this);
        b01.setOnClickListener(this);
        b02.setOnClickListener(this);
        b03.setOnClickListener(this);
        b04.setOnClickListener(this);
        b05.setOnClickListener(this);
        b06.setOnClickListener(this);

        b10 = findViewById(R.id.bB1);
        b11 = findViewById(R.id.bB2);
        b12 = findViewById(R.id.bB3);
        b13 = findViewById(R.id.bB4);
        b14 = findViewById(R.id.bB5);
        b15 = findViewById(R.id.bB6);
        b16 = findViewById(R.id.bB7);

        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);


        b20 = findViewById(R.id.bC1);
        b21 = findViewById(R.id.bC2);
        b22 = findViewById(R.id.bC3);
        b23 = findViewById(R.id.bC4);
        b24 = findViewById(R.id.bC5);
        b25 = findViewById(R.id.bC6);
        b26 = findViewById(R.id.bC7);

        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b24.setOnClickListener(this);
        b25.setOnClickListener(this);
        b26.setOnClickListener(this);

        b30 = findViewById(R.id.bD1);
        b31 = findViewById(R.id.bD2);
        b32 = findViewById(R.id.bD3);
        b33 = findViewById(R.id.bD4);
        b34 = findViewById(R.id.bD5);
        b35 = findViewById(R.id.bD6);
        b36 = findViewById(R.id.bD7);

        b30.setOnClickListener(this);
        b31.setOnClickListener(this);
        b32.setOnClickListener(this);
        b33.setOnClickListener(this);
        b34.setOnClickListener(this);
        b35.setOnClickListener(this);
        b36.setOnClickListener(this);

        b40 = findViewById(R.id.bE1);
        b41 = findViewById(R.id.bE2);
        b42 = findViewById(R.id.bE3);
        b43 = findViewById(R.id.bE4);
        b44 = findViewById(R.id.bE5);
        b45 = findViewById(R.id.bE6);
        b46 = findViewById(R.id.bE7);

        b40.setOnClickListener(this);
        b41.setOnClickListener(this);
        b42.setOnClickListener(this);
        b43.setOnClickListener(this);
        b44.setOnClickListener(this);
        b45.setOnClickListener(this);
        b46.setOnClickListener(this);

        b50 = findViewById(R.id.bF1);
        b51 = findViewById(R.id.bF2);
        b52 = findViewById(R.id.bF3);
        b53 = findViewById(R.id.bF4);
        b54 = findViewById(R.id.bF5);
        b55 = findViewById(R.id.bF6);
        b56 = findViewById(R.id.bF7);

        b50.setOnClickListener(this);
        b51.setOnClickListener(this);
        b52.setOnClickListener(this);
        b53.setOnClickListener(this);
        b54.setOnClickListener(this);
        b55.setOnClickListener(this);
        b56.setOnClickListener(this);

        tablero[0][0]=b00;
        tablero[0][1]=b01;
        tablero[0][2]=b02;
        tablero[0][3]=b03;
        tablero[0][4]=b04;
        tablero[0][5]=b05;
        tablero[0][6]=b06;
        tablero[1][0]=b10;
        tablero[1][1]=b11;
        tablero[1][2]=b12;
        tablero[1][3]=b13;
        tablero[1][4]=b14;
        tablero[1][5]=b15;
        tablero[1][6]=b16;
        tablero[2][0]=b20;
        tablero[2][1]=b21;
        tablero[2][2]=b22;
        tablero[2][3]=b23;
        tablero[2][4]=b24;
        tablero[2][5]=b25;
        tablero[2][6]=b26;
        tablero[3][0]=b30;
        tablero[3][1]=b31;
        tablero[3][2]=b32;
        tablero[3][3]=b33;
        tablero[3][4]=b34;
        tablero[3][5]=b35;
        tablero[3][6]=b36;
        tablero[4][0]=b40;
        tablero[4][1]=b41;
        tablero[4][2]=b42;
        tablero[4][3]=b43;
        tablero[4][4]=b44;
        tablero[4][5]=b45;
        tablero[4][6]=b46;
        tablero[5][0]=b50;
        tablero[5][1]=b51;
        tablero[5][2]=b52;
        tablero[5][3]=b53;
        tablero[5][4]=b54;
        tablero[5][5]=b55;
        tablero[5][6]=b56;

    }


}