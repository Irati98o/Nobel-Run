package com.nobelrun;

import com.badlogic.gdx.Game;
import com.nobelrun.vistas.Login;

public class NobelRunGame extends Game {

    public Login login;

    @Override
    public void create() {
        Assets.cargar();
        login = new Login(this);
        setScreen(login);
    }
}
