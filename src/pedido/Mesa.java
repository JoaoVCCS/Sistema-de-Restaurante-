package pedido;

public class Mesa {
    private int numeroMesa;
    private int lugares;
    private boolean ocupada;

    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.lugares = 4;
        this.ocupada = false;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public int getLugares() {
        return lugares;
    }

    public boolean isOcupada() {
        return ocupada;
    }
}