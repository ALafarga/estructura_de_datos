import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Esta clase consite esclusivamente de metodos estaticos que operan con Strings y una cola que guarda la expresion una
 * vez que ha sido convertida a su expresion postfijo y una pila que se utilizara para operar la expresion postfijo asi
 * como servira tambien de apoyo para guardar temporalmente los operadores
 *
 * Soporta el uso de llaves, corchetes o parentesis pero no soporta el usar un parentesis como una multiplicacion, un
 * ejemplo del funcionamiento o correcta sintaxis que puede decifrar esta clase seria la que utilizamos en una calculadora
 * convencional casio
 *
 * Puede realizar las operaciones de:
 *      suma
 *      multiplicacion
 *      resta
 *      division
 * @author Lafarga
 * @version 1.0
 * 23/feb/18
 */

//No se tomo en cuenta los parentesis como simbolo para multiplicar
public class EvaliacionExpresiones {

    //Guarda los operandos
    public static StackLE<String> stack = new StackLE<>();

    //Guarda la operacion en post fijo
    public static QueueLE<String> queuePostFix = new QueueLE<>();

    /**
     * Este metodo soporta las siguentes operaciones:
     *      *   multiplicacion
     *      /   division
     *      +   suma
     *      -   resta
     *
     * @param operando1 de la operacion a realizar
     * @param operando2 de la operacion a realizar
     * @param operacion tipo de operacion a realizar
     * @return resultado de la operacion
     *
     */
    static double opera (double operando1, double operando2, String operacion) throws OperacionNoSoportadaException
    {
        double operandoAux = operando1; //cast para que todas las operaciones sean realizadas con la ALU de punto flotante
        double result;
        if (operacion.equals("^")) {
            return result = Math.pow(operandoAux, operando2);
        }
        else if (operacion.equals("*")) {
            return result = operandoAux * operando2;
        }else if (operacion.equals("/")) {
            return result = operandoAux / operando2;
        }
        else if (operacion.equals("%")) {
            return result = (operandoAux % operando2);
        }
        else if (operacion.equals("+")) {
            return result = operandoAux + operando2;
        }
        else if (operacion.equals("-")) {
            return result = operandoAux - operando2;
        }
        throw new OperacionNoSoportadaException(operacion);
    }

