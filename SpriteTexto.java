package com.example.lourdesjorgealex.laberinto.frameWorkGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by PROFDAM on 24/11/2017.
 */

public class SpriteTexto {
    private String texto;
    private int x, y;
    private Game game;
    private Paint pincel;

    private static Game juego=Game.game;

    public SpriteTexto(String texto){
        this.texto=texto;
        juego.addSpTexto(this);
        pincel= new Paint();
        pincel.setColor(Color.rgb(100, 0, 200));
        pincel.setTextSize (50);
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public  void dibujar(Canvas canvas){
        canvas.drawText(texto, x, y,pincel);
    }
    public void setPosicion(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void setTamanio(int tamanio){
        this.pincel.setTextSize(tamanio);
    }

    public void setColor(int r, int g, int b){
        pincel.setColor(Color.rgb(r, g, b));
    }
}
