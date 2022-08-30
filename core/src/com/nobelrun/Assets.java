package com.nobelrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {

    public static Texture texture_fondo;
    public static Sprite sprite_fondo;


    public static void cargar(){
        texture_fondo = new Texture(Gdx.files.internal("Catalina.png"));
        texture_fondo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite_fondo = new Sprite(texture_fondo);
        sprite_fondo.flip(true, false);

    }
}
