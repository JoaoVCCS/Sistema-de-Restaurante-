package pessoas;

public class Cozinheiro extends Funcionario {
    private String especialidade;

    public Cozinheiro(String nome, String cpf, double salario, String especialidade) {
        super(nome, cpf, salario);
        this.especialidade = especialidade;
    }

    @Override
    public double calcularBonus() {
        return getSalario() * 0.05;
    }
}