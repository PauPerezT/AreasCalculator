package com.paulaperez.areascalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbCuadrado,rbCirculo,rbTriangulo,rbCubo;
    private Button btResult;
    private TextView tvResult;
    private EditText et1, et2, et3;
    private Figura selectedFig;
    private String param1_s, param2_s, param3_s;
    private double param1_d, param2_d, param3_d;

    private enum Figura {
        Cuadrado,Circulo, Triangulo, Cubo
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbCuadrado=(RadioButton)findViewById(R.id.rbCuadrado);
        rbCirculo=(RadioButton)findViewById(R.id.rbCirculo);
        rbTriangulo=(RadioButton)findViewById(R.id.rbTriangulo);
        rbCubo=(RadioButton)findViewById(R.id.rbCubo);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        btResult=(Button)findViewById(R.id.btResult);
        tvResult=(TextView)findViewById(R.id.tvResult);

        et1.setVisibility(View.VISIBLE);
        et1.setHint("Lado (cm)");
        et2.setVisibility(View.GONE);
        et3.setVisibility(View.GONE);
        selectedFig=Figura.Cuadrado;



        rbCuadrado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et1.setVisibility(View.VISIBLE);
                    et1.setHint("Lado (cm)");
                    et2.setVisibility(View.GONE);
                    et3.setVisibility(View.GONE);

                    rbCirculo.setChecked(false);
                    rbTriangulo.setChecked(false);
                    rbCubo.setChecked(false);
                    selectedFig=Figura.Cuadrado;
                }

            }
        });


        rbCirculo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et1.setVisibility(View.VISIBLE);
                    et1.setHint("Radio (cm)");
                    et2.setVisibility(View.GONE);
                    et3.setVisibility(View.GONE);

                    rbCuadrado.setChecked(false);
                    rbTriangulo.setChecked(false);
                    rbCubo.setChecked(false);
                    selectedFig=Figura.Circulo;
                }

            }
        });


        rbTriangulo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et1.setVisibility(View.VISIBLE);
                    et2.setVisibility(View.VISIBLE);
                    et3.setVisibility(View.VISIBLE);
                    et1.setHint("Lado 1 (cm)");
                    et2.setHint("Lado 2 (cm)");
                    et3.setHint("Lado 3 (cm)");


                    rbCirculo.setChecked(false);
                    rbCuadrado.setChecked(false);
                    rbCubo.setChecked(false);
                    selectedFig=Figura.Triangulo;
                }

            }
        });

        rbCubo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {

                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et1.setVisibility(View.VISIBLE);
                    et1.setHint("Arista (cm)");
                    et2.setVisibility(View.GONE);
                    et3.setVisibility(View.GONE);

                    rbCirculo.setChecked(false);
                    rbTriangulo.setChecked(false);
                    rbCuadrado.setChecked(false);
                    selectedFig=Figura.Cubo;
                }

            }
        });

        btResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                param1_s=et1.getText().toString();
                param2_s=et2.getText().toString();
                param3_s=et3.getText().toString();

                param1_d = param1_s.isEmpty()?0:Double.parseDouble(param1_s);
                param2_d = param2_s.isEmpty()?0:Double.parseDouble(param2_s);
                param3_d = param2_s.isEmpty()?0:Double.parseDouble(param3_s);

                String perimetroToWrite=computePerimetro(selectedFig,param1_d,param2_d,param3_d);
                String areaToWrite=computeArea(selectedFig,param1_d,param2_d,param3_d);
                String volumenToWrite="";
                if(selectedFig==Figura.Cubo) volumenToWrite =computeVolumen(param1_d);

                tvResult.setText(perimetroToWrite+ "\n" + areaToWrite + ((selectedFig==Figura.Cubo)?"\n"+volumenToWrite:""));
            }
        });

    }


    private String computePerimetro(Figura fig, double param1, double param2, double param3){

        double perimetro =0;


        switch (fig){

            case Cuadrado:
                //param1 Lado
                perimetro=param1*4;

                break;

            case Circulo:
                //param1 su radio

                perimetro=2*Math.PI*param1;

                break;

            case Triangulo:
                //params sus lados
                perimetro=param1+param2+param3;
                double semiPerimetro=perimetro/2;
                double areaWithoutSquare = semiPerimetro*(semiPerimetro-param1)*(semiPerimetro-param2)*(semiPerimetro-param3);
                if(areaWithoutSquare<0){
                    return "";
                }

                break;

            case Cubo:
                //param1 su arista

                perimetro=param1*12;


               break;

        }

        return "Perimetro:" + Double.toString(perimetro)+" cm";
    }

    private String computeArea(Figura fig,  double param1, double param2, double param3){

        double perimetro=0,semiPerimetro=0,area=0;


        switch (fig){

            case Cuadrado:
                //param1 Lado
                area=Math.pow(param1,2);

                break;

            case Circulo:
                //param1 su radio

                area=Math.PI*Math.pow(param1,2);

                break;

            case Triangulo:
                //params sus lados
                perimetro=param1+param2+param3;
                semiPerimetro=perimetro/2;
                double areaWithoutSquare = semiPerimetro*(semiPerimetro-param1)*(semiPerimetro-param2)*(semiPerimetro-param3);
                if(areaWithoutSquare<0){
                    return "ERROR , NO es un triangulo";
                }
                area=Math.sqrt(areaWithoutSquare);


                break;

            case Cubo:
                //param1 su arista

                area=6*Math.pow(param1,2);

                break;

        }

        return "Area:" + Double.toString(area)+" cm2";


    }

    private String computeVolumen(double arista){


         double volumen=Math.pow(arista,3);
         return "Volumen:" + Double.toString(volumen)+ " cm3";

    }


}
