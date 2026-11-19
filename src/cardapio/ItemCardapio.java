package cardapio;

public abstract class ItemCardapio {
    private String nome;
    private double preco;
    private String descricao;

    public ItemCardapio(String nome, double preco, String descricao) {
        setNome(nome);
        setPreco(preco);
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

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }
        this.preco = preco;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.nome = nome;
    }
}