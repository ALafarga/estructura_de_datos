public class testInfix2PostFix {
    public static void test ()
    {
        EvaliacionExpresiones eval = new EvaliacionExpresiones();
        String a = String.valueOf(1);
        a = "(";
        System.out.println("Is "+ a +" an operand: " + eval.esOperando(a));
        System.out.println("Is "+ a +" an operator: " + eval.esOperador(a));
        System.out.println();

        String b = "+";
        b = "*";
        System.out.println("La prioridad del operando " + b + " es: " + eval.getPrioridad(b));
        System.out.println();

        String c = "-";
        eval.stack.push(b);
//        c = "/";
        System.out.println("Entre " + c + " y " + b + " el que tiene mayor prioridad es "+ c + " : " + eval.tieneMayorPrioridad(c));
        eval.stack.pop();
        System.out.println();

        String d = "}";
        System.out.println("El string " + d + " abre una llave: " + eval.abreLlave(d));
        System.out.println("El string " + d + " cierra una llave: " + eval.cierraLlave(d));
        System.out.println();

        //test infijo
        //quitar test cuando se evalua pues en la evaluacion ya se corre este metodo y este metodo saca todo del stack
//        System.out.print("InToPostFijo test: ");
//        inToPostFijo(expresionInfixToTransform);
//        System.out.println(stackTest.pop());

        String expresionInfixToTransform = "((1+2)*3-4)*5"; //1 2 + 3 * 4 - 5 *
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 1 2 + 3 * 4 - 5 *");
        System.out.println("Resultado esperado: " +  ((((double)1+(double)2)*(double)3-(double)4)*(double)5));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println("");

        expresionInfixToTransform = "6/2*(2+1)";    //6 2 / 2 1 + *
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 6 2 / 2 1 + *");
        System.out.println("Resultado esperado: " +  ((double)6/(double)2*((double)2+(double)1)));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "6/2(2+1)";    //6 2 2 1 + /
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 6 2 2 1 + /");
        System.out.println("Resultado esperado: " +  ((double)6/(double)2*((double)2+(double)1)));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "3+4*5/6";    //3 4 5 * 6 / +
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 3 4 5 * 6 / +");
        System.out.println("Resultado esperado: " +  ((double)3+(double)4*(double)5/(double)6));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "10 + 3 * 5 / (16 - 4)";    //10.2 3.4 5 * 16 4 - / +
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 10.2 3.4 5 * 16 4 - / +");
        System.out.println("Resultado esperado: " +  ((double)10 + (double)3 * (double)5 / ((double)16 - (double)4)));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "10.2 + 3.4 * 5 / (16 - 4)";    //10.2 3.4 5 * 16 4 - / +
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 10.2 3.4 5 * 16 4 - / +");
        System.out.println("Resultado esperado: " +  ((10.2 + 3.4 * (double)5 / ((double)16 - (double)4))));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "(300+23)*(43-21)/(84+7)";    //300 23 + 43 21 - * 84 7 + /
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 300 23 + 43 21 - * 84 7 + /");
        System.out.println("Resultado esperado: " +  (((double)300+(double)23)*((double)43-(double)21)/((double)84+(double)7)));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "(4+8)*(6-5)/((3-2)*(2+2))";    // 4 8 + 6 5 - * 3 2 – 2 2 + * /
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada:  4 8 + 6 5 - * 3 2 – 2 2 + * /");
        System.out.println("Resultado esperado: " +  (((double)4+(double)8)*((double)6-(double)5)/(((double)3-(double)2)*((double)2+(double)2))));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "2^3";    //2 3 ^
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada:  2 3 ^");
        System.out.println("Resultado esperado: " +  (Math.pow((double)2,(double)3)));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "14%3";    //14 3 %
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 14 3 %");
        System.out.println("Resultado esperado: " +  ((double)14%3));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

        expresionInfixToTransform = "20%3";    //20 3 %
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Expresion en post fijo esperada: 20 3 %");
        System.out.println("Resultado esperado: " +  ((double)20%3));
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();
    }

    public static void main(String[] args)
    {
        test();
    }
}
