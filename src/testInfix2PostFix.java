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

//        stack.push("+");
//        stack.push("(");
//        stack.push("/")
        String expresionInfixToTransform = "1+2*3-4*5";
        System.out.println("Expresion a evaluar: " + expresionInfixToTransform);
        System.out.println("Resultado esperado: " +  (1+2*3-4*5));
        //test infijo
        //quitar test cuando se evalua pues en la evaluacion ya se corre este metodo y este metodo saca todo del stack
//        System.out.print("InToPostFijo test: ");
//        inToPostFijo(expresionInfixToTransform);
//        System.out.println(stackTest.pop());
        System.out.println("Evalua: " + eval.evalua(expresionInfixToTransform));
        System.out.println();

    }
}
