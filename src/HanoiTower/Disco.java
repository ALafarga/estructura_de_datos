package HanoiTower;
import java.awt.*;

public class Disco {

    int largo;
//    String color;
    Color color;
    int altura;
    int coorX;

    public Disco() {
    }

//    public Disco(int largo, String color) {
//        this.largo = largo;
//        this.color = color;
//    }

    public Disco(int tamano, Color color, int altura, int coorX) {
        this.largo = tamano;
        this.color = color;
        this.altura = altura;
        this.coorX = coorX;
    }

    @Override
    public String toString() {
        return "largo: " + largo + "\n altura: " + altura + "\n coordenada X: " + coorX + "\n color: " + color.toString() + "\n";
    }
}
