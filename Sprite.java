/**
 * DESARROLLO DE APLICACIONES MULTIPLATAFORMA
 * MODULO: PROGRAMACION MULTIMEDIA Y DISPOSITIVOS MOVILES
 * CENTRO: COLEGIO STMA TRINIDAD
 * CURSO: 2016-2017
 **/
package com.example.lourdesjorgealex.laberinto.frameWorkGame;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Sprite {
    private PixMap pixmap;
    private int x,y;
    private int ancho, alto;
    private Rect malla;
    private boolean habilitado;
    private boolean visible;
    protected static Game juego=Game.game;
    public Sprite(PixMap pixmap){
        this.pixmap=pixmap;
        this.x=100;
        this.y=100;
        ancho=pixmap.getImagen().getWidth();
        alto=pixmap.getImagen().getHeight();
        juego.addSprite(this);
        malla=new Rect(x,y,ancho,alto);
        habilitado=true;
        visible=true;
    }

    public void setPosicion(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.malla.set(x, y,x+ancho,y+alto);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.malla.set(x, y,x+ancho,y+alto);
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public abstract void actualizar(int deltaTime);
    public void actualizarLogica(int deltaTime){
        if (habilitado)
            actualizar(deltaTime);
    }
    public abstract void chocar(Sprite sp);

    public boolean hayColision(Sprite sp){
        boolean choco=false;
        choco= malla.intersect(sp.getMalla());
        return choco;
    }
    public void setHabilitado(boolean habilitado){
        this.habilitado=habilitado;
    }
    public void setVisible(boolean visible){
        this.visible=visible;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public boolean isVisible() {
        return visible;
    }


    public void dibujar(Canvas canvas){
        if (visible)
            canvas.drawBitmap(pixmap.getImagen(), x , y , null);
    }

    public Rect getMalla(){
        return malla;
    }

}
