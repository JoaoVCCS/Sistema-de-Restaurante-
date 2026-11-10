package pessoas;

public abstract class Funcionario extends Pessoa {
    private double salario;

    public Funcionario(String nome, String cpf, double salario) {
        super(nome, cpf);
        this.salario = salario;
    }

    public abstract double calcularBonus();

    public double getSalario() {
        return salario;
    }
}
