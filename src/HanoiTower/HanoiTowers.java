package HanoiTower;

import ListaEnlazada.StackLE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HanoiTowers extends JPanel implements ActionListener
{

    /**
     * Global variables
     */
    private int n = 2;
    private int DISTANCIA_AL_BORDE = 50;
    private StackLE <Disco> A = new StackLE <Disco>();   //Torre inicial
    private int xA;
    private int ALTURA_A;
    private StackLE <Disco> B = new StackLE <Disco>();
    private int xB;
    private int ALTURA_B;
    private StackLE <Disco> C = new StackLE <Disco>();
    private int xC = 150;
    private int ALTURA_C;
    private int DIFERENCIA_DE_TAMANOS = 10;
    private int TAMANO_MINIMO = 50;
    private int TAMANO_DISCOS = 10;
//    private String [] colores = {"azul", "rojo", "amarillo", "verde", "rosa", "morado", "cafe", "negro", "nariz","gris"};
    private Color [] colores = {Color.blue, Color.red, Color.yellow, Color.green, Color.pink, Color.magenta, Color.orange, Color.black, Color.cyan, Color.white};
    private Disco disco[] = new Disco[n];  //discos a mover
    private int SPEED = 2000;
    private Timer timer = new Timer(SPEED, this);
    private Graphics g;

    public HanoiTowers(){
        super();
        this.setPreferredSize(new Dimension (700, 220));
    }

    public void setN(int n) {
        this.n = n;
        repaint();
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

    public void paintComponent(Graphics g) {
        /*Paint pinta sobre frames y paint component pinta en sobre paneles*/
        super.paintComponent(g);

        xA = this.getWidth() / 4;
        xB = 2 * this.getWidth() / 4;
        xC = 3 * this.getWidth() / 4;
        ALTURA_A = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
        ALTURA_B = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;
        ALTURA_C = this.getHeight() - DISTANCIA_AL_BORDE - TAMANO_DISCOS;

        g.drawLine(xA, DISTANCIA_AL_BORDE, xA, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw A stack
        g.drawLine(xB, DISTANCIA_AL_BORDE, xB, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw B stack
        g.drawLine(xC, DISTANCIA_AL_BORDE, xC, this.getHeight() - DISTANCIA_AL_BORDE);   //Draw C stack
//        this.pintaDiscos(g, this.n,  this.getxReglaInicio(), this.getWidth()-spaceToBorder);
        // this.pintaTriangulos(g, level, a, b, c);
        iniciaDiscos(this.n, this.disco);
        iniciaTorres(g, this.n, this.disco);
//        timer.start();
//        hanoiTowers(g, this.n, A, C, B);
//        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);
//        pintaDiscos(g);
        this.g = g;
        imprimeTorres();
    }

    public void actionPerformed(ActionEvent e)
    {
        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);

        repaint();
    }

    private void iniciaDiscos(int n, Disco[] disco) {
        int tamano = TAMANO_MINIMO;
        for(int i = 0; i < n; i++)
        {
            disco[i] = new Disco (tamano, colores[i], ALTURA_A, xA);
            tamano += DIFERENCIA_DE_TAMANOS;

        }
    }

    private  void iniciaTorres(Graphics g, int n, Disco [] disco) {
        A.push(disco[n - 1]);
        disco[n - 1].altura = ALTURA_A;
        g.setColor(disco[n - 1].color);
//        g.fillRect(xA - (disco[n - 1].tamano/2), ALTURA_A, disco[n - 1].tamano, TAMANO_DISCOS);
        g.fillRect(disco[n - 1].coorX - (disco[n - 1].tamano/2), disco[n - 1].altura, disco[n - 1].tamano, TAMANO_DISCOS);
        ALTURA_A -= TAMANO_DISCOS;
        if (n != 1)
        {
            iniciaTorres(g, n - 1, disco);
        }
    }

    private void pintaDiscos (Graphics g)
    {
        StackLE <Disco> tempA = new StackLE<Disco>();
        tempA = A;
        StackLE <Disco> tempB = new StackLE<Disco>();
        tempB = B;
        StackLE <Disco> tempC = new StackLE<Disco>();
        tempC = C;
        StackLE <Disco> auxA = new StackLE<Disco>();
        StackLE <Disco> auxB = new StackLE<Disco>();
        StackLE <Disco> auxC = new StackLE<Disco>();
        Disco tempDisco = new Disco();

        while (!tempA.isEmpty())
        {
            auxA.push(tempA.pop());
        }
        while (!tempB.isEmpty())
        {
            auxB.push(tempB.pop());
        }
        while (!tempC.isEmpty())
        {
            auxC.push(tempC.pop());
        }
        while(!auxA.isEmpty())
        {
            tempDisco = auxA.pop();
            g.drawRect(xA - (tempDisco.tamano/2), ALTURA_A, tempDisco.tamano, TAMANO_DISCOS);
            ALTURA_A-= TAMANO_DISCOS;
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
        //iniciaDiscos(this.n, this.disco);
//        iniciaTorres(, n, disco);
//        hanoiTowers(this.n, A, C, B);
//        imprimeTorres();
    }

    public void startHanoi()
    {
        timer.start();
//        hanoiTowers(g, this.n, A, xA, C, xC, B, xB);

    }

    void hanoiTowers(Graphics g, int n, StackLE <Disco> desde, int desdeX, StackLE <Disco> hasta, int hastaX, StackLE <Disco> aux, int auxX)
    {

        if( n == 1)
        {
            hasta.push(desde.pop());
            hasta.top().altura -= TAMANO_DISCOS;
            hasta.top().coorX = hastaX;
            g.setColor(hasta.top().color);
//            g.fillRect(xA - (hasta.top().tamano/2), ALTURA_A, hasta.top().tamano, TAMANO_DISCOS);
//            ALTURA_A -= TAMANO_DISCOS;
            g.fillRect(hasta.top().coorX - (hasta.top().tamano/2), hasta.top().altura, hasta.top().tamano, TAMANO_DISCOS);

            try
            {
                Thread.sleep(SPEED);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            repaint();
        }
        else
        {
            hanoiTowers(g, n - 1, desde, desdeX, aux, auxX, hasta, hastaX);
            hanoiTowers(g, 1, desde, desdeX, hasta, hastaX, aux, auxX);
            hanoiTowers(g, n - 1, aux, auxX, hasta, hastaX, desde, desdeX);
//            A = desde;
//            B = aux;
//            C = hasta;
        }
        timer.stop();
    }
}


