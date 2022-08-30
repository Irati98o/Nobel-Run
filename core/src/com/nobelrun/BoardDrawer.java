package com.nobelrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nobelrun.vistas.VistaJuego;

import java.awt.event.InputEvent;
import java.util.ArrayList;

public class BoardDrawer {
    private static final BitmapFont font = new BitmapFont();
    private static final int cardWidth = 150;
    private static final int cardHeight = 225;
    private final OrthographicCamera camara;
    private final Vector3 tp;


    private static final int OFFSET_RANGE = 5;

    public static Texture textura_img;
    public static Sprite sprite_img;

    VistaJuego game;

    public BoardDrawer(OrthographicCamera camara) {

        this.camara = camara;

        this.tp = new Vector3();
    }

    public void drawCV(SpriteBatch batch, ArrayList<Card> cv, Stage stage){
        int x = 42;
        int y = 12;
        int tam = cv.size();
        if(!cv.isEmpty()){
            for(int i = 0; i < tam; i++){
                Card card = cv.get(i);
                card.setPosition(x, y);
                card.toFront();
                stage.addActor(card);
                y += 50;
            }
        }
    }

    public void drawCv_pello(SpriteBatch batch, ArrayList<Card> cv_pello, Stage stage){
        int x = 1242;
        int y = 12;
        int tam = cv_pello.size();
        if(!cv_pello.isEmpty()){
            for(int i = 0; i < tam; i++){
                Card card = cv_pello.get(i);
                card.setPosition(x, y);
                card.toFront();
                stage.addActor(card);
                y += 50;
            }
        }
    }

    public void drawIniciales(SpriteBatch batch, ArrayList<Card> mano, Stage stage, int x, int y){
    //   int x = 350;
    //   int y = 13;
       camara.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
       if(!mano.isEmpty()){
           for (int i = 0; i < mano.size(); i++){
               final Card card = mano.get(i);
               card.setPosition(x, y);
               card.toFront();
               stage.addActor(card);
               x += cardWidth;
           }

 /*          if(Gdx.input.justTouched()){
               if(tp.y < 300 && tp.y > 13) {
                   if (tp.x > 328 && tp.x < 486) {
                       font.draw(batch, "Carta 1", tp.x, tp.y+50);
                   } else if(tp.x > 486 && tp.x < 630){
                       font.draw(batch, "Carta 2", tp.x, tp.y+50);
                   } else if(tp.x > 632 && tp.x < 776){
                       font.draw(batch, "Carta 3", tp.x, tp.y+50);
                   } else if(tp.x > 780 && tp.x < 922){
                       font.draw(batch, "Carta 4", tp.x, tp.y+50);
                   } else if(tp.x > 925 && tp.x < 1067){
                       font.draw(batch, "Carta 5", tp.x, tp.y+50);
                   }
               }
           }

  */

       }

    }


    public void drawTablero(SpriteBatch batch, ArrayList<Card> juego, Stage stage){
        int x = 450;
        int y = 300;
        int cont = 0;
        int cartas = juego.size();
        if(!juego.isEmpty()) {
            if(cartas < 5){
                for(int k = 0; k < cartas; k++){
                    Card card = juego.get(cont);
                    card.setPosition(x,y);
                    stage.addActor(card);
                    x += cardWidth;
                    cont++;
                }
            } else if (cartas > 5 && cartas < 10){
                for(int q = 0; q < 2; q++){
                    for (int r = 0; r < (cartas-5); r++){
                        Card card = juego.get(cont);
                        card.setPosition(x,y);
                        stage.addActor(card);
                        x += cardWidth;
                        cont++;
                    }
                    x = 450;
                    y += cardHeight + 10;
                }
            } else {
                for (int j = 0; j < 2; j++) {
                    for (int i = 0; i < 5; i++) {
                        Card card = juego.get(cont);
                        card.setPosition(x, y);
                        //System.out.println(card.getCoste_monedas());
                        stage.addActor(card);
                        x += cardWidth;
                        cont++;
                    }
                    x = 450;
                    y += cardHeight + 10;
                }
            }
        }

    }

}
