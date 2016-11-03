package com.example.janillo.simonbis;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public  int[] botones={R.id.bRojo,R.id.bAzul,R.id.bVerde,R.id.bAmarillo};
    public  int[] secuencia;
    public static Handler manejador= new Handler();
    public static Runnable ejecucion;
    public int numeroAleatorio;
    public int contador=0;
    public static int numeroVueltas=4;
    public Button miBoton;
    public static long tiempoEjecucion=2000;
    int vueltaVerificacion=0;
    public MediaPlayer mpacierto=null;
    public MediaPlayer mpfallo=null;
    int fallos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final MediaPlayer*/ mpacierto =  MediaPlayer.create(this, R.raw.acierto);
       // final MediaPlayer
                mpfallo =  MediaPlayer.create(this, R.raw.fallo);

        ejecucion = new Runnable() {
            @Override
            public void run() {
                     if (contador < numeroVueltas) {
                       numeroAleatorio = (int) (Math.random() * 4 + 0);
                       secuencia[contador] = botones[numeroAleatorio];
                       miBoton = (Button) findViewById(botones[numeroAleatorio]);
                        Parpadeo.parpadear(miBoton);
                        manejador.postDelayed(ejecucion, tiempoEjecucion);
                         mpacierto.start();
                         contador++;
                     }
                     else {
                        manejador.removeCallbacks(ejecucion);
                     }
            }
        };

    }

    public void salir(){
       System.exit(0);
    }

    public void jugar(View v){
        contador=0;
        ejecucion.run();
    }

    public void ponNivel(View v){
        Button botonNivel=(Button) findViewById(v.getId());
switch (v.getId()){
    case R.id.bnivel1:
        Parpadeo.tiempoParpadeo=1500;
       tiempoEjecucion=2000;
        numeroVueltas=4;
        secuencia=new int[numeroVueltas];
        vueltaVerificacion=0;
        break;
    case R.id.bnivel2:
        Parpadeo.tiempoParpadeo=1000;
        tiempoEjecucion=1500;
        numeroVueltas=5;
        secuencia=new int[numeroVueltas];
        vueltaVerificacion=0;
        break;
    case R.id.bnivel3:
        Parpadeo.tiempoParpadeo=500;
        tiempoEjecucion=1000;
        numeroVueltas=6;
        secuencia=new int[numeroVueltas];
        vueltaVerificacion=0;
        break;
    case R.id.bnivel4:
        Parpadeo.tiempoParpadeo=300;
        tiempoEjecucion=700;
        numeroVueltas=7;
        secuencia=new int[numeroVueltas];
        vueltaVerificacion=0;
        break;
    case R.id.bnivel5:
        Parpadeo.tiempoParpadeo=200;
        tiempoEjecucion=500;
        numeroVueltas=8;
        secuencia=new int[numeroVueltas];
        vueltaVerificacion=0;
        break;
    default:
        Parpadeo.tiempoParpadeo=1500;
        tiempoEjecucion=2000;
        secuencia=new int[4];
        numeroVueltas=4;
        vueltaVerificacion=0;
        break;
}

    }

    public void verificar(View v){

        if(v.getId()==secuencia[vueltaVerificacion]){
            mpacierto.start();
            vueltaVerificacion++;
            if(vueltaVerificacion>=secuencia.length) {
                if(fallos>0){
                    Toast.makeText(this, "Has perdido", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            mpfallo.start();
            vueltaVerificacion++;
            fallos++;
            if(vueltaVerificacion>=secuencia.length) {
                if(fallos>0){
                    Toast.makeText(this, "Has perdido", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
                }
            }
        }


        }

    }

