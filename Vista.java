/**
 * DESARROLLO DE APLICACIONES MULTIPLATAFORMA
 * MODULO: PROGRAMACION MULTIMEDIA Y DISPOSITIVOS MOVILES
 * CENTRO: COLEGIO STMA TRINIDAD
 * CURSO: 2016-2017
 **/
package com.example.lourdesjorgealex.laberinto.frameWorkGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;


public class Vista extends SurfaceView implements SurfaceHolder.Callback{
    private ArrayList<Sprite> sprites;
    private ArrayList<SpriteTexto> spTextos;
    private Bitmap buffer;
    private Canvas canvasBuffer;

    private boolean creada=false;
    private Game game;
    private int ancho,alto;

    public Vista(Game game){
        super(game);
        this.game=game;

        getHolder().addCallback(this);
        Rect screen=new Rect();
        game.getWindowManager().getDefaultDisplay().getRectSize(screen);
        ancho=screen.width();
        alto=screen.height();
    }





    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        creada=true;
        crearBuffer();
    }


    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    public boolean isCreada(){
        return creada;
    }
    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }

    public void setSprites(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }
    public void setSpTextos(ArrayList<SpriteTexto> spTextos) {
        this.spTextos = spTextos;
    }
    public void dibujar(){
        canvasBuffer.drawColor(Color.rgb(0, 130, 0)); //COlor de fondo
        for (int i=0;i<sprites.size();i++){
            sprites.get(i).dibujar(canvasBuffer);
        }
        for (int i=0;i<spTextos.size();i++){
            spTextos.get(i).dibujar(canvasBuffer);
        }
    }
    public void crearBuffer(){
        buffer = Bitmap.createBitmap(ancho,alto, Bitmap.Config.RGB_565);
        canvasBuffer=new Canvas(buffer);
    }
    public Bitmap getBuffer(){
        return buffer;
    }

}



