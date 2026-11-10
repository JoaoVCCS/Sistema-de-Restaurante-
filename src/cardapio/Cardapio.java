package cardapio;

import java.util.ArrayList;

public class Cardapio {
    private ArrayList<ItemCardapio> listaItens = new ArrayList<>();

    public void adicionarItem(ItemCardapio item) {
        listaItens.add(item);
    }

    public void removerItem(String nome) {
        boolean removeu = listaItens.removeIf(item -> item.getNome().equalsIgnoreCase(nome));
        System.out.println(removeu ? "Item removido com sucesso!" : "Item não encontrado.");
    }

    public void listarItens() {
        if (listaItens.isEmpty()) {
            System.out.println("O cardápio está vazio!");
            return;
        }

        for (ItemCardapio item : listaItens) {
            item.exibirDetalhes();
            System.out.println("-----------------");
        }
    }

    public ItemCardapio buscarPorNome(String nome) {
        for (ItemCardapio item : listaItens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }
}