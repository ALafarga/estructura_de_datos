package HanoiTower;

import ListaEnlazada.StackLE;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionListener;

public class HanoiTowers extends JPanel implements Runnable
{

    /**
     * Global variables
     */
    //Variables para ventana
    private int SPEED = 500;
    private int DISTANCIA_AL_BORDE = 50;
    private int GLOBAL_PANEL_WIDTH = 700;
    private int GLOBAL_PANEL_HEIGHT = 220;
    //Variables para Stacks
    private int n = 7;
    private StackLE <Disco> A = new StackLE <Disco>();   //Torre inicial
    private int xA = 175;
//    private int ALTURA_PINTA_A = 50;
    private StackLE <Disco> B = new StackLE <Disco>();
    private int xB = 350;
//    private int ALTURA_PINTA_B = 50;
    private StackLE <Disco> C = new StackLE <Disco>();
    private int xC = 525;
//    private int ALTURA_PINTA_C = 50;
    //Variables disco
    private int DIFERENCIA_DE_TAMANOS = 10;
    private int ALTURA_DISCO = 10;
    private int TAMANO_INICIAL_DISCOS = 40;
    private int ANGULO_RECTANGULO = 2;
//    private String [] colores = {"azul", "rojo", "amarillo", "verde", "rosa", "morado", "cafe", "negro", "nariz","gris"};
    private Color [] colores = {Color.blue, Color.red, Color.yellow, Color.green, Color.pink, Color.magenta, Color.orange, Color.black, Color.cyan, Color.white};
    private Disco disco[] = new Disco[n];  //discos a mover

//    private boolean discosIniciados = false;

//    private boolean torresIniciadas = false;
    public Thread runner;

//    private Timer timer = new Timer(SPEED, this);
    private Graphics g;

    public HanoiTowers(){
        super();
        this.setPreferredSize(new Dimension (GLOBAL_PANEL_WIDTH, GLOBAL_PANEL_HEIGHT));
        runner = new Thread(this);
        initFrame();
        iniciaDiscos(this.n, this.disco);
        iniciaTorres(this.n, this.disco);
        repaint();
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
//        runner.interrupt();
        this.n = n;
        System.out.println("n: " + n);
        restart();
        repaint();
    }

    public int getSpeed() {
        return SPEED / 100;
    }

