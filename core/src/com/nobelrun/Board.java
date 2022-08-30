package com.nobelrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Board {

    private static final int OFFSET_RANGE = 10;

    private ArrayList<Card> iniciales;
    private ArrayList<Card> baraja;
    private final ArrayList<Card> erabilitakoak;
    private final ArrayList<Card> mano;
    private final ArrayList<Card> tablero;
    private final ArrayList<Card> cv;
    private final ArrayList<Card> cv_pello;
    private final ArrayList<Card> tmp;
    private final ArrayList<Card> basura;

    private final OrthographicCamera camara;
    private final Game parent;
    private final Vector3 touchPoint = new Vector3();
    private Skin skin;


    private int elegidoPublicar = 0;
    private int esfuerzo_mano = 0;
    private int dato_mano = 0;
    private int moneda_mano = 0;
    private int esfuerzo = 0;
    private int pos = 1;
    private int puntuacion = 0;
    private int monedas = 0;
    private int cont = 1;
    private int[] seleccionados = new int[5];
    private int datos = 0;
    private boolean publicado = false;
    private int puntuacion_pello = 0;
    private int partida_jugada = 0;
    private int parttida_ganada = 0;

    BoardDrawer boardDrawer;

    Scanner scan;

    Deck deck;


    public Board(OrthographicCamera camara, Game parent){
        this.erabilitakoak = new ArrayList<>();
        this.mano = new ArrayList<>();
        this.tablero = new ArrayList<>();
        this.cv = new ArrayList<>();
        this.cv_pello = new ArrayList<>();
        this.tmp = new ArrayList<>();
        this.basura = new ArrayList<>();

        this.camara = camara;
        this.parent = parent;

       // batch = new SpriteBatch();
        boardDrawer = new BoardDrawer(camara);

        deck = new Deck();
        this.iniciales = deck.crearCartasIniciales();
        this.baraja = deck.crearBarajaJuego();

        scan = new Scanner(System.in);
    }


    public void initIniciales(){
        for (int i = 0; i < 5; i++){
            Card card = iniciales.get(0);
            mano.add(card);
            iniciales.remove(card);
        }
        //System.out.println(iniciales.size());
        //System.out.println(mano.size());
    }

    public void initTablero(){
        for (int i = 0; i < 10; i++){
            final Card card = baraja.get(0);
            tablero.add(card);
            baraja.remove(card);
        }
    }

    public void sumaCartaTablero(){
        Card card = baraja.get(0);
        tablero.add(card);
        baraja.remove(card);
    }


    public void initBoard(){
        initIniciales();
        initTablero();
    }

    public boolean cartaElegida(){
        int spriteLocationX = 327;      //Prueba para la primera carta
        int spriteLocationY = 23;
        touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camara.unproject(touchPoint);
        Rectangle bounds = new Rectangle(spriteLocationX, spriteLocationY, 150, 225);
        System.out.println(touchPoint.x + ", " + touchPoint.y);
        if(bounds.contains(touchPoint.x, touchPoint.y)){
            return true;
        }
        return false;
    }


    public void turnoJugador(SpriteBatch batch){
        scan = new Scanner(System.in);


        int coste_monedas = 0;
        int coste_datos = 0;
        int coste_esfuerzo = 0;

        int[] estrellas = new int[10];


        if (!baraja.isEmpty()) {        //Si la baraja fuera 0, termina el juego

            //Imprimir lo que tenemos en mano
            System.out.println("\nMano: ");
            for (Card c : mano) {
                System.out.println(c.getType());
                esfuerzo_mano += c.getValor_esfuerzo();
                dato_mano += c.getValor_dato();
                moneda_mano += c.getValor_moneda();
            }

            //Imprimir el tablero + comprobar eventos
            System.out.println("\nTablero: ");
            for (int y = 0; y < tablero.size(); y++) {
                Card c = tablero.get(y);
                System.out.println((y + 1) + " " + c.getType());

                y = comproborEvento(c, y, dato_mano, esfuerzo_mano, moneda_mano);
            }

          /*  System.out.println("Txanponak: " + moneda_mano);
            System.out.println("Datuak: " + dato_mano);
            System.out.println("Ahaleginak: " + (esfuerzo_mano + esfuerzo));*/

            while (!mano.isEmpty()) {          //Si no tenemos cartas, termina el turno

                aukerak();

                if(monedas > 1 || datos > 1 || esfuerzo > 1){
                    erosi(coste_monedas, coste_datos, coste_esfuerzo);
                }

                verMano();

                System.out.println("\nZer egin nahi duzu? \n1. Jolastu. \n2. Txanda bukatu.");
                int r4 = scan.nextInt();

                elegidoPublicar = 0;
                esfuerzo = 0;
                monedas = 0;
                datos = 0;

                if (r4 == 2) {
                    publicado = false;
                    esfuerzo_mano = 0;
                    dato_mano = 0;
                    moneda_mano = 0;

                    int s = 0;
                    int m = mano.size();
                    for (int i = 0; i < m; i++) {
                        System.out.println("\n Deskartatu '" + mano.get(s).getType() + "' karta? \n1. Bai.\n2. Ez.");   //Hau raro dabil
                        int r5 = scan.nextInt();
                        if (r5 == 1) {
                            deskarte(s);
                            s--;
                        }
                        s++;

                    }


                    break;
                }
            }
            esfuerzo_mano = 0;
            dato_mano = 0;
            moneda_mano = 0;
            esfuerzo = 0;
            monedas = 0;
            datos = 0;

            //Pello
            int indice_tmp = 0;
            for (int z = 0; z < tablero.size(); z++) {
                Card q = tablero.get(z);
                if (q.getType().toString().equals("Q1") || q.getType().toString().equals("Q2") ||
                        q.getType().toString().equals("Q3") || q.getType().toString().equals("Q4")
                        || q.getType().toString().equals("Q1OA") || q.getType().toString().equals("TOP")) {
                    estrellas[z] = q.getCv_estrella();
                    tmp.add(q);
                    //System.out.println(tmp.get(indice_tmp).getType());
                    indice_tmp++;
                }

            }

            int minimo = min(estrellas);
            for (int u = 0; u < indice_tmp; u++) {
                if (minimo == tmp.get(u).getCv_estrella()) {
                    cv_pello.add(tmp.get(u));
                    puntuacion_pello += tmp.get(u).getCv_estrella();
                    tablero.remove(tmp.get(u));
                    Arrays.fill(estrellas, 0);
                    tmp.clear();
                    break;
                }
            }

            System.out.println("CV de Pello: ");
            for (Card e : cv_pello) {
                System.out.println(e.getType());
            }

            while (tablero.size() > 5) {
                System.out.println("Aukeratu ze karta kendu nahi duzun: ");
                for (int p = 0; p < tablero.size(); p++) {
                    System.out.println((p + 1) + ". " + tablero.get(p).getType());
                }
                int r6 = scan.nextInt();
                tablero.remove(r6 - 1);
            }

            boolean ultimaRonda = false;


            while ((tablero.size() < 10) && (!ultimaRonda)) {
                if (!baraja.isEmpty()) {
                    sumaCartaTablero();
                } else {
                    System.out.println("Baraja vacia");
                    ultimaRonda = true;
                }
            }


            while (mano.size() < 5) {
                robarCarta();
            }

            System.out.println("\nPuntuacion cv: " + puntuacion);
            System.out.println("Puntuacion Pello: " + puntuacion_pello);


        } else{
            if (puntuacion > puntuacion_pello){
                parttida_ganada++;
                System.out.println("\nZorionak partida irabazi duzu!!!");
            } else if (puntuacion < puntuacion_pello){
                System.out.println("\nOooohh berriz saiatu!!!");
            } else {
                System.out.println("\nBerdinketa!!!");
            }
            partida_jugada++;
        }
    }

    public void robarCarta(){
        if (!iniciales.isEmpty()) {
            mano.add(iniciales.get(0));
            iniciales.remove(0);
        } else {
            Collections.shuffle(erabilitakoak);
            iniciales.addAll(erabilitakoak);
            erabilitakoak.clear();
        }
    }

    public int comproborEvento(Card c, int y, int dato_mano, int esfuerzo_mano, int moneda_mano){

        if (c.getType().toString().equals("GIGANTE")) {
            System.out.println("\n'" + c.getType() + " +2 ahalegin dituzu!");
            esfuerzo += c.getValor_esfuerzo();
            tablero.remove(c);
            y--;
            sumaCartaTablero();
            System.out.println("\nAhalegin guztiak: " + esfuerzo);
        }

        if (c.getType().toString().equals("CUIDADOS") || c.getType().toString().equals("TUBERIA")) {
            System.out.println("\n'" + c.getType() + "' irten da bi ahalegin kendu behar dituzu, guztira: " + esfuerzo_mano);
            int j = 0;

            while (esfuerzo_mano > 0 && j < 2) {        //Elegir cartas

                System.out.println("\nAukeratu karta: ");
                int c1 = scan.nextInt();

                if (mano.get(c1 - 1).getValor_esfuerzo() != 0) {
                    j += mano.get(c1 - 1).getValor_esfuerzo();
                    esfuerzo_mano -= mano.get(c1 - 1).getValor_esfuerzo();
                    moneda_mano -= mano.get(c1 - 1).getValor_moneda();
                    erabilitakoak.add(mano.get(c1 - 1));
                    mano.remove(mano.get(c1 - 1));
                }

                for (Card card : mano) {
                    System.out.println(pos + ". " + card.getType());
                    pos++;
                }
                pos = 1;

            }
            tablero.remove(c);
            y--;
            sumaCartaTablero();
        }

        if (c.getType().toString().equals("PERDIDA")) {
            System.out.println("\n'" + c.getType() + "' irten da datu bat kendu behar duzu, guztira: " + dato_mano);
            int k = 0;
            while (dato_mano >= 1 && k < 1) {
            System.out.println("\nAukeratu karta: ");
                int c2 = scan.nextInt();
                if (mano.get(c2 - 1).getValor_dato() >= 1) {
                    k += mano.get(c2 - 1).getValor_dato();
                    dato_mano -= mano.get(c2 - 1).getValor_dato();
                    moneda_mano -= mano.get(c2 - 1).getValor_moneda();
                    erabilitakoak.add(mano.get(c2 - 1));
                    mano.remove(mano.get(c2 - 1));
                }


            }
            tablero.remove(c);
            y--;
            sumaCartaTablero();
        }

        if (c.getType().toString().equals("IMPOSTORA")) {
            System.out.println("\n'" + c.getType() + "' irten da ahalegin bat kendu behar duzu, guztira: " + esfuerzo_mano);
            int l = 0;
            while (esfuerzo_mano >= 1 && l < 1) {
                System.out.println("\nAukeratu karta: ");
                int c3 = scan.nextInt();
                if (mano.get(c3 - 1).getValor_esfuerzo() >= 1) {
                    l += mano.get(c3 - 1).getValor_esfuerzo();
                    esfuerzo_mano -= mano.get(c3 - 1).getValor_esfuerzo();
                    moneda_mano -= mano.get(c3 - 1).getValor_moneda();
                    erabilitakoak.add(mano.get(c3 - 1));
                    mano.remove(mano.get(c3 - 1));
                }


            }
            tablero.remove(c);
            y--;
            sumaCartaTablero();
        }
        return y;
    }


    public void aukerak(){
        for (int w = 0; w < mano.size(); w++) {
            Card carta = mano.get(w);

 /*           String[] aukeran = {
                    "Aukeratu",
                    "1. Txanponak bildu",
                    "2. Datuak bildu",
                    "3. Ahaleginak bildu",
                    "4. Kartak hartu",
                    "5. Argitaratu",
                    "6. Paso"
            };

            String resp = (String) JOptionPane.showInputDialog(null, "Zer egin nahi duzu '" + carta.getType() + "' kartarekin?",
                    "Aukerak", JOptionPane.QUESTION_MESSAGE, null, aukeran, aukeran[0]);

            if(resp.equalsIgnoreCase(aukeran[1])){
                if (carta.getValor_moneda() != 0) {
                    monedas += carta.getValor_moneda();
                    seleccionados[w] = 1;
                }
            } else if(resp.equalsIgnoreCase(aukeran[2])){
                if (carta.getValor_dato() != 0) {
                    datos += carta.getValor_dato();
                    seleccionados[w] = 1;
                }
            } else if(resp.equalsIgnoreCase(aukeran[3])){
                if (carta.getValor_esfuerzo() != 0) {
                    esfuerzo += carta.getValor_esfuerzo();
                    seleccionados[w] = 1;
                }
            } else if(resp.equalsIgnoreCase(aukeran[4])){
                if (carta.getValor_carta() != 0) {
                    seleccionados[w] = 1;
                    for (int g = 0; g < carta.getValor_carta(); g++) {
                        robarCarta();
                    }
                    w--;
                }
            } else if(resp.equalsIgnoreCase(aukeran[5])){
                if (publicado) {
                    System.out.println("Txandako karta bat bakarrik argitaratu daiteke.");
                    elegidoPublicar = 10;
                }
                if (++elegidoPublicar == 1) {
                    publicado = true;
                    if (carta.getCv_estrella() > 0) {
                        cv.add(carta);
                        puntuacion += carta.getCv_estrella();
                    } else if (carta.getCv_moneda() != 0) {
                        basura.add(carta);
                        monedas += carta.getCv_moneda();
                    } else if (carta.getCv_dato() != 0) {
                        basura.add(carta);
                        datos += carta.getCv_dato();
                    } else if (carta.getCv_esfuerzo() != 0) {
                        basura.add(carta);
                        esfuerzo += carta.getCv_esfuerzo();
                    } else if (carta.getCv_estrella() < 0) {
                        cv_pello.add(carta);
                        puntuacion_pello += carta.getCv_estrella();
                    }

                    mano.remove(w);
                    w--;
                }
            } else if(resp.equalsIgnoreCase(aukeran[6])){
                seleccionados[w] = 0;
            } else {
                seleccionados[w] = 0;
            }

  */
            System.out.println("\nZer egin nahi duzu '" + carta.getType() + "' kartarekin?");
            System.out.println("1. Txanponak bildu.\n2. Datuak bildu.\n3. Ahaleginak bildu.\n4. Kartak hartu.\n5. Argitaratu.\n6. Paso. ");
            int r1 = scan.nextInt();

            switch (r1) {
                case 1:
                    if (carta.getValor_moneda() != 0) {
                        monedas += carta.getValor_moneda();
                        seleccionados[w] = 1;
                    }
                    break;
                case 2:
                    if (carta.getValor_dato() != 0) {
                        datos += carta.getValor_dato();
                        seleccionados[w] = 1;
                    }
                    break;
                case 3:
                    if (carta.getValor_esfuerzo() != 0) {
                        esfuerzo += carta.getValor_esfuerzo();
                        seleccionados[w] = 1;
                    }
                    break;
                case 4:
                    if (carta.getValor_carta() != 0) {
                        seleccionados[w] = 1;
                        for (int g = 0; g < carta.getValor_carta(); g++) {
                            robarCarta();
                        }
                        w--;
                    }
                    break;
                case 5:
                    if (publicado) {
                        System.out.println("Txandako karta bat bakarrik argitaratu daiteke.");
                        elegidoPublicar = 10;
                    }
                    if (++elegidoPublicar == 1) {
                        publicado = true;
                        if (carta.getCv_estrella() > 0) {
                            cv.add(carta);
                            puntuacion += carta.getCv_estrella();
                        } else if (carta.getCv_moneda() != 0) {
                            basura.add(carta);
                            monedas += carta.getCv_moneda();
                        } else if (carta.getCv_dato() != 0) {
                            basura.add(carta);
                            datos += carta.getCv_dato();
                        } else if (carta.getCv_esfuerzo() != 0) {
                            basura.add(carta);
                            esfuerzo += carta.getCv_esfuerzo();
                        } else if (carta.getCv_estrella() < 0) {
                            cv_pello.add(carta);
                            puntuacion_pello += carta.getCv_estrella();
                        }

                        mano.remove(w);
                        w--;
                    }
                    break;
                case 6:
                    seleccionados[w] = 0;
                    break;
                default:
                    seleccionados[w] = 0;
                    break;
            }

            System.out.println("Ahora tienes: \n" + monedas + " monedas\n"      //EZ DIRE 0 IPINTZEN
                    + esfuerzo + " esfuerzo\n" + datos + " datos");
            cont++;
        }
    }

    public void verMano(){
        int indice_m = 1;

        System.out.println("\nEskuan: ");
        for (Card m : mano){
            System.out.println(indice_m + ". " + m.getType());
            indice_m++;
        }
    }

    public void erosi(int coste_monedas, int coste_datos, int coste_esfuerzo){
        int indice = 0;
        int indice_t = 1;

        System.out.println("Aukeratu ze karta erosi nahi duzun:");
        for (Card t : tablero){
            System.out.println(indice_t + ". " + t.getType());
            indice_t++;
        }
        int r3 = scan.nextInt();

        Card cartaAcomprar = tablero.get(r3 - 1);

        System.out.println("'" + cartaAcomprar.getType() + "' aukeratu duzu.");

        coste_monedas = cartaAcomprar.getCoste_monedas();
        coste_datos = cartaAcomprar.getCoste_dato();
        coste_esfuerzo = cartaAcomprar.getCoste_esfuerzo();

        if (monedas >= coste_monedas && datos >= coste_datos && esfuerzo >= coste_esfuerzo) {     //Karta bat erosi
            int k = mano.size();
            for (int i = 0; i < k; i++) {
                //System.out.println(seleccionados[i]);
                //mano.remove(seleccionados[i]);
                if (seleccionados[i] == 1) {
                    erabilitakoak.add(mano.get(indice));
                    mano.remove(indice);

                } else {
                    indice++;
                }
            }

            monedas -= coste_monedas;
            datos -= coste_datos;
            esfuerzo -= coste_esfuerzo;

            if (cartaAcomprar.getType().toString().equals("Q1") || cartaAcomprar.getType().toString().equals("Q2")
                    || cartaAcomprar.getType().toString().equals("Q3") || cartaAcomprar.getType().toString().equals("Q4")
                    || cartaAcomprar.getType().toString().equals("Q1OA") || cartaAcomprar.getType().toString().equals("TOP")) {
                cv.add(cartaAcomprar);
                puntuacion += cartaAcomprar.cv_estrella;
                tablero.remove(cartaAcomprar);
            } else {
                erabilitakoak.add(cartaAcomprar);
                tablero.remove(cartaAcomprar);
            }

        } else {
            System.out.println("No tienes suficientes recursos.");
        }
    }

    public void deskarte(int o){

        erabilitakoak.add(mano.get(o));
        mano.remove(mano.get(o));

    }


    public int min(int lista[]){

        for(int i=0;i<(lista.length-1);i++){
            for(int j=i+1;j<lista.length;j++){
                if(lista[i]>lista[j]){
                    int variableauxiliar=lista[i];
                    lista[i]=lista[j];
                    lista[j]=variableauxiliar;
                }
            }
        }

        int p = 0;
        for(int x = 0; x < lista.length; x++){
            if(lista[x] != 0){
                p = x;
                break;
            }
        }
        //System.out.println(lista[p]);
        return lista[p];
    }

    public ArrayList<Card> getIniciales() {
        return iniciales;
    }

    public ArrayList<Card> getBaraja() {
        return baraja;
    }

    public ArrayList<Card> getTablero() {
        return tablero;
    }

    public ArrayList<Card> getMano(){
        return mano;
    }

    public ArrayList<Card> getCV(){
        return cv;
    }

    public ArrayList<Card> getBasura() {
        return basura;
    }

    public ArrayList<Card> getCv_pello() {
        return cv_pello;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getPuntuacion_pello() {
        return puntuacion_pello;
    }

}
