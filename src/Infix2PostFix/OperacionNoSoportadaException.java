package Infix2PostFix;

public class OperacionNoSoportadaException extends RuntimeException {

    public OperacionNoSoportadaException()
    {
        super("La lista esta vacia");
    }

    public OperacionNoSoportadaException (String msj)
    {
        super("Esta opreacion: \"" + msj + "\" no esta soportada por esta aplicacion.");
    }
}
