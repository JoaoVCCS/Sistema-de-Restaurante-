package excecoes;

public class PedidoFechadoException extends Exception{
    public PedidoFechadoException(){
        super("Não é possível alterar um pedido já fechado.");
    }
}