    public void setSpeed(int SPEED) {
        this.SPEED = (11 - SPEED) * 100;
//        this.runner.stop();
        System.out.println("Speed: " + SPEED);
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
//        ALTURA_PINTA_A = this.getHeight() - DISTANCIA_AL_BORDE - ALTURA_DISCO * A.size();
//        ALTURA_PINTA_B = this.getHeight() - DISTANCIA_AL_BORDE - ALTURA_DISCO * B.size();
//        ALTURA_PINTA_C = this.getHeight() - DISTANCIA_AL_BORDE - ALTURA_DISCO * C.size();

        g.drawLine(xA, DISTANCIA_AL_BORDE, xA, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw A stack
        g.drawLine(xB, DISTANCIA_AL_BORDE, xB, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw B stack
        g.drawLine(xC, DISTANCIA_AL_BORDE, xC, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw C stack
//        iniciaDiscos(this.n, this.disco);
//        iniciaTorres(g, this.n, this.disco);
        pintaTorres(g);
//        timer.start();
//        hanoiTowers(g, this.n, A, C, B);
//        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);
//        imprimeTorres();
    }

    public void initFrame()
    {
        JFrame ventana = new JFrame("Torres de Hanoi");
//        JPanel control = new JPanel();
//        JPanel drawPanel = new JPanel();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        HanoiTowers obj = new HanoiTowers();
        ventana.add(this);
        ventana.add(new PanelControl(this), BorderLayout.WEST);
        ventana.pack();
        ventana.setVisible(true);
    }

    private void iniciaDiscos(int n, Disco[] disco) {
//        if(!discosIniciados) {
            int tamano = TAMANO_INICIAL_DISCOS;
            for (int i = 0; i < n; i++) {
                disco[i] = new Disco(tamano,
                                    colores[i],
                                    GLOBAL_PANEL_HEIGHT - DISTANCIA_AL_BORDE - ALTURA_DISCO,
                                    xA);
                tamano += DIFERENCIA_DE_TAMANOS;

            }
//            discosIniciados = true;
//        }
    }
    //            torresIniciadas = true;
    //        }
    //            }
    //                iniciaTorres(g, n - 1, disco);
    //            if (n != 1) {
    ////            ALTURA_PINTA_A -= TAMANO_DISCOS;
    //                    disco[n - 1].altura, disco[n - 1].largo, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
    //            g.fillRoundRect(disco[n - 1].coorX - (disco[n - 1].largo / 2),
    ////        g.fillRect(xA - (disco[n - 1].largo/2), ALTURA_PINTA_A, disco[n - 1].largo, TAMANO_DISCOS);
    //            g.setColor(disco[n - 1].color);
    //            disco[n - 1].altura = ALTURA_PINTA_A;
    //            A.push(disco[n - 1]);
    //        if(!torresIniciadas) {
//    private void iniciaTorres(Graphics g, int n, Disco [] disco) {

//    }

    private void iniciaTorres(int n, Disco [] disco)
    {
        for (int i = n - 1; i >= 0; i--){
            A.push(disco[i]);
            disco[i].altura = 220 - DISTANCIA_AL_BORDE - ALTURA_DISCO * (A.size());
        }
    }

    public void start()
    {

        this.runner.start();
    }

    public void restart() {
    //        runner.interrupt();
    //        try{
    //            runner.join();
    //        }catch (Exception e){
    //            System.out.println(e);}
        this.A.flush();
        this.B.flush();;
        this.C.flush();;
        iniciaDiscos(this.n, this.disco);
        iniciaTorres(this.n, this.disco);
        repaint();
    }

    public void run()
    {
        try
        {
            hanoiTowers(this.n, A, xA, C, xC, B, xB, g);
        }
        catch (Exception e)
        {
            System.out.println("Runnable exception: " + e);
            return;
        }

    }

    private void pintaDisco(Disco tempDisco, Graphics g) {
        g.setColor(tempDisco.color);
        g.fillRoundRect(tempDisco.coorX - (tempDisco.largo /2),
                            tempDisco.altura,
                            tempDisco.largo,
                            ALTURA_DISCO,
                            ANGULO_RECTANGULO,
                            ANGULO_RECTANGULO);
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
        Disco tempDisco;
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
//            g.fillRoundRect(tempA.top().coorX - (tempA.top().largo/2), tempA.top().altura,
//                    tempA.top().largo, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_PINTA_A-= TAMANO_DISCOS;
        }
        while(!tempB.isEmpty())
        {
            tempDisco = tempB.pop();
            pintaDisco(tempDisco, g);
//            g.fillRoundRect(tempB.top().coorX - (tempB.top().largo/2), tempB.top().altura,
//                    tempB.top().largo, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_PINTA_A-= TAMANO_DISCOS;
        }
        while(!tempC.isEmpty())
        {
            tempDisco = tempC.pop();
            pintaDisco(tempDisco, g);
//            g.fillRoundRect(tempC.top().coorX - (tempC.top().largo/2), tempC.top().altura,
//                    tempC.top().largo, TAMANO_DISCOS, ANGULO_RECTANGULO, ANGULO_RECTANGULO);
//            ALTURA_PINTA_A-= TAMANO_DISCOS;
        }

    }
    //        }
    //            System.out.println(C.pop());
    //        {
    //        while(!C.isEmpty())
    //        System.out.println("Torre C:");
    //        }
    //            System.out.println(B.pop());
    //        {
    //        while(!B.isEmpty())
    //        System.out.println("Torre B:");
    //        }
    //            System.out.println(A.pop());
    //        {
    //        while(!A.isEmpty())
    //        System.out.println("Torre A:");
    //    {
    private  void imprimeTorres()
    {
        System.out.println("A:\n" + A);
        System.out.println("B:\n" + B);
        System.out.println("C:\n" + C);

    }
    /**
     *
     */
//    void hanoiTowers()
//    {
//        initFrame();
//        iniciaDiscos(this.n, this.disco);
//        //paintComponent(g);
//        //paintAll(this.g);
////        xA = this.getWidth() / 4;
////        xB = 2 * this.getWidth() / 4;
////        xC = 3 * this.getWidth() / 4;
////        ALTURA_PINTA_A = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
////        ALTURA_PINTA_B = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
////        ALTURA_PINTA_C = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
////        iniciaDiscos(this.n, this.disco);
////        iniciaTorres(g, this.n, this.disco);
////        hanoiTowers(this.n, A, C, B);
////        imprimeTorres();
//    }
    //    {
    //        timer.start();
    ////        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);
    //}


    void hanoiTowers(int n, StackLE <Disco> desde, int desdeX, StackLE <Disco> hasta, int hastaX, StackLE <Disco> aux, int auxX, Graphics g)
    {

        if( n == 1)
        {
            hasta.push(desde.pop());
            Disco temp;
            temp = hasta.top();
            temp.altura =  this.getHeight() - hasta.size()
                    * ALTURA_DISCO - DISTANCIA_AL_BORDE;
            temp.coorX = hastaX;
            pintaDisco(temp, g);
//            g.setColor(temp.color);
////            g.fillRect(xA - (hasta.top().largo/2), ALTURA_PINTA_A, hasta.top().largo, TAMANO_DISCOS);
////            ALTURA_PINTA_A -= TAMANO_DISCOS;
//            g.fillRect(temp.coorX - (temp.largo/2), temp.altura, temp.largo,
//                    TAMANO_DISCOS);
//            repaint();

            try
            {
                Thread.sleep(this.SPEED);
            }
            catch (Exception e)
            {
                System.out.println("Sleep excpection");
                e.printStackTrace();
                return;
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


