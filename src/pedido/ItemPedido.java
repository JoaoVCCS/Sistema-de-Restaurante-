package pedido;

import cardapio.ItemCardapio;

public class ItemPedido {
    private ItemCardapio item;
    private int quantidade;

    public ItemPedido(ItemCardapio item, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.item = item;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return item.getPreco() * quantidade;
    }

    public ItemCardapio getItem() {
        return item;
    }
}