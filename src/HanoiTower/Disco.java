package HanoiTower;

public class Disco {

    int tamano;
    String color;

    public Disco(int tamano, String color) {
        this.tamano = tamano;
        this.color = color;
    }

    @Override
    public String toString() {
        return "tamano: " + tamano + "\n color: " + color;
    }
}
