package HanoiTower;
import java.awt.*;

public class Disco {

    int tamano;
//    String color;
    Color color;
    int altura;
    int coorX;

    public Disco() {
    }

//    public Disco(int tamano, String color) {
//        this.tamano = tamano;
//        this.color = color;
//    }

    public Disco(int tamano, Color color, int altura, int coorX) {
        this.tamano = tamano;
        this.color = color;
        this.altura = altura;
        this.coorX = coorX;
    }

    @Override
    public String toString() {
        return "tamano: " + tamano + "\n color: " + color;
    }
}
