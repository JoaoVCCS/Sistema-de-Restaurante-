package pedido;

import excecoes.ItemNaoEncontradoException;
import excecoes.MesaOcupadaException;
import excecoes.PedidoFechadoException;
import pessoas.Garcom;

import java.util.ArrayList;

public class Pedido {
    private Mesa mesa;
    private Garcom garcom;
    private boolean fechado;
    private ArrayList<ItemPedido> itens = new ArrayList<>();

    public Pedido(Mesa mesa, Garcom garcom) throws MesaOcupadaException {
        mesa.ocupar();
        this.mesa = mesa;
        this.garcom = garcom;
        this.fechado = false;
    }

    public void adicionarItem(ItemPedido itemPedido) throws PedidoFechadoException {
        verificarAberto();
        itens.add(itemPedido);
    }

    public void removerItem(String nome) throws PedidoFechadoException, ItemNaoEncontradoException {
        verificarAberto();
        boolean removeu = itens.removeIf(
                ip -> ip.getItem().getNome().equalsIgnoreCase(nome));
        if (!removeu) {
            throw new ItemNaoEncontradoException(nome);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido ip : itens) {
            total += ip.calcularSubtotal();
        }
        return total;
    }

    public void fechar() throws PedidoFechadoException {
        verificarAberto();
        fechado = true;
        mesa.liberar();
    }

    private void verificarAberto() throws PedidoFechadoException {
        if (fechado) {
            throw new PedidoFechadoException();
        }
    }

    public boolean isFechado() { return fechado; }
    public Garcom getGarcom() { return garcom; }
    public Mesa getMesa() { return mesa; }
}