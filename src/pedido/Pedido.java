package pedido;

import pessoas.Garcom;

import java.util.ArrayList;

public class Pedido {
    private Mesa mesa;
    private boolean status = false;
    private Garcom garcom;

    private ArrayList<ItemPedido> pedidos = new ArrayList<>();

    public Pedido(Mesa mesa, Garcom garcom) {
        this.mesa = mesa;
        this.garcom = garcom;
    }

    public void adicionarItem(ItemPedido itemPedido) {
        pedidos.add(itemPedido);
    }

    public void removerItem(String nome) {
        boolean removeu = pedidos.removeIf(ip -> ip.getItem().getNome().equalsIgnoreCase(nome));
        System.out.println(removeu ? "Item removido do pedido." : "Item não encontrado no pedido.");
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido ip : pedidos) {
            total += ip.calcularSubtotal();
        }
        return total;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public Mesa getMesa() {
        return mesa;
    }
}