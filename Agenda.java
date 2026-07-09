import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



    static String[][] adicionarContato(String[][] agenda, String novoContato, String novoTelefone) {
        String[][] novosContatos = new String[agenda.length + 1][2];
        for (int i = 0; i < agenda.length; i++) {
            novosContatos[i][0] = agenda[i][0];
            novosContatos[i][1] = agenda[i][1];
        }
        novosContatos[agenda.length][0] = novoContato;
        novosContatos[agenda.length][1] = novoTelefone;
        System.out.println("Contato adicionado com sucesso!");
        return novosContatos;
    }

    static String[][] removerContato(String[][] agenda, String contatoRemover) {
        boolean encontrou = false;
        int indiceRemover = -1;
        for (int i = 0; i < agenda.length; i++) {
            if ((agenda[i][0] != null && agenda[i][0].equalsIgnoreCase(contatoRemover)) ||
                    (agenda[i][1] != null && agenda[i][1].equalsIgnoreCase(contatoRemover))) {
                indiceRemover = i;
                encontrou = true;
                break;
            }
        }

        if (!encontrou) {
            System.out.println("Contato não encontrado para remoção.");
            return agenda;
        }

        String[][] novaAgendaReduzida = new String[agenda.length - 1][2];
        int novaPosicao = 0;

        for (int i = 0; i < agenda.length; i++) {
            if (i != indiceRemover) {
                novaAgendaReduzida[novaPosicao][0] = agenda[i][0];
                novaAgendaReduzida[novaPosicao][1] = agenda[i][1];
                novaPosicao++;
            }
        }

        System.out.println("Contato removido com sucesso e tamanho da agenda reduzido!");
        return novaAgendaReduzida;
    }

    static void editarContato(String[][] agenda, String contatoEditar, Scanner scanner) {
        boolean encontrou = false;
        for (int i = 0; i < agenda.length; i++) {
            if ((agenda[i][0] != null && agenda[i][0].equalsIgnoreCase(contatoEditar)) ||
                    (agenda[i][1] != null && agenda[i][1].equalsIgnoreCase(contatoEditar))) {
                agenda[i][0] = lerString(scanner, "Insira o novo nome: ");
                agenda[i][1] = lerString(scanner, "Insira o novo telefone: ");
                System.out.println("Contato editado com sucesso!");
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            System.out.println("Contato não encontrado para a edição!");
        }
    }

    static void buscarContato(String termoBusca, String[][] agenda) {
        boolean encontrou = false;
        for (int i = 0; i < agenda.length; i++) {
            if (agenda[i][0] != null && agenda[i][0].equalsIgnoreCase(termoBusca)) {
                System.out.println("Encontrado! Nome: " + agenda[i][0] + " | Telefone: " + agenda[i][1]);
                encontrou = true;
            } else if (agenda[i][1] != null && agenda[i][1].equals(termoBusca)) {
                System.out.println("Encontrado! Nome: " + agenda[i][0] + " | Telefone: " + agenda[i][1]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Contato não encontrado!");
        }
    }

    static void salvarLog(String acao) {
        String caminhoLog = "historico_acoes.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoLog, true))) {
            writer.write("AÇÃO: " + acao + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico de ações: " + e.getMessage());
        }
    }

    static void salvarContatoEmArquivo(String[][] agenda, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            int contagemValidos = 0;
            for (int i = 0; i < agenda.length; i++) {
                if (agenda[i][0] != null && agenda[i][1] != null) {
                    writer.write("Nome: " + agenda[i][0] + " | Telefone: " + agenda[i][1] + "\n");
                    contagemValidos++;
                }
            }
            System.out.println("Sucesso! " + contagemValidos + " contatos foram salvos em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar contatos em arquivo: " + e.getMessage());
        }
    }

    static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Erro: O número não pode ser negativo.");
            } else {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                scanner.nextLine();
            }
        }
    }

    static String lerString(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Erro: Este campo não pode ficar em branco.");
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[][] agenda = new String[0][2];
        int opcao = 0;

        salvarLog("Sistema iniciado pelo usuário.");

        while (opcao != 6) {
            System.out.println("\n======================================");
            System.out.println("              SISTEMA DE AGENDA         ");
            System.out.println("========================================");
            System.out.println(" 1 - Adicionar contato");
            System.out.println(" 2 - Remover contato (Diminui o tamanho)");
            System.out.println(" 3 - Editar contato");
            System.out.println(" 4 - Buscar contato");
            System.out.println(" 5 - Salvar contatos em arquivo");
            System.out.println(" 6 - Sair");
            System.out.println("========================================");
            System.out.println("Contatos atualmente na memória: " + agenda.length);
            System.out.println("========================================");

            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    String novoContato = lerString(scanner, "Digite o nome que deseja adicionar: ");
                    String novoTelefone = lerString(scanner, "Digite o telefone que deseja adicionar: ");
                    agenda = adicionarContato(agenda, novoContato, novoTelefone);
                    salvarLog("Contato adicionado -> Nome: " + novoContato + " | Telefone: " + novoTelefone);
                    break;

                case 2:
                    String contatoRemover = lerString(scanner, "Digite o nome ou telefone do contato que deseja excluir: ");
                    agenda = removerContato(agenda, contatoRemover);
                    salvarLog("Remoção de contato executada para o termo: " + contatoRemover);
                    break;

                case 3:
                    String contatoEditar = lerString(scanner, "Digite o nome ou telefone do contato que deseja editar: ");
                    editarContato(agenda, contatoEditar, scanner);
                    salvarLog("Edição acionada para o termo: " + contatoEditar);
                    break;

                case 4:
                    String busca = lerString(scanner, "Digite o nome ou telefone para buscar: ");
                    buscarContato(busca, agenda);
                    salvarLog("Busca realizada pelo termo: " + busca);
                    break;

                case 5:
                    String caminhoArquivo = lerString(scanner, "Digite o nome/caminho do arquivo de destino (ex: contatos.txt): ");
                    salvarContatoEmArquivo(agenda, caminhoArquivo);
                    salvarLog("Contatos salvos no arquivo externo: " + caminhoArquivo);
                    break;

                case 6:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    salvarLog("Sistema encerrado com sucesso.");
                    break;

                default:
                    System.out.println("\nOpção inexistente. Tente um número entre 1 e 6.");
                    break;
            }
        }
        scanner.close();
    }


