package com.nobelrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.swing.JPopupMenu;

public class Card extends ImageButton{


    public enum Type {
       TRABAJO, INVESTIGACION, LOCAL, INTERNACIONAL, NACIONAL, PREDOC, POSTDOC, SENIOR, LABORATORIO,
        Q4, Q3, Q2, Q1, Q1OA, TOP, GIGANTE, CUIDADOS, PERDIDA, TUBERIA, IMPOSTORA,
        ADA_LOVELACE, ALEXANDRA_ELBAKYAN, CHIEN_SHIUNG_WU, DONNA_STRICKLAND, DOROTHY_CROWFOOT_HODGKIN,
        FLORENCE_NIGHTINGALE, GLADYS_MAE_WEST, GRACE_MURRAY_HOPPER, HEDY_LAMARR,
        JOCELYN_BELL_BURNELL, KATHERINE_JOHNSON, LISE_MEITNER, MARGARET_HAMILTON,
        MARGARITA_SALAS, MARIE_SKLODOWSKA_CURIE, MARY_SOMERVILLE, RAYE_JEAN_MONTAGUE, ROBERTA_WILLIAMS,
        ROSALIND_ELSIE_FRANKLIN, SALLY_KRISTEN_RIDE, VALENTINA_TERESHKOVA
    }

    public enum Color {
        GRIS, AZUL, MORADO
    }

    public int coste_monedas;
    public int coste_esfuerzo;
    public int coste_dato;
    public int valor_moneda;
    public int valor_esfuerzo;
    public int valor_dato;
    public  int valor_carta;
    public int cv_estrella;
    public int cv_moneda;
    public int cv_dato;
    public int cv_esfuerzo;
    Type type;
    Color color;

    int cont = 0;
    int esfuerzo;
    int moneda;
    int dato;
    boolean clickado;
    JPopupMenu menu;

    //Trabajo
    public Card(Type type, Color color, int coste_monedas, int coste_esfuerzo, int coste_dato,
                int valor_moneda, int valor_esfuerzo, int valor_dato, int cv_estrella) {
        super(new TextureRegionDrawable(new TextureRegion(new Texture(getTexturePath(type)))));
        getStyle().imageDisabled = new TextureRegionDrawable(new TextureRegion(new Texture("atzie.png")));

        this.type = type;
        this.color = color;
        this.coste_monedas = coste_monedas;
        this.coste_esfuerzo = coste_esfuerzo;
        this.coste_dato = coste_dato;
        this.valor_moneda = valor_moneda;
        this.valor_esfuerzo = valor_esfuerzo;
        this.valor_dato = valor_dato;
        this.cv_estrella = cv_estrella;


    }

    public Card(Type type, Color color, int coste_monedas, int valor_moneda,
                int valor_esfuerzo, int valor_dato, int valor_carta,
                int cv_estrella, int cv_moneda, int cv_esfuerzo, int cv_dato) {
        super(new TextureRegionDrawable(new TextureRegion(new Texture(getTexturePath(type)))));
        getStyle().imageDisabled = new TextureRegionDrawable(new TextureRegion(new Texture("atzie.png")));

        this.type = type;
        this.color = color;
        this.coste_monedas = coste_monedas;
        this.valor_moneda = valor_moneda;
        this.valor_esfuerzo = valor_esfuerzo;
        this.valor_dato = valor_dato;
        this.valor_carta = valor_carta;
        this.cv_estrella = cv_estrella;
        this.cv_moneda = cv_moneda;
        this.cv_esfuerzo = cv_esfuerzo;
        this.cv_dato = cv_dato;
    }

    public void show(boolean show) {
        setDisabled(!show);
        clearListeners();
    }

    @Override
    public boolean addListener(EventListener listener) {
        return super.addListener(listener);
    }

    public int getCoste_monedas() {

        return coste_monedas;
    }

    public int getCoste_esfuerzo() {
        return coste_esfuerzo;
    }

    public int getCoste_dato() {
        return coste_dato;
    }

    public Type getType() {
        return type;
    }

    public String color() {
        return color.toString();
    }

    public int getValor_moneda() {

        return valor_moneda;
    }

    public int getValor_esfuerzo() {
        return valor_esfuerzo;
    }

    public int getValor_dato() {
        return valor_dato;
    }

    public int getValor_carta() {
        return valor_carta;
    }

    public int getCv_estrella() {
        return cv_estrella;
    }

    public int getCv_moneda() {
        return cv_moneda;
    }

    public int getCv_dato() {
        return cv_dato;
    }

    public int getCv_esfuerzo() {
        return cv_esfuerzo;
    }

    public static String getTexturePath(Type type) {

        String typePath = type.toString().toLowerCase();

        switch (type) {
            case LOCAL:
            case NACIONAL:
            case INTERNACIONAL:
                return "proyecto_" + typePath + ".png";
            case Q4: return "Q4.png";
            case Q3: return "Q3.png";
            case Q2: return "Q2.png";
            case Q1: return "Q1.png";
            case Q1OA: return "Q1OA.png";
            case TOP: return "TOP.png";
            case GIGANTE: return "A_hombros_de_" + typePath + "s.png";
            case PERDIDA: return typePath + "_copia_de_seguridad.png";
            case IMPOSTORA: return "sindrome_" + typePath + ".png";
            default:
             //   System.out.println("Ez da existitzen");
                //  Gdx.app.exit();
                return typePath + ".png";
        }

    }


}