    /**
     * Esta funcion sirve para leer lo que esta en la operacion postfija y evaluarla
     * Hace los casteos necesarios para interpretar la operacion
     * Llama a la funcion opera que se encarga de regresar el resultado de la operacion opera()
     * Utiliza el stack global para meter los operadores que se deben operar y el queue global para leer de ella la
     * expresion post fija
     *
     * OPERACIONES QUE SOPORTA
     * *
     * /
     * +
     * -
     */
    static void getString()
    {
        String element;
        while(!queuePostFix.isEmpty())
        {
            element = queuePostFix.dequeue();
            if(element.equals("^"))
            {
                double operand1 = Double.parseDouble(stack.pop());
                double operand2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(opera(operand2, operand1, element)));
            }
            else if(element.equals("*"))
            {
                double operand1 = Double.parseDouble(stack.pop());
                double operand2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(opera(operand1, operand2, element)));
            }
            else if(element.equals("/"))
            {
                double operand1 = Double.parseDouble(stack.pop());
                double operand2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(opera(operand2, operand1, element)));
            }
            else if(element.equals("%"))
            {
                double operand1 = Double.parseDouble(stack.pop());
                double operand2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(opera(operand2, operand1, element)));
            }
            else if(element.equals("+"))
            {
                double operand1 = Double.parseDouble(stack.pop());
                double operand2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(opera(operand1, operand2, element)));
            }
            else if(element.equals("-"))
            {
                double operand1 = Double.parseDouble(stack.pop());
                double operand2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(opera(operand2, operand1, element)));
            }
            else
                {
    //            stack.push("3");
                stack.push(element);
            }
        }
    }

    /**
     * Este metodo regresa true si el parametro es un operador y esta soportado por la clase, es decir si es *, /, +, -
     * @param caracter a evaluar si es un operador
     * @return true si el parametro es un operador *, /, + or -
     */
    public static boolean esOperador(String caracter) {
        char c = caracter.charAt(0);
        return (c == '%' || c == '*' || c == '/' || c == '+' || c == '-' || c == '^' || c == '%');
    }

    /**
     * Este metodo regresa true si el parametro es un numero
     * @param caracter a evaluar si es un operador
     * @return true si el parametro es un operador *, /, + or -
     */
    public static boolean esOperando(String caracter)
    {
        return (caracter.compareToIgnoreCase("0") > 0 && caracter.compareToIgnoreCase("9") < 0 ||
                (caracter.compareToIgnoreCase("a") > 0 && caracter.compareToIgnoreCase("z") < 0));
    }


    public static int getPrioridad(String op)
    {
        int prioridad = -1;
        char aux = op.charAt(0);
        switch (aux)
        {
            case '+':
            case '-':
                prioridad = 1;
                break;
            case '*':
            case '/':
            case '%':
                prioridad = 2;
                break;
            case '^':
                prioridad =3;
        }
        return prioridad;
    }

    /**
     * Si el elemnto a comparar y el elemento que sigue en la pila tienen la misma prioridad significa que debe de salir
     * del stack e ir a la expresion postfija por lo que se ejecuta un pop.
     *
     * @param op1 es el operador que se quiere comparar con el siguiente elemento en la pila
     * @return verdadero si el operador que se esta tratando tiene mayor prioridad que el operador que se encuentra en pila.
     */
    public static boolean tieneMayorPrioridad(String op1)
    {
        int peso1 = getPrioridad(op1);
//        if(abreLlave(stack.top()))
//            String
        int peso2 = getPrioridad(stack.top());
        if (peso1 == peso2){
            queuePostFix.enqueue(stack.pop());
            return true;
        }
        return peso1>peso2? true : false;
    }

    public static boolean cierraLlave(String caracter)
    {
        return (caracter.equals("}") || caracter.equals("]") || caracter.equals(")"));
    }

    public static boolean abreLlave(String caracter)
    {
        return (caracter.equals("{") || caracter.equals("[") || caracter.equals("("));
    }

    /**
     * Este metodo saca lo que haya en la pila hasta que encuentre el parentesis que abre la frase
     * Revisa primero si la pila esta vacia, si lo esta no necesita meter nada a la cola porque ya se metio to do lo que
     * se podia meter
     * <p>
     *     Ademas se hace un top para revisar si es la llave que buscamos antes de hacer el pop, si no es la llave que
     *     buscamos entonces se hace pop para revisar el siguiente elemento.
     * </p>
     */
    public static void popHastaAbreLlave()
    {

            do {
                if(stack.isEmpty()) {
                    break;
                }
                if (!abreLlave(stack.top()))
                    queuePostFix.enqueue(stack.top());
            } while (!abreLlave(stack.pop()));
    }

    /**
     * Metodo que convierte la expresion que el usuario introdujo a la aplicacion para hacer las operaciones y evalua
     * la misma.
     * <p>
     *     Se usan expresiones regulares para separa el string en sus elementos, elementos a utilizar en los calculos
     * </p><p>
     *     Debido a la inexperiencia con expresiones regulares se tuvieron que hacer dos comprobaciones o dos pokejokes
     *     para que el metodo fuera mas general.
     *     Se hizo una omision a los espacios y a los espacios en blanco
     *     Se hizo un reemplazo del espacio en blanco antes de un caracter
     * </p>
     * @param infix
     */
    public static void inToPostFijo(String infix)
    {
        String [] infix2pos = infix.split("(?<=[-+*/^%])|(?=[-+*/^%])|(?=[({\\[)}\\]])|(?<=[({\\[)}\\]])|\\s");
      //  System.out.println(Arrays.toString(infix2pos));
        for (int i = 0; i < infix2pos.length; i++) {
            String aux = infix2pos[i];
            if(!aux.equals("") && !aux.equals(" ")) {
                //Fallo el remover espacios antes de elementos con la expresion regular por lo que se uso esta alternativa
                aux = aux.replace(" " , "");
                if (esOperando(aux)) {
                    queuePostFix.enqueue(aux);
                } else if (esOperador(aux) || abreLlave(aux) || cierraLlave(aux)) {
                    if (stack.isEmpty()) {
                        stack.push(aux);
                    } else if (abreLlave(aux)) {
                        stack.push(aux);
                    } else if (cierraLlave(aux)) {
                        popHastaAbreLlave();
                    } else {
                        if (tieneMayorPrioridad(aux)) {
                            stack.push(aux);
                        } else {
//                        popHastaAbreLlaveOVacio();
                            popHastaAbreLlave();
                            stack.push(aux);
                        }
                    }
                }
            }
        }

        while(!stack.isEmpty()){
            queuePostFix.enqueue(stack.pop());
        }

        //Imprimie la expresion PostFijo que se va a evaluar
        System.out.println("PostFijo: " + queuePostFix);
        getString();
    }

    /**
     * Metodo principal de la clase
     * Es el encargado de llamar a la conversion infijo a postfijo y de imprimir el resultado
     * @param expresion
     * @return
     */
    static double evalua(String expresion)
    {
        inToPostFijo(expresion);
        //Variable utilizada para revizar si solo hay un elemento en el stack
        String temp = stack.pop();
        if(!stack.isEmpty())
        {
            //si hay mas de un elemento significa que la expresion que se quiere transformar no esta soportada o esta equivocada
            System.out.println("La logica de esta operacion no esta soportada o es incorrecta");
            do
            {
                stack.pop();
            }
            while (!stack.isEmpty());
        }
        stack.push(temp);
        return Double.parseDouble(stack.pop());
//        return 0;
    }
}
