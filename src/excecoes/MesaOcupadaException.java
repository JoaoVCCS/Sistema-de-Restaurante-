package excecoes;

public class MesaOcupadaException extends Exception{
    public MesaOcupadaException(int numero){
        super("A mesa " + numero + " já está ocupada.");
    }
}
