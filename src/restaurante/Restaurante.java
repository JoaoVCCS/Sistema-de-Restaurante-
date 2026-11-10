package restaurante;

import java.util.ArrayList;

import cardapio.Cardapio;
import pedido.Mesa;
import pedido.Pedido;
import pessoas.Funcionario;

public class Restaurante {
    private Cardapio cardapio = new Cardapio();
    private ArrayList<Mesa> mesas = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cardapio getCardapio() {
        return cardapio;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
}