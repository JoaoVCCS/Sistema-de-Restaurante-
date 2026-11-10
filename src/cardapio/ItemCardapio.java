package cardapio;

public abstract class ItemCardapio {
    private String nome;
    private double preco;
    private String descricao;

    public ItemCardapio(String nome, double preco, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public void exibirDetalhes() {
        System.out.println("Nome : " + this.nome);
        System.out.println("Preço : " + this.preco);
        System.out.println("Descrição : " + this.descricao);
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }
}