package cardapio;

public class Sobremesa extends ItemCardapio {
    private boolean vegana;

    public Sobremesa(String nome, double preco, String descricao, boolean vegana) {
        super(nome, preco, descricao);
        this.vegana = vegana;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Vegana : " + (this.vegana ? "Sim" : "Não"));
    }
}
