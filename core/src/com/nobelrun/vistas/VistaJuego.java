package com.nobelrun.vistas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nobelrun.Assets;
import com.nobelrun.Deck;
import com.nobelrun.Manager;
import java.awt.Toolkit;


public class VistaJuego implements Screen {

   // NobelRunGame game;
   private final Game parent;
   private final Manager manager;

    public Stage stage;

    public OrthographicCamera camara;
    public SpriteBatch batch;

    public Vector3 tp;
    public BitmapFont font;

    public ShapeRenderer shape;
    Deck deck;

    public int posX, posY;
    public boolean clicado = false;

    public VistaJuego(Game parent, Manager manager){
        this.parent = parent;
        this.manager = manager;

        camara = new OrthographicCamera();
        camara.setToOrtho(false, 1400, 1400);
        camara.update();

        batch = new SpriteBatch();

        font = new BitmapFont();
        tp = new Vector3();

        shape = new ShapeRenderer();

        stage = new Stage(new ScreenViewport());

        posX = 350;
        posY = 13;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camara.update();

        Gdx.input.setInputProcessor(stage);

        batch.setProjectionMatrix(camara.combined);                 //escala el sprite al tamaño de la pantalla

        stage.act(Gdx.graphics.getDeltaTime());

        batch.begin();                                          //Rendering code

        batch.draw(Assets.sprite_fondo, 0, 0);

        //Si se esta clicando   Con esto hacemos un drag
     /*   if(Gdx.input.isTouched()){
            camara.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            font.draw(batch,tp.x+ " , " + tp.y, tp.x - 25, tp.y - 5);
            posX = (int)tp.x - 700;
            posY = (int)tp.y - 700;
        }*/

        //Si se ha clicado   Pinta donde se clica
        if(Gdx.input.justTouched()){
            camara.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            clicado = true;
        }

/*        if(clicado){
            if(tp.y < 445 && tp.y > 28) {
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
            font.draw(batch, tp.x + " , " + tp.y, tp.x - 25, tp.y - 5);
        }

 */
        if(clicado){
            manager.turnoJugador(batch);
        }


        font.draw(batch, "Nire CV", 30, 1375);
        font.draw(batch, "Peioren CV", Toolkit.getDefaultToolkit().getScreenSize().getSize().width - 230,1375);
        //font.draw(batch, "Zaborra", Toolkit.getDefaultToolkit().getScreenSize().getSize().width - 230, 450);
        //font.draw(batch, "Erabilitakoak", 30, 450);
        font.draw(batch, "Markagailua:", 250, 1275);
        font.draw(batch, "Zu:", 250, 1245);
        font.draw(batch, " " + manager.getPuntuacion(), 300, 1245);
        font.draw(batch, "Peio:", 250, 1215);
        font.draw(batch, " " + manager.getPuntuacion_pello(), 300, 1215);
        font.getData().setScale(1,2);


        manager.drawIniciales(batch, manager.getMano(), stage, posX, posY);
        manager.drawTablero(batch, manager.getTablero(), stage);
        manager.drawCV(batch, manager.getCV(), stage);
        manager.drawCv_pello(batch, manager.getCv_pello(), stage);


        batch.end();
        stage.draw();

        shape.begin(ShapeRenderer.ShapeType.Line);

        //CV
        shape.rect(20,10,200,1100);
        shape.setColor(255,255,255,1);

        //Zure kartak
        shape.rect((Toolkit.getDefaultToolkit().getScreenSize().getSize().width / 2) - 385,10,780,230);
        shape.setColor(255,255,255,1);

        //CV Pello
        shape.rect(Toolkit.getDefaultToolkit().getScreenSize().getSize().width - 220,10,200,1270);
        shape.setColor(255,255,255,1);


        int x = 0;
        int y = 0;
        //10 kartak
        for(int i = 0; i<2; i++){
            for(int j = 0; j<5; j++){
                shape.rect(x+450, y+300, 150, 230);
                shape.setColor(255, 255, 255, 1);
                x += 150;
            }
            x = 0;
            y += 230;
        }

        shape.end();


    }

    @Override
    public void resize(int width, int height) {

    }

     @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
