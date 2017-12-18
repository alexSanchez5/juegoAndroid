package com.example.lourdesjorgealex.laberinto.frameWorkGame;

/**
 * Created by PROFDAM on 24/11/2017.
 */

public class HiloLoop extends Thread {

    private long tiempoFrame=50000000L;
    private long tInicio,tFin;
    private long tDormir=0,tCiclo;
    private int deltaTime;//en msg
    private Game game;
    private Vista vista;

    private boolean fin;

    public HiloLoop(Game game){
        this.game=game;
        this.vista=game.getVista();

    }
    @Override
    public void run(){

        while(!vista.isCreada());

        long tdormir, tactivo;
        tInicio= System.nanoTime();
        while (!fin) {
            game.loop(deltaTime);
            tFin= System.nanoTime();
            tInicio=tFin;
            tCiclo=tFin-tInicio;
            if (tCiclo<tiempoFrame){
                tDormir=tiempoFrame-tCiclo;
                dormir(tDormir);
            }
            deltaTime=(int)((tCiclo+tDormir)/1000000);

        }
    }
    public void dormir(long t){
        try {
            Thread.sleep(t/1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setTiempoFrame(long tiempoFrame){
        this.tiempoFrame=tiempoFrame;
    }
}
