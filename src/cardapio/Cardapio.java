package cardapio;

import excecoes.ItemNaoEncontradoException;

import java.util.ArrayList;

public class Cardapio {
    private ArrayList<ItemCardapio> listaItens = new ArrayList<>();

    public void adicionarItem(ItemCardapio item) {
        listaItens.add(item);
    }

    public void removerItem(String nome) throws ItemNaoEncontradoException {
        boolean removeu = listaItens.removeIf(item -> item.getNome().equalsIgnoreCase(nome));
        if (!removeu) {
            throw new ItemNaoEncontradoException(nome);
        }
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

    public ItemCardapio buscarPorNome(String nome) throws ItemNaoEncontradoException {
        for (ItemCardapio item : listaItens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException(nome);
    }
}