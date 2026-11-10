package pessoas;

public class Cliente extends Pessoa{
    private String celular;

    public Cliente(String nome, String cpf, String celular) {
        super(nome, cpf);
        this.celular = celular;
    }
}
