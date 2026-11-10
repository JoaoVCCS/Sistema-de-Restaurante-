package cardapio;

public class Bebida extends ItemCardapio {
    private boolean alcoolica;

    public Bebida(String nome, double preco, String descricao, boolean alcoolica) {
        super(nome, preco, descricao);
        this.alcoolica = alcoolica;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Alcoolica : " + (this.alcoolica ? "Sim" : "Não"));
    }
}
