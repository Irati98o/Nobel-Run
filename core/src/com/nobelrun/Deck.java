package com.nobelrun;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    public ArrayList<Card> iniciales = new ArrayList<>();
    private ArrayList<Card> basicas = new ArrayList<>();
    private ArrayList<Card> avanzadas = new ArrayList<>();

    private ArrayList<Card> monton2 = new ArrayList<>();
    private ArrayList<Card> monton3 = new ArrayList<>();
    public ArrayList<Card> montonJuego = new ArrayList<>();
    private ArrayList<Card> resto = new ArrayList<>();

    public Deck() {
        this.montonJuego = crearBarajaJuego();
    }

    public ArrayList<Card> crearCartasIniciales(){
        iniciales = new ArrayList<>();

        for (int x = 0; x < 2; x++){
            iniciales.add(new Card(Card.Type.INVESTIGACION, Card.Color.GRIS, 0,0, 0, 1,0, 1,0));
        }

        for (int y = 0; y < 8; y++){
            iniciales.add(new Card(Card.Type.TRABAJO, Card.Color.GRIS,0, 0, 0,1, 1,0, 0));
        }

        Collections.shuffle(iniciales);

        return iniciales;
    }

    public ArrayList<Card> crearBarajaJuego(){
        basicas.add(new Card(Card.Type.GIGANTE, Card.Color.AZUL, 0,0, 0, 0,2, 0,0));
        basicas.add(new Card(Card.Type.CUIDADOS, Card.Color.AZUL, 0,0, 0, 0,-2, 0,0));
        basicas.add(new Card(Card.Type.PERDIDA, Card.Color.AZUL,0, 0, 0,0, -1,0, 0));
        basicas.add(new Card(Card.Type.TUBERIA, Card.Color.AZUL, 0,0, 0, 0,-2, 0,0));
        basicas.add(new Card(Card.Type.IMPOSTORA, Card.Color.AZUL, 0,0, 0, 0,-1, 0,0));
        basicas.add(new Card(Card.Type.ROBERTA_WILLIAMS, Card.Color.AZUL, 5, 2, 0, 0, 0, 0, 6, 0, 0));
        basicas.add(new Card(Card.Type.LISE_MEITNER, Card.Color.AZUL, 5, 0, 2, 0, 0, 0, 0, 6, 0));
        basicas.add(new Card(Card.Type.GLADYS_MAE_WEST, Card.Color.AZUL, 4, 0, 2, 0, 0, 0, 0, 4, 0));
        basicas.add(new Card(Card.Type.GRACE_MURRAY_HOPPER, Card.Color.AZUL, 3, 2, 0, 0, 0, 0, 4, 0, 0));
        basicas.add(new Card(Card.Type.KATHERINE_JOHNSON, Card.Color.AZUL, -5, 0, 0, 3, 0, 0, 0, 0, 5));
        basicas.add(new Card(Card.Type.MARY_SOMERVILLE, Card.Color.AZUL, 3, 0, 2, 0, 0, 0, 0, 4, 0));
        basicas.add(new Card(Card.Type.HEDY_LAMARR, Card.Color.AZUL, 3, 0, 0, 2, 0, 0, 0, 0, 4));
        basicas.add(new Card(Card.Type.DOROTHY_CROWFOOT_HODGKIN, Card.Color.AZUL, 4, 2, 0, 0, 0, -2, 0, 0, 0));
        basicas.add(new Card(Card.Type.MARGARITA_SALAS, Card.Color.AZUL, 3, 0, 0, 2, 0, 0, 4, 0, 0));
        basicas.add(new Card(Card.Type.ROSALIND_ELSIE_FRANKLIN, Card.Color.AZUL, 4, 0, 0, -2, 0, 0, 0, 0, -4));
        basicas.add(new Card(Card.Type.ALEXANDRA_ELBAKYAN, Card.Color.AZUL, 2, 0, 0, 2, 0, 0, 2, 0, 0));

        avanzadas.add(new Card(Card.Type.JOCELYN_BELL_BURNELL, Card.Color.MORADO, 8, 0, 0, 2, 0, -4, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.CHIEN_SHIUNG_WU, Card.Color.MORADO, 8, 0, 2, 0, 0, -4, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.DONNA_STRICKLAND, Card.Color.MORADO, 8, 0, 0, 0, -1, 4, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.VALENTINA_TERESHKOVA, Card.Color.MORADO, 8, 0, 2, 0, 0, 4, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.RAYE_JEAN_MONTAGUE, Card.Color.MORADO, 6, 3, 0, 0, 0, -4, 0, 4, 0));
        avanzadas.add(new Card(Card.Type.MARIE_SKLODOWSKA_CURIE, Card.Color.MORADO, 10, 0, 5, 0, 0, 5, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.FLORENCE_NIGHTINGALE, Card.Color.MORADO, 8, 0, 0, 2, 0, 4, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.MARGARET_HAMILTON, Card.Color.MORADO, 6, 0, 2, 0, 0, 3, 0, 0, 0));
        avanzadas.add(new Card(Card.Type.SALLY_KRISTEN_RIDE, Card.Color.MORADO, 6, 0, 0, 0, 2, 0, 4, 0, 0));
        avanzadas.add(new Card(Card.Type.ADA_LOVELACE, Card.Color.MORADO, 6, 3, 0, 0, 0, 0, 0, 0, 4));


        for (int x = 0; x < 2; x++){
            avanzadas.add(new Card(Card.Type.INTERNACIONAL, Card.Color.MORADO, 0,10, 0, 6,0, 3,3));
            avanzadas.add(new Card(Card.Type.SENIOR, Card.Color.MORADO,8,0, 0, 0,4, 2,3));
            avanzadas.add(new Card(Card.Type.TOP, Card.Color.MORADO,0,7, 3, 0,0, 0,6));
            iniciales.add(new Card(Card.Type.INVESTIGACION, Card.Color.GRIS, 0,0, 0, 1,0, 1,0));
        }

        for (int i = 0; i < 4; i++) {
            basicas.add(new Card(Card.Type.LABORATORIO, Card.Color.AZUL, 4, 0, 0, 2, 0, 0, 2, 0, 0));
            avanzadas.add(new Card(Card.Type.NACIONAL, Card.Color.MORADO,0,6, 0, 4,0, 2,2));
            avanzadas.add(new Card(Card.Type.POSTDOC, Card.Color.MORADO,4,0, 0, 0,3, 1,2));
            basicas.add(new Card(Card.Type.Q3, Card.Color.AZUL,0,3, 1, 0,0, 0,2));
            avanzadas.add(new Card(Card.Type.Q2, Card.Color.MORADO, 0,4, 2, 0,0, 0,3));
            avanzadas.add(new Card(Card.Type.Q1, Card.Color.MORADO,0,5, 2, 0,0, 0,4));
            avanzadas.add(new Card(Card.Type.Q1OA, Card.Color.MORADO,1,5, 2, 0,0, 0,5));
        }

        for (int y = 0; y < 8; y++){
            basicas.add(new Card(Card.Type.Q4, Card.Color.AZUL,0,2, 1, 0,0, 0,1));
            iniciales.add(new Card(Card.Type.TRABAJO, Card.Color.GRIS,0, 0, 0,1, 1,0, 0));
        }

        for (int k = 0; k < 10; k++){
            basicas.add(new Card(Card.Type.LOCAL, Card.Color.AZUL,0,4, 0, 2,1, 0,1));
            basicas.add(new Card(Card.Type.PREDOC, Card.Color.AZUL,4,0, 0, 1,2, 0,1));
        }


        //Aldatu
        Collections.shuffle(iniciales);
        Collections.shuffle(basicas);
        Collections.shuffle(avanzadas);

 //       System.out.println(basicas.size());
 //       System.out.println(avanzadas.size());

        for(int i = 0; i < 20; i++){
            if(i >= 15){
                monton2.add(avanzadas.get(0));
                avanzadas.remove(0);
                monton3.add(basicas.get(0));
                basicas.remove(0);
            } else {
                monton2.add(basicas.get(0));
                basicas.remove(0);
                monton3.add(avanzadas.get(0));
                avanzadas.remove(0);
            }

        }

 //       System.out.println(monton2.size());
 //       System.out.println(monton3.size());

        Collections.shuffle(monton2);
        Collections.shuffle(monton3);

        montonJuego.addAll(monton2);

        resto.addAll(basicas);
        resto.addAll(avanzadas);

        Collections.shuffle(resto);

        montonJuego.addAll(resto);
        montonJuego.addAll(monton3);

        return montonJuego;

 //       System.out.print(montonJuego.size());

    }

/*    public Card draw(Integer monton){
        Card card;
        if(monton == 1){
            card = iniciales.get(0);
            iniciales.remove(0);
            return card;
        } else {
            card = montonJuego.get(0);
            montonJuego.remove(0);
            return card;
        }
    }

 */


    public ArrayList<Card> getMontonJuego() {

        return montonJuego;
    }
}
