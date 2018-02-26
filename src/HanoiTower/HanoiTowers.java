package HanoiTower;

import ListaEnlazada.StackLE;

import javax.swing.*;
import java.util.Stack;

public class HanoiTowers {

    /**
     * Global variables
     */
    public  StackLE <Disco> A = new StackLE <Disco>();   //Torre inicial
    public  StackLE <Disco> B = new StackLE <Disco>();
    public  StackLE <Disco> C = new StackLE <Disco>();
    private  int n;

    public  int DIFERENCIA_DE_TAMANOS = 10;
    public  int TAMANO_MINIMO = 50;
    public  String [] colores = {"azul", "rojo", "amarillo", "verde", "rosa", "morado", "cafe", "negro", "nariz","gris"};

//
//    public void initFrame()
//    {
//        JFrame ventana
//    }

    public void setNivel(int nivel) {
        this.n = nivel;
//        repaint();
    }

    private  void iniciaDiscos(int n, Disco[] disco) {
        int tamano = TAMANO_MINIMO;
        for(int i = 0; i < n; i++)
        {
            disco[i] = new Disco (tamano, colores[i]);
            tamano += DIFERENCIA_DE_TAMANOS;
        }
    }

    private  void iniciaTorres(int n, Disco [] disco) {
        if (n == 1)
        {
            A.push(disco[n - 1]);
        }
        else
        {
            A.push(disco[n - 1]);
            iniciaTorres(n - 1, disco);
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
     * @param n cantidad de discos en total
     */
    void hanoiTowers(int n)
    {
//        initFrame();
        this.n = n;
        Disco disco[] = new Disco[n];  //discos a mover
        iniciaDiscos(n, disco);
        iniciaTorres(n, disco);
        hanoiTowers(n, A, C, B);
        imprimeTorres();
    }

    void hanoiTowers(int n, StackLE desde, StackLE hasta, StackLE aux)
    {
        if( n == 1)
        {
            hasta.push(desde.pop());
        }
        else
        {
            hanoiTowers(n - 1, desde, aux, hasta);
            hanoiTowers(1, desde, hasta, aux);
            hanoiTowers(n - 1, aux, hasta, desde);
//            A = desde;
//            B = aux;
//            C = hasta;
        }
    }
}


