package pessoas;

public class Gerente extends Funcionario{

    public Gerente(String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    @Override
    public double calcularBonus() {
        return getSalario() * 0.20;
    }
}
