package restaurante;

import java.util.Scanner;

import cardapio.Bebida;
import cardapio.ItemCardapio;
import cardapio.Prato;
import cardapio.Sobremesa;
import pedido.ItemPedido;
import pedido.Mesa;
import pedido.Pedido;
import pessoas.Cozinheiro;
import pessoas.Funcionario;
import pessoas.Garcom;
import pessoas.Gerente;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Restaurante restaurante = new Restaurante();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarItem(); break;
                case 2: restaurante.getCardapio().listarItens(); break;
                case 3: buscarItem(); break;
                case 4:
                    System.out.print("Nome do item para remover: ");
                    restaurante.getCardapio().removerItem(sc.nextLine());
                    break;
                case 5: cadastrarMesa(); break;
                case 6: listarMesas(); break;
                case 7: cadastrarGarcom(); break;
                case 8: listarFuncionarios(); break;
                case 9: criarPedido(); break;
                case 10: listarPedidos(); break;
                case 11: cadastrarCozinheiro(); break;
                case 12: cadastrarGerente(); break;
                case 0: System.out.println("Encerrando. Até logo!"); break;
                default: System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }

    static void exibirMenu() {
        System.out.println("\n===== RESTAURANTE =====");
        System.out.println("1  - Cadastrar item no cardápio");
        System.out.println("2  - Listar cardápio");
        System.out.println("3  - Buscar item por nome");
        System.out.println("4  - Remover item do cardápio");
        System.out.println("5  - Cadastrar mesa");
        System.out.println("6  - Listar mesas");
        System.out.println("7  - Cadastrar garçom");
        System.out.println("8  - Listar funcionários (com bônus)");
        System.out.println("9  - Criar pedido");
        System.out.println("10 - Listar pedidos");
        System.out.println("11 - Cadastrar cozinheiro");
        System.out.println("12 - Cadastrar gerente");
        System.out.println("0  - Sair");
        System.out.print("Escolha: ");
    }

    static void cadastrarItem() {
        System.out.print("Tipo (1-Bebida, 2-Prato, 3-Sobremesa): ");
        int tipo = Integer.parseInt(sc.nextLine());
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        switch (tipo) {
            case 1:
                System.out.print("Alcoólica (true/false): ");
                boolean alc = Boolean.parseBoolean(sc.nextLine());
                restaurante.getCardapio().adicionarItem(new Bebida(nome, preco, desc, alc));
                break;
            case 2:
                System.out.print("Tempo de preparo (min): ");
                int tempo = Integer.parseInt(sc.nextLine());
                restaurante.getCardapio().adicionarItem(new Prato(nome, preco, desc, tempo));
                break;
            case 3:
                System.out.print("Vegana (true/false): ");
                boolean vegana = Boolean.parseBoolean(sc.nextLine());
                restaurante.getCardapio().adicionarItem(new Sobremesa(nome, preco, desc, vegana));
                break;
            default:
                System.out.println("Tipo inválido.");
        }
    }

    static void buscarItem() {
        System.out.print("Nome do item: ");
        ItemCardapio item = restaurante.getCardapio().buscarPorNome(sc.nextLine());
        if (item != null) {
            item.exibirDetalhes();
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    static void cadastrarMesa() {
        System.out.print("Número da mesa: ");
        int numero = Integer.parseInt(sc.nextLine());
        restaurante.adicionarMesa(new Mesa(numero));
        System.out.println("Mesa cadastrada.");
    }

    static void listarMesas() {
        if (restaurante.getMesas().isEmpty()) {
            System.out.println("Nenhuma mesa cadastrada.");
            return;
        }
        for (Mesa m : restaurante.getMesas()) {
            System.out.println("Mesa " + m.getNumeroMesa() + " | Lugares: " + m.getLugares()
                    + " | " + (m.isOcupada() ? "Ocupada" : "Livre"));
        }
    }

    static void cadastrarGarcom() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Salário: ");
        double salario = Double.parseDouble(sc.nextLine());
        System.out.print("Gorjetas: ");
        double gorjetas = Double.parseDouble(sc.nextLine());
        restaurante.adicionarFuncionario(new Garcom(nome, cpf, salario, gorjetas));
        System.out.println("Garçom cadastrado.");
    }

    static void cadastrarCozinheiro() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Salário: ");
        double salario = Double.parseDouble(sc.nextLine());
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        restaurante.adicionarFuncionario(new Cozinheiro(nome, cpf, salario, esp));
        System.out.println("Cozinheiro cadastrado.");
    }

    static void cadastrarGerente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Salário: ");
        double salario = Double.parseDouble(sc.nextLine());
        restaurante.adicionarFuncionario(new Gerente(nome, cpf, salario));
        System.out.println("Gerente cadastrado.");
    }

    static void listarFuncionarios() {
        if (restaurante.getFuncionarios().isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : restaurante.getFuncionarios()) {
            System.out.println(f.getNome() + " -> bônus: " + f.calcularBonus());
        }
    }

    static void criarPedido() {
        if (restaurante.getMesas().isEmpty() || restaurante.getFuncionarios().isEmpty()) {
            System.out.println("Cadastre ao menos uma mesa e um garçom antes.");
            return;
        }

        System.out.print("Posição da mesa na lista: ");
        int m = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Posição do garçom na lista de funcionários: ");
        int g = Integer.parseInt(sc.nextLine()) - 1;

        if (m < 0 || m >= restaurante.getMesas().size()
                || g < 0 || g >= restaurante.getFuncionarios().size()
                || !(restaurante.getFuncionarios().get(g) instanceof Garcom)) {
            System.out.println("Mesa ou garçom inválido.");
            return;
        }

        Pedido pedido = new Pedido(restaurante.getMesas().get(m),
                (Garcom) restaurante.getFuncionarios().get(g));

        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("Nome do item do cardápio: ");
            ItemCardapio item = restaurante.getCardapio().buscarPorNome(sc.nextLine());
            if (item == null) {
                System.out.println("Item não encontrado.");
            } else {
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(sc.nextLine());
                pedido.adicionarItem(new ItemPedido(item, qtd));
            }
            System.out.print("Adicionar outro item? (s/n): ");
            continuar = sc.nextLine();
        }

        restaurante.adicionarPedido(pedido);
        System.out.println("Pedido criado. Total: R$ " + pedido.calcularTotal());
    }

    static void listarPedidos() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido.");
            return;
        }
        int n = 1;
        for (Pedido p : restaurante.getPedidos()) {
            System.out.println("Pedido " + n++ + " | Mesa " + p.getMesa().getNumeroMesa()
                    + " | Garçom: " + p.getGarcom().getNome()
                    + " | Total: R$ " + p.calcularTotal());
        }
    }
}