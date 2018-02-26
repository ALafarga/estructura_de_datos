package HanoiTower;

import ListaEnlazada.ListaEnlazada;
import ListaEnlazada.StackLE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class HanoiTowers extends JPanel implements Runnable
{

    /**
     * Global variables
     */
    private int n = 7;
    private int SPEED = 100;
    private int DISTANCIA_AL_BORDE = 50;
    private StackLE <Disco> A = new StackLE <Disco>();   //Torre inicial
    private int xA = 100;
    private int ALTURA_A = 50;
    private StackLE <Disco> B = new StackLE <Disco>();
    private int xB = 200;
    private int ALTURA_B = 50;
    private StackLE <Disco> C = new StackLE <Disco>();
    private int xC = 150;
    private int ALTURA_C = 50;
    private int DIFERENCIA_DE_TAMANOS = 10;
    private int TAMANO_MINIMO = 50;
    private int TAMANO_DISCOS = 10;
    private int ANGULO_RECTANGULO = 2;
//    private String [] colores = {"azul", "rojo", "amarillo", "verde", "rosa", "morado", "cafe", "negro", "nariz","gris"};
    private Color [] colores = {Color.blue, Color.red, Color.yellow, Color.green, Color.pink, Color.magenta, Color.orange, Color.black, Color.cyan, Color.white};
    private Disco disco[] = new Disco[n];  //discos a mover

    private boolean discosIniciados = false;
    private boolean torresIniciadas = false;


    public Thread runner;
//    private Timer timer = new Timer(SPEED, this);
    private Graphics g;

    public HanoiTowers(){
        super();
        this.setPreferredSize(new Dimension (700, 220));
        runner = new Thread(this);
    }

    public void setN(int n) {
        this.n = n;
        repaint();
    }

    public void paintComponent(Graphics g) {
        /*Paint pinta sobre frames y paint component pinta en sobre paneles*/
        super.paintComponent(g);

        this.g = g;
        //
        xA = this.getWidth() / 4;
        xB = 2 * this.getWidth() / 4;
        xC = 3 * this.getWidth() / 4;
        ALTURA_A = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
        ALTURA_B = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
        ALTURA_C = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;

        g.drawLine(xA, DISTANCIA_AL_BORDE, xA, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw A stack
        g.drawLine(xB, DISTANCIA_AL_BORDE, xB, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw B stack
        g.drawLine(xC, DISTANCIA_AL_BORDE, xC, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw C stack
//        iniciaDiscos(this.n, this.disco);
//        iniciaTorres(g, this.n, this.disco);
        /////////////////////////////////       ERROR               //////////////////////////////////////////

        //Error al pintar las torres dinamicamente

        pintaTorres(g);
//        timer.start();
//        hanoiTowers(g, this.n, A, C, B);
//        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);
//        imprimeTorres();
    }

    public void initFrame()
    {
        JFrame ventana = new JFrame("Torres de Hanoi");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HanoiTowers obj = new HanoiTowers();
        ventana.add(obj);
        ventana.add(new PanelControl(obj), BorderLayout.WEST);
        ventana.pack();
        ventana.setVisible(true);
    }

    private void iniciaDiscos(int n, Disco[] disco) {
        if(!discosIniciados) {
            int tamano = TAMANO_MINIMO;
            for (int i = 0; i < n; i++) {
                disco[i] = new Disco(tamano, colores[i], ALTURA_A, xA);
                tamano += DIFERENCIA_DE_TAMANOS;

            }
            discosIniciados = true;
        }
    }

    private void iniciaTorres(Graphics g, int n, Disco [] disco) {
        if(!torresIniciadas) {
            A.push(disco[n - 1]);
            disco[n - 1].altura = ALTURA_A;
            g.setColor(disco[n - 1].color);
//        g.fillRect(xA - (disco[n - 1].tamano/2), ALTURA_A, disco[n - 1].tamano, TAMANO_DISCOS);
            g.fillRoundRect(disco[n - 1].coorX - (disco[n - 1].tamano / 2),
                    disco[n - 1].altura, disco[n - 1].tamano, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_A -= TAMANO_DISCOS;
            if (n != 1) {
                iniciaTorres(g, n - 1, disco);
            }
            torresIniciadas = true;
        }
    }
    private void iniciaTorres(int n, Disco [] disco) {
            A.push(disco[n - 1]);
            disco[n - 1].altura = ALTURA_A;
//            g.setColor(disco[n - 1].color);
////        g.fillRect(xA - (disco[n - 1].tamano/2), ALTURA_A, disco[n - 1].tamano, TAMANO_DISCOS);
//            g.fillRoundRect(disco[n - 1].coorX - (disco[n - 1].tamano / 2),
//                    disco[n - 1].altura, disco[n - 1].tamano, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_A -= TAMANO_DISCOS;
            if (n != 1) {
                iniciaTorres(n - 1, disco);
            }
    }

    //    {
    //        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);
    //        timer.stop();
    //        repaint();

//    public void actionPerformed(ActionEvent e)


//    }
    public void start()
    {

        this.runner.start();
    }

    public void restart() {

    }

    public void run()
    {
        try{
            hanoiTowers(this.n, A, xA, C, xC, B, xB, g);

        }
        catch (Exception e)
        {
            System.out.println("Runnable exception "+e);
        }

    }

    private void pintaDisco(Disco tempDisco, Graphics g) {
        g.setColor(tempDisco.color);
        g.fillRoundRect(tempDisco.coorX - (tempDisco.tamano/2), tempDisco.altura,
                tempDisco.tamano, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
        repaint();
    }

    private void pintaTorres (Graphics g)
    {

        StackLE <Disco> tempA = new StackLE<Disco>();
//        tempA = A;
        StackLE <Disco> tempB = new StackLE<Disco>();
//        tempB = B;
        StackLE <Disco> tempC = new StackLE<Disco>();
//        tempC = C;
        tempA = A.duplicate();
        tempB = B.duplicate();
        tempC = C.duplicate();
        Disco tempDisco = new Disco();
//

//        StackLE <Disco> auxA = new StackLE<Disco>();
//        StackLE <Disco> auxB = new StackLE<Disco>();
//        StackLE <Disco> auxC = new StackLE<Disco>();
//        while (!tempA.isEmpty())
//        {
//            auxA.push(tempA.pop());
//        }
//        while (!tempB.isEmpty())
//        {
//            auxB.push(tempB.pop());
//        }
//        while (!tempC.isEmpty())
//        {
//            auxC.push(tempC.pop());
////        }
//        Disco temp= new Disco();
//        for(int i = 0; i < A.size(); i++)
//        {
//            temp = A.top();
//
//        }
        while(!tempA.isEmpty())
        {
            tempDisco = tempA.pop();
            pintaDisco(tempDisco, g);
//            g.fillRoundRect(tempA.top().coorX - (tempA.top().tamano/2), tempA.top().altura,
//                    tempA.top().tamano, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_A-= TAMANO_DISCOS;
        }
        while(!tempB.isEmpty())
        {
            tempDisco = tempB.pop();
            pintaDisco(tempDisco, g);
//            g.fillRoundRect(tempB.top().coorX - (tempB.top().tamano/2), tempB.top().altura,
//                    tempB.top().tamano, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_A-= TAMANO_DISCOS;
        }
        while(!tempC.isEmpty())
        {
            tempDisco = tempC.pop();
            pintaDisco(tempDisco, g);
//            g.fillRoundRect(tempC.top().coorX - (tempC.top().tamano/2), tempC.top().altura,
//                    tempC.top().tamano, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_A-= TAMANO_DISCOS;
        }

    }
    private  void imprimeTorres()
    {
        System.out.println("Torre A:");
        while(!A.isEmpty())
        {
            System.out.println(A.pop());
        }
        System.out.println("Torre B:");
        while(!B.isEmpty())
        {
            System.out.println(B.pop());
        }
        System.out.println("Torre C:");
        while(!C.isEmpty())
        {
            System.out.println(C.pop());
        }
    }

    /**
     *
     */
    void hanoiTowers()
    {
        initFrame();
        iniciaDiscos(this.n, this.disco);
        iniciaTorres(this.n, this.disco);
        //paintComponent(g);
        //paintAll(this.g);
//        xA = this.getWidth() / 4;
//        xB = 2 * this.getWidth() / 4;
//        xC = 3 * this.getWidth() / 4;
//        ALTURA_A = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
//        ALTURA_B = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
//        ALTURA_C = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
//        iniciaDiscos(this.n, this.disco);
//        iniciaTorres(g, this.n, this.disco);
//        hanoiTowers(this.n, A, C, B);
//        imprimeTorres();
    }
    //
    ////        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);
    //        timer.start();
    //    {
//    public void startHanoi()

//    }

    void hanoiTowers(int n, StackLE <Disco> desde, int desdeX, StackLE <Disco> hasta, int hastaX, StackLE <Disco> aux, int auxX, Graphics g)
    {

        if( n == 1)
        {
            System.out.println("Voy a hacer pop");
            hasta.push(desde.pop());
            System.out.println("hecho pop");
            Disco temp = new Disco();
            temp = hasta.top();
            temp.altura =  this.getHeight() - hasta.size() * TAMANO_DISCOS - DISTANCIA_AL_BORDE;
            temp.coorX = hastaX;
            pintaDisco(temp, g);
//            g.setColor(temp.color);
////            g.fillRect(xA - (hasta.top().tamano/2), ALTURA_A, hasta.top().tamano, TAMANO_DISCOS);
////            ALTURA_A -= TAMANO_DISCOS;
//            g.fillRect(temp.coorX - (temp.tamano/2), temp.altura, temp.tamano,
//                    TAMANO_DISCOS);
//            repaint();

            try
            {
                Thread.sleep(SPEED);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            hanoiTowers(n - 1, desde, desdeX, aux, auxX, hasta, hastaX, g);
            hanoiTowers(1, desde, desdeX, hasta, hastaX, aux, auxX, g);
            hanoiTowers(n - 1, aux, auxX, hasta, hastaX, desde, desdeX, g);
//            A = desde;
//            B = aux;
//            C = hasta;
        }
//        timer.stop();
    }

    public static void main(String[] args) {
        new HanoiTowers();
    }
}


