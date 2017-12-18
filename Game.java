package com.example.lourdesjorgealex.laberinto.frameWorkGame;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;


public abstract class Game extends AppCompatActivity {
    private Vista vista;
    private Thread loopGame;
    private boolean fin;
    private int ancho,alto;
    private ArrayList<Sprite> sprites;
    private ArrayList<SpriteTexto> spTextos;
  //  private Bitmap buffer;
  //  private Canvas canvasBuffer;
    //private long fps=50;
    private long tFrame=20;//en msg
    private GestorDedo gestorDedo;
    private SurfaceHolder holder;
    private Canvas canvas;
    public  static Game game;
    private HiloLoop hiloLoop;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sprites=new ArrayList<Sprite>();
        spTextos=new ArrayList<SpriteTexto>();
        game=this;

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        vista=new Vista(this);
        vista.setSprites(sprites);
        vista.setSpTextos(spTextos);

        setContentView(vista);

        ancho=vista.getAncho();
        alto=vista.getAlto();
        Rect screen=new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(screen);
        ancho=screen.width();
        alto=screen.height();
        //1184x720--->74x45 de 16px
     //   buffer = Bitmap.createBitmap(ancho,alto, Bitmap.Config.RGB_565);
     //   canvasBuffer=new Canvas(buffer);

        gestorDedo=new GestorDedo(this,vista);
        fin=false;
        hiloLoop=new HiloLoop(this);
        iniciarJuego();
      //  tFrame=1000000000/fps;
        hiloLoop.setTiempoFrame(tFrame);
        holder = vista.getHolder();
        hiloLoop.start();

    }


    public void loop(int deltaTime){
            try {
                canvas= null;
                vista.dibujar();
                actualizarLogica(deltaTime);
                //*Pintado en pantalla
                canvas = holder.lockCanvas();
                synchronized(holder) {
                    canvas.drawBitmap(vista.getBuffer(), 0, 0,null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (canvas != null) holder.unlockCanvasAndPost(canvas);
            }
            //actulizar entradas: teclado, raton

            Sprite sp;
            if (gestorDedo.isTocada()){              ;
                for (int i=0;i<sprites.size();i++){
                    sp=sprites.get(i);
                    if (sp instanceof Controlable)
                        ((Controlable) sp).doAccion(gestorDedo.getTouchEvento());
                }
            }

    }

    public void actualizarLogica(int deltaTime){
        for (int i=0;i<sprites.size();i++){
            sprites.get(i).actualizar(deltaTime);
        }
        this.actualizar(deltaTime);
    }
    public abstract void actualizar(long deltaTime);

    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }
    public ArrayList<Sprite> getSprites(){
        return sprites;
    }

    public void addSpTexto(SpriteTexto spTexto){
        spTextos.add(spTexto);
    }
    public ArrayList<SpriteTexto> getSpritesTexto(){
        return spTextos;
    }

    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
    public abstract void iniciarJuego();

    public GestorDedo getGestorDedo(){
        return gestorDedo;
    }
    public Vista getVista(){
        return vista;
    }
    public boolean verColisiones(Sprite sp){
        Sprite aux;
        boolean siColision=false;
        for (int i=0;i<sprites.size();i++){
            aux=sprites.get(i);
            if (aux!=sp && aux.isHabilitado()){
                if (sp.hayColision(aux)){
                    // System.out.println("colision");
                    aux.chocar(sp);
                    sp.chocar(aux);
                    siColision=true;
                }
            }
        }
        return siColision;
    }
    public void setTimeFrame(int tiempoFrame){
        hiloLoop.setTiempoFrame(tiempoFrame*1000000);
    }
}
