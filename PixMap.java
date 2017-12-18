/**
 * DESARROLLO DE APLICACIONES MULTIPLATAFORMA
 * MODULO: PROGRAMACION MULTIMEDIA Y DISPOSITIVOS MOVILES
 * CENTRO: COLEGIO STMA TRINIDAD
 * CURSO: 2016-2017
 **/
package com.example.lourdesjorgealex.laberinto.frameWorkGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;


public class PixMap {
    private Bitmap imagen;
    private int ancho,alto;

    public PixMap(String fichero){
        imagen=leerImagen(fichero);

        ancho = imagen.getWidth();
        alto = imagen.getHeight();
    }
    public Bitmap getImagen(){
        return imagen;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }
    private Bitmap leerImagen(String fichero){
        Bitmap imagen=null;
        InputStream in=null;
        try {
            in=Game.game.getAssets().open(fichero);
        } catch (IOException e) {}
        imagen = BitmapFactory.decodeStream(in);
        return imagen;
    }
}
