package com.example.util_idades;


import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usersInputBox;
    private ImageButton backspace;
    private TextView preResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        setupUI();

        usersInputBox.setText("");
    }

    @Override
    public void onClick(View view) {

        int pos;

        switch (view.getId()){

            case (R.id.backspace):
                //gets the cursors position
                int cursorPosEnd = usersInputBox.getSelectionEnd();
                int textLength = usersInputBox.getText().length();

                if (cursorPosEnd != 0 && textLength != 0){
                    SpannableStringBuilder selection = (SpannableStringBuilder) usersInputBox.getText();
                    selection.replace(cursorPosEnd - 1, cursorPosEnd, "");
                    //updates the text
                    usersInputBox.setText(selection);
                    //puts the cursor back in the correct spot
                    usersInputBox.setSelection(cursorPosEnd - 1);
                }
                break;

            case (R.id.zero):
                pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("0", usersInputBox.getText().toString(), pos));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.one):
                pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("1", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.two):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("2", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.three):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("3", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.four):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("4", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.five):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("5", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.six):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("6", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.seven):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("7", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.eight):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("8", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.nine):
                 pos = usersInputBox.getSelectionStart();
                usersInputBox.setText(updateText("9", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
                usersInputBox.setSelection(pos + 1);
                break;

            case (R.id.clear):
                usersInputBox.setText("");
                break;

           case (R.id.point):
                pos = usersInputBox.getSelectionStart();
               usersInputBox.setText(updateText(".", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
               usersInputBox.setSelection(pos + 1);
                break;

           case (R.id.equals):
               String expression = usersInputBox.getText().toString();

               expression = expression.replaceAll("÷", "/");
               expression = expression.replaceAll("×", "*");

               String resultado;

               try {

               resultado = calcular(expression);

                }catch (IndexOutOfBoundsException e) {
                   resultado = "Syntax Error";
               }catch (NumberFormatException e){
                   resultado = "";
               }

               usersInputBox.setText(resultado);
               usersInputBox.setSelection(resultado.length());
                break;

           case (R.id.divide):
                pos = usersInputBox.getSelectionStart();
               usersInputBox.setText(updateText("÷", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
               usersInputBox.setSelection(pos + 1);
                break;

           case (R.id.multiply):
                pos = usersInputBox.getSelectionStart();
               usersInputBox.setText(updateText("×", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
               usersInputBox.setSelection(pos + 1);
                break;

           case (R.id.minus):
                pos = usersInputBox.getSelectionStart();
               usersInputBox.setText(updateText("-", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
               usersInputBox.setSelection(pos + 1);
                break;

           case (R.id.add):
                pos = usersInputBox.getSelectionStart();
               usersInputBox.setText(updateText("+", usersInputBox.getText().toString(), usersInputBox.getSelectionStart()));
               usersInputBox.setSelection(pos + 1);
                break;



        }

        try {

            String expression = usersInputBox.getText().toString();

            expression = expression.replaceAll("÷", "/");
            expression = expression.replaceAll("×", "*");

            preResult.setText(calcular(expression));
            preResult.setTextColor(Color.GRAY);
            preResult.setTextSize(40);

        }catch (IndexOutOfBoundsException e){
            preResult.setText("");
        }catch (NumberFormatException e){
            preResult.setText("");
        }

    }

    private String calcular(String expression) {

        char[] tokens = expression.toCharArray();
        List<String> list = new ArrayList<>();

        String s = "";

        String operacion = "";
        String num1 = "";
        String num2 = "";

        boolean operacionPendiente = false;

        for (int i = 0; i < tokens.length; i++) {
            if (Character.isDigit(tokens[i]) || tokens[i]=='.') {
                s += Character.toString(tokens[i]);
            } else {
                list.add(s);
                list.add(Character.toString(tokens[i]));

                if (operacionPendiente) {
                    operacionPendiente = false;
                    num2 = s;

                    list.set(list.lastIndexOf(num1), evaluar(num1, operacion, num2));
                    list.remove(list.lastIndexOf(operacion));
                    list.remove(list.lastIndexOf(num2));
                }

                if (tokens[i] == '*' || tokens[i] == '/') {
                    operacionPendiente = true;

                    operacion = Character.toString(tokens[i]);
                    num1 = list.get(list.lastIndexOf(operacion) - 1);
                }

                s = "";
            }

            if (i == tokens.length - 1 && s.length() > 0) {
                list.add(s);

                if (list.get(list.size() - 2).equals("*") || list.get(list.size() - 2).equals("/")) {
                    num1 = list.get(list.size() - 3);
                    operacion = list.get(list.size() - 2);
                    num2 = list.get(list.size() - 1);

                    list.set(list.size() - 3, evaluar(num1, operacion, num2));
                    list.remove(list.size() - 2);
                    list.remove(list.size() - 1);
                }
            }
        }



            while (list.size() > 1) {
                num1 = list.get(0);
                operacion = list.get(1);
                num2 = list.get(2);

                list.set(0, evaluar(num1, operacion, num2));
                list.remove(2);
                list.remove(1);
            }



        return convertir(list.get(0));


    }

    private String convertir(String string) {


        double num = Double.valueOf(string);
        DecimalFormat df;

        if (num%1==0){
            df = new DecimalFormat("0");
        } else {
            df = new DecimalFormat("0.00");
        }


        return df.format(num);

    }

    private String evaluar(String a, String operator, String b) {

        double r = 0;

        try {
            switch (operator) {
                case "/":
                    r += Double.parseDouble(a) / Double.parseDouble(b);
                    break;
                case "*":
                    r += Double.parseDouble(a) * Double.parseDouble(b);
                    break;
                case "-":
                    r += Double.parseDouble(a) - Double.parseDouble(b);
                    break;
                case "+":
                    r += Double.parseDouble(a) + Double.parseDouble(b);
                    break;
            }
        }catch(NumberFormatException e){
            return "";
        }

        return Double.toString(r);

    }

    private void setupUI(){

        usersInputBox = findViewById(R.id.textView);
        usersInputBox.setShowSoftInputOnFocus(false);

        preResult = findViewById(R.id.tResult);
        preResult.setMaxHeight(preResult.getHeight());

        backspace = findViewById(R.id.backspace);
        Button btnClear = findViewById(R.id.clear);
        Button btnDivide = findViewById(R.id.divide);
        Button btnSeven = findViewById(R.id.seven);
        Button btnEight = findViewById(R.id.eight);
        Button btnNine = findViewById(R.id.nine);
        Button btnMultiply = findViewById(R.id.multiply);
        Button btnFour = findViewById(R.id.four);
        Button btnFive = findViewById(R.id.five);
        Button btnSix = findViewById(R.id.six);
        Button btnMinus = findViewById(R.id.minus);
        Button btnOne = findViewById(R.id.one);
        Button btnTwo = findViewById(R.id.two);
        Button btnThree = findViewById(R.id.three);
        Button btnPlus = findViewById(R.id.add);
        Button btnZero = findViewById(R.id.zero);
        Button btnDecimal = findViewById(R.id.point);
        Button btnEqual = findViewById(R.id.equals);

        backspace.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnDecimal.setOnClickListener(this);
        btnEqual.setOnClickListener(this);



    }

    private String updateText(String stringToAdd, String oldString, int cursorPos){
        String leftOfCursor = oldString.substring(0, cursorPos);
        String rightOfCursor = oldString.substring(cursorPos);

        return leftOfCursor + stringToAdd + rightOfCursor;

    }


}