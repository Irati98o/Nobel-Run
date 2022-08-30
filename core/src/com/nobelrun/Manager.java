package com.nobelrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class Manager {

    private final Board board;
    private final BoardDrawer boardDrawer;

    public Manager(OrthographicCamera camara, Game parent) {
        board = new Board(camara, parent);
        board.initBoard();
        boardDrawer = new BoardDrawer(camara);
    }

    public void drawIniciales(SpriteBatch batch, ArrayList<Card> mano, Stage stage, int x, int y) {
        boardDrawer.drawIniciales(batch, mano, stage, x, y);
    }


    public void drawTablero(SpriteBatch batch, ArrayList<Card> juego, Stage stage) {
        boardDrawer.drawTablero(batch, juego, stage);
    }

    public void drawCV(SpriteBatch batch, ArrayList<Card> cv, Stage stage) {
        boardDrawer.drawCV(batch, cv, stage);
    }

    public void drawCv_pello(SpriteBatch batch, ArrayList<Card> cv_pello, Stage stage) {
        boardDrawer.drawCv_pello(batch, cv_pello, stage);
    }


    public ArrayList<Card> getIniciales() {

        return board.getIniciales();
    }


    public ArrayList<Card> getTablero() {

        return board.getTablero();
    }

    public ArrayList<Card> getBasura() {

        return board.getBasura();
    }

    public void turnoJugador(SpriteBatch batch) {
        board.turnoJugador(batch);
    }

    public ArrayList<Card> getMano() {
        return board.getMano();
    }

    public ArrayList<Card> getCV() {
        return board.getCV();
    }

    public ArrayList<Card> getCv_pello() {
        return board.getCv_pello();
    }

    public int getPuntuacion() {
        return board.getPuntuacion();
    }

    public int getPuntuacion_pello() {

        return board.getPuntuacion_pello();
    }


}