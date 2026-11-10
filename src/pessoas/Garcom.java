package pessoas;

public class Garcom extends Funcionario{
    private double gorjetas;

    public Garcom(String nome, String cpf, double salario, double gorjetas) {
        super(nome, cpf, salario);
        this.gorjetas = gorjetas;
    }


    @Override
    public double calcularBonus() {
        return gorjetas * 0.10 + getSalario() * 0.02;
    }
}
