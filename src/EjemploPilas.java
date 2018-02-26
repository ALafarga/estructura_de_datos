import ListaEnlazada.StackLE;

public class EjemploPilas {

    public static void borrarValor(StackLE<Integer> pila, int valor) {
        StackLE<Integer> temp = new StackLE<Integer>();
        while (!pila.isEmpty()) {
            int data = pila.pop();
            if (data != valor) {
                temp.push(data);
            }
        }
        while (!temp.isEmpty()) {
            int data = temp.pop();
            pila.push(data);
        }
    }

    public static StackLE<Integer> clonarPila(StackLE<Integer> pila) {
        StackLE<Integer> temp = new StackLE<Integer>();
        StackLE<Integer> clone = new StackLE<Integer>();
        while (!pila.isEmpty()) {
            int data = pila.pop();
            temp.push(data);
        }
        while (!temp.isEmpty()) {
            int data = temp.pop();
            clone.push(data);
            pila.push(data);
        }
        return clone;
    }

    public static void main(String[] args) {
        StackLE<Integer> pila0 = new StackLE<Integer>();
        pila0.push(55);
        pila0.push(14);
        pila0.push(35);
        pila0.push(1002);
        pila0.push(300);
        pila0.push(764);
        pila0.push(981);
        pila0.push(142);
        pila0.push(1232);
        pila0.push(1432);
        pila0.push(123);
        System.out.println(pila0);
        StackLE<Integer> pila1 = clonarPila(pila0);
        System.out.println(pila1);
        borrarValor(pila0, 1002);
        System.out.println(pila0);
    }
}


