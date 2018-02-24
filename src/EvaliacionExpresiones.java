public class EvaliacionExpresiones {
    //TODO prioridades
    //TODO validaciones

    //Guarda los operandos
    public static StackLE<String> stack = new StackLE<>();

    //Guarda la operacion en post fijo
    public static QueueLE<String> queuePostFix = new QueueLE<>();

    /**
     *
     * @param operando1 de la operacion a realizar
     * @param operando2 de la operacion a realizar
     * @param operacion tipo de operacion a realizar
     * @return resultado de la operacion
     *
     */

    static double opera (int operando1, int operando2, String operacion) throws OperacionNoSoportadaException
    {
        double operandoAux = operando1; //cast para que todas las operaciones sean realizadas con la ALU de punto flotante
        double result;
        if (operacion.equals("*")) {
            return result = operandoAux * operando2;
        }
        if (operacion.equals("/")) {
            return result = operandoAux / operando2;
        }
        if (operacion.equals("+")) {
            return result = operandoAux + operando2;
        }
        if (operacion.equals("-")) {
            return result = operandoAux - operando2;
        }


        throw new OperacionNoSoportadaException(operacion);
    }

    /**
     * Esta funcion sirve para leer lo que esta en la operacion postfija y evaluarla
     * Hace los casteos necesarios para interpretar la operacion
     * Llama a la funcion opera que se encarga de regresar el resultado de la operacion
     * El finalizar guarda el resultado en el stack
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
            if(element.equals("*"))
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
                stack.push(String.valueOf(opera(operand1, operand2, element)));
            }
            else{
    //            stack.push("3");
                stack.push(element);
            }
        }
    }


    public static boolean esOperador(String caracter) {
        char c = caracter.charAt(0);
        return (c == '%' || c == '*' || c == '/' || c == '+' || c == '-' );
    }

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
        }
        return prioridad;
    }

    /**
     *
     * @param op1
     * @return verdadero si el operador que se esta tratando tiene mayor prioridad que el operador que se encuentra en pila.
     */
    public static boolean tieneMayorPrioridad(String op1)
    {
        int peso1 = getPrioridad(op1);
        int peso2 = getPrioridad(stack.top());
        if (peso1 == peso2){
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

    public static void popHastaAbreLlave(StackLE<String> stack, QueueLE<String> queueOperands)
    {
        while(abreLlave(queuePostFix.next()))
            queuePostFix.enqueue(stack.pop());
    }

    public static void popHastaAbreLlaveOVacio()
    {
        while(!stack.isEmpty())
            if(!abreLlave(stack.top()))
                queuePostFix.enqueue(stack.pop());
    }

    public static void inToPostFijo(String infix)
    {
        for (int i = 0; i <= infix.length()-1; i++) {
            String caracter = Character.toString(infix.charAt(i));
            if (esOperando(caracter)) {
                queuePostFix.enqueue(caracter);
            } else if(esOperador(caracter)){
                if(stack.isEmpty()){
                    stack.push(caracter);
                } else if (abreLlave(caracter)) {
                    stack.push(caracter);
                } else if (cierraLlave(caracter)) {
                    popHastaAbreLlave(stack, queuePostFix);
                } else {
                    if(tieneMayorPrioridad(caracter)){
                        stack.push(caracter);
                    } else {
                        popHastaAbreLlaveOVacio();
                        stack.push(caracter);
                    }
                }
            }
        }

//        StackLE<String> temp = new StackLE<>();
        while(!stack.isEmpty()){
//            temp.push(stack.top());
            queuePostFix.enqueue(stack.pop());
        }
        //Imprimie la expresion PostFijo que se va a evaluar
        System.out.println("PostFijo: " + queuePostFix);
//        stack = temp;
//        while(!queuePostFix.isEmpty())
//        {
//            stack.push(queuePostFix.dequeue());
//        }
        getString();
    }

    static double evalua(String expresion)
    {
//        String [] inFix= null;
//        System.out.println(expresion.length());
//        for(int i = 0; i < expresion.length(); i++)
//        {
//            System.out.print(i + " ");
//            System.out.println(expresion.charAt(i));
//            inFix[i] = Character.toString(expresion.charAt(i));
//            //getString(Character.toString(expresion.charAt(i)));
//        }
        inToPostFijo(expresion);
        return Double.parseDouble(stack.pop());
//        return 0;
    }

   public static void main(String[] args)
    {
        testInfix2PostFix.test();
    }
}
