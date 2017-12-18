/**
 * DESARROLLO DE APLICACIONES MULTIPLATAFORMA
 * MODULO: PROGRAMACION MULTIMEDIA Y DISPOSITIVOS MOVILES
 * CENTRO: COLEGIO STMA TRINIDAD
 * CURSO: 2016-2017
 **/
package com.example.lourdesjorgealex.laberinto.frameWorkGame;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GestorDedo implements OnTouchListener {
    private EventoTocar eventoTocar;
    private boolean pantallaTocada;
    private Game game;
    public static class EventoTocar { //Crear una clase estatica que contiene los elementos necesarios, puede ser x,y
        public int tipoEvento; //Aqui se pondría la acción que se realiza MotionEvent.ACTION_MOVE (por ejemplo)
        public int x, y;
    }
    public GestorDedo(Game game,Vista vista){
        this.game=game;
        vista.setOnTouchListener(this);
        eventoTocar=new EventoTocar();
        pantallaTocada=false;
    }

    public boolean isTocada(){
        return pantallaTocada;
    }

    public EventoTocar getTouchEvento(){
        pantallaTocada=false;
        return eventoTocar;
    }

    public boolean onTouch(View v, MotionEvent event) { //El metodo debe ser synchronized
        eventoTocar.x=(int) event.getX();
        eventoTocar.y=(int) event.getY();
        pantallaTocada=true;
        return false;
    }

}

