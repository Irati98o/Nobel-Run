package com.nobelrun.vistas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nobelrun.Manager;

import java.awt.Toolkit;

public class Login implements Screen {
    private final Game parent;
    private final SpriteBatch batch;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final BitmapFont font = new BitmapFont();
    private final OrthographicCamera camara;
    private final Manager manager;

    //
    private final Viewport viewport;
    Stage stage;
    //

    public Login(Game parent){
        this.parent = parent;

        camara = new OrthographicCamera();
        //
        viewport = new FitViewport(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, camara);
        viewport.apply();
        camara.position.set(camara.viewportWidth / 2, camara.viewportHeight / 2, 0);
        camara.update();
        //
        batch = new SpriteBatch();

        manager = new Manager(camara, parent);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(stage);


        font.setColor(Color.WHITE);
        font.getData().setScale(3,3);
        batch.begin();
        Texture logo = new Texture("logoa.png");
        Sprite sprite = new Sprite(logo);
        Sprite registro = new Sprite(new Texture("registro.png"));
        Sprite login = new Sprite(new Texture("login.png"));
        batch.draw(sprite, 400, 450);
        registro.setScale(2);
        batch.draw(registro, Toolkit.getDefaultToolkit().getScreenSize().width /2+100, 100);
        login.setScale(2);
        batch.draw(login, Toolkit.getDefaultToolkit().getScreenSize().width /2-200, 100);

        batch.end();

        if(Gdx.input.justTouched()){
            parent.setScreen(new VistaJuego(parent, manager));
        }

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
        batch.dispose();
        font.dispose();
    }
}
