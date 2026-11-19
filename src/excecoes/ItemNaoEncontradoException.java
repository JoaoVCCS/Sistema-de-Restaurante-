package excecoes;

public class ItemNaoEncontradoException extends Exception{
    public ItemNaoEncontradoException(String nome){
        super("Item não encontrado no cardápio: " + nome);
    }
}


