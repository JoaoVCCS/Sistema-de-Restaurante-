package cardapio;

public class Prato extends ItemCardapio {
    private int tempoPreparo;

    public Prato(String nome, double preco, String descricao, int tempoPreparo) {
        super(nome, preco, descricao);
        this.tempoPreparo = tempoPreparo;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Tempo de preparo : " + this.tempoPreparo + " min");
    }
}
