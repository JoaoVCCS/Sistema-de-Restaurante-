package restaurante;

import java.util.Scanner;

import cardapio.Bebida;
import cardapio.ItemCardapio;
import cardapio.Prato;
import cardapio.Sobremesa;
import excecoes.ItemNaoEncontradoException;
import excecoes.MesaOcupadaException;
import excecoes.PedidoFechadoException;
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

            try {
                switch (opcao) {
                    case 1: cadastrarItem(); break;
                    case 2: restaurante.getCardapio().listarItens(); break;
                    case 3: buscarItem(); break;
                    case 4: removerItem(); break;
                    case 5: cadastrarMesa(); break;
                    case 6: listarMesas(); break;
                    case 7: cadastrarGarcom(); break;
                    case 8: listarFuncionarios(); break;
                    case 9: criarPedido(); break;
                    case 10: listarPedidos(); break;
                    case 11: cadastrarCozinheiro(); break;
                    case 12: cadastrarGerente(); break;
                    case 13: fecharPedido(); break;
                    case 0: System.out.println("Encerrando. Até logo!"); break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (ItemNaoEncontradoException | MesaOcupadaException | PedidoFechadoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (NumberFormatException e) {
                // precisa vir ANTES de IllegalArgumentException (é filha dela)
                System.out.println("Digite um número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Dado inválido: " + e.getMessage());
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
        System.out.println("13 - Fechar pedido");
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
                System.out.println("Bebida cadastrada.");
                break;
            case 2:
                System.out.print("Tempo de preparo (min): ");
                int tempo = Integer.parseInt(sc.nextLine());
                restaurante.getCardapio().adicionarItem(new Prato(nome, preco, desc, tempo));
                System.out.println("Prato cadastrado.");
                break;
            case 3:
                System.out.print("Vegana (true/false): ");
                boolean vegana = Boolean.parseBoolean(sc.nextLine());
                restaurante.getCardapio().adicionarItem(new Sobremesa(nome, preco, desc, vegana));
                System.out.println("Sobremesa cadastrada.");
                break;
            default:
                System.out.println("Tipo inválido.");
        }
    }

    static void buscarItem() throws ItemNaoEncontradoException {
        System.out.print("Nome do item: ");
        ItemCardapio item = restaurante.getCardapio().buscarPorNome(sc.nextLine());
        item.exibirDetalhes();
    }

    static void removerItem() throws ItemNaoEncontradoException {
        System.out.print("Nome do item para remover: ");
        restaurante.getCardapio().removerItem(sc.nextLine());
        System.out.println("Item removido com sucesso!");
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
        int n = 1;
        for (Mesa m : restaurante.getMesas()) {
            System.out.println(n++ + " - Mesa " + m.getNumeroMesa() + " | Lugares: " + m.getLugares()
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
        int n = 1;
        for (Funcionario f : restaurante.getFuncionarios()) {
            System.out.println(n++ + " - " + f.getNome()
                    + " (" + f.getClass().getSimpleName() + ")"
                    + " -> bônus: R$ " + String.format("%.2f", f.calcularBonus()));
        }
    }

    static void criarPedido() throws MesaOcupadaException {
        if (restaurante.getMesas().isEmpty() || restaurante.getFuncionarios().isEmpty()) {
            System.out.println("Cadastre ao menos uma mesa e um garçom antes.");
            return;
        }

        listarMesas();
        System.out.print("Número da mesa na lista: ");
        int m = Integer.parseInt(sc.nextLine()) - 1;

        listarFuncionarios();
        System.out.print("Número do garçom na lista de funcionários: ");
        int g = Integer.parseInt(sc.nextLine()) - 1;

        if (m < 0 || m >= restaurante.getMesas().size()
                || g < 0 || g >= restaurante.getFuncionarios().size()
                || !(restaurante.getFuncionarios().get(g) instanceof Garcom)) {
            System.out.println("Mesa ou garçom inválido.");
            return;
        }

        Pedido pedido = new Pedido(restaurante.getMesas().get(m),
                (Garcom) restaurante.getFuncionarios().get(g));
        restaurante.adicionarPedido(pedido);

        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            try {
                System.out.print("Nome do item do cardápio: ");
                ItemCardapio item = restaurante.getCardapio().buscarPorNome(sc.nextLine());
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(sc.nextLine());
                pedido.adicionarItem(new ItemPedido(item, qtd));
                System.out.println("Item adicionado!");
            } catch (ItemNaoEncontradoException | PedidoFechadoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Dado inválido: " + e.getMessage());
            }
            System.out.print("Adicionar outro item? (s/n): ");
            continuar = sc.nextLine();
        }

        System.out.println("Pedido criado. Total: R$ " + String.format("%.2f", pedido.calcularTotal()));
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
                    + " | " + (p.isFechado() ? "Fechado" : "Aberto")
                    + " | Total: R$ " + String.format("%.2f", p.calcularTotal()));
        }
    }

    static void fecharPedido() throws PedidoFechadoException {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido.");
            return;
        }
        listarPedidos();
        System.out.print("Número do pedido para fechar: ");
        int n = Integer.parseInt(sc.nextLine()) - 1;

        if (n < 0 || n >= restaurante.getPedidos().size()) {
            System.out.println("Pedido inválido.");
            return;
        }

        Pedido p = restaurante.getPedidos().get(n);
        p.fechar();
        System.out.println("Pedido fechado. Total: R$ " + String.format("%.2f", p.calcularTotal()));
    }
}