package com.example.janillo.simonbis;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.Button;

/**
 * Created by janillo on 30/10/2016.
 */

public class Parpadeo  {


    public static Button miBotonBis;
    public static int tiempoParpadeo=1500;



    public static void parpadear(Button boton){
        miBotonBis=boton;
        miBotonBis.setFocusable(true);
        miBotonBis.setFocusableInTouchMode(true);
        miBotonBis.requestFocus();
        miBotonBis.postDelayed(new Runnable() {

            @Override
            public void run() {
                miBotonBis.setFocusable(false);
                miBotonBis.setFocusableInTouchMode(false);
            }
        }, tiempoParpadeo);

    }

}
