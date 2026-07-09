import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

    static void salvarLog(String acao) {
        String caminhoLog = "historico_biblioteca.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoLog, true))) {
            writer.write("AÇÃO: " + acao + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico de ações: " + e.getMessage());
        }
    }

    static void salvarLivrosEmArquivo(String[] livros, int[] qtd, String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            writer.write("=== ACERVO DA BIBLIOTECA ===\n");
            int cadastrados = 0;
            for (int i = 0; i < livros.length; i++) {
                if (livros[i] != null) {
                    writer.write("Título/Autor: " + livros[i] + " | Quantidade em Estoque: " + qtd[i] + "\n");
                    cadastrados++;
                }
            }
            writer.write("============================\n");
            writer.write("Total de títulos distintos: " + cadastrados + "\n");
            System.out.println("\nAcervo exportado com sucesso para: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o acervo de livros: " + e.getMessage());
        }
    }


    static int lerInteiro(Scanner read, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (read.hasNextInt()) {
                int valor = read.nextInt();
                read.nextLine();
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Erro: O número não pode ser negativo.");
            } else {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                read.nextLine();
            }
        }
    }

    static String lerString(Scanner read, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = read.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Erro: Este campo não pode ficar em branco.");
        }
    }


    static void cadastroDeLivros(String[] livros, String livro, int[] qtd, int qtdLivro) {
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null && livros[i].equalsIgnoreCase(livro)) {
                System.out.println("\nLivro já existente! Quantidade aumentada.");
                qtd[i] += qtdLivro;
                return;
            }
        }

        for (int i = 0; i < livros.length; i++) {
            if (livros[i] == null) {
                livros[i] = livro;
                qtd[i] = qtdLivro;
                System.out.println("\nNovo livro cadastrado com sucesso!");
                return;
            }
        }
        System.out.println("\nErro: Memória do sistema cheia! Não foi possível cadastrar.");
    }

    static void emprestimo(String[] livros, String busca, int[] qtd, Scanner read) {
        boolean buscar = false;
        boolean existeMasSemEstoque = false;

        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null) {
                if (livros[i].equalsIgnoreCase(busca)) {
                    if (qtd[i] > 0) {
                        System.out.println("\nLivro emprestado com sucesso! ");
                        qtd[i]--;
                        salvarLog("Empréstimo direto efetuado -> Título: " + livros[i]);
                        return;
                    } else {
                        existeMasSemEstoque = true;
                    }
                } else if (livros[i].toLowerCase().contains(busca.toLowerCase())) {
                    System.out.println("\nTítulo semelhante encontrado: " + livros[i]);
                    buscar = true;
                }
            }
        }

        if (buscar) {
            String escolha = lerString(read, "\nDigite o nome exato do título desejado listado acima: ");
            for (int j = 0; j < livros.length; j++) {
                if (livros[j] != null && escolha.equalsIgnoreCase(livros[j])) {
                    if (qtd[j] > 0) {
                        System.out.println("\nLivro emprestado com sucesso! ");
                        qtd[j]--;
                        salvarLog("Empréstimo por busca semelhante efetuado -> Título: " + livros[j]);
                        return;
                    } else {
                        existeMasSemEstoque = true;
                    }
                }
            }
        }

        if (existeMasSemEstoque) {
            System.out.println("\nImpossível realizar empréstimo! O livro está indisponível no estoque no momento.");
            salvarLog("Tentativa de empréstimo falhou (Sem estoque) para o termo: " + busca);
        } else {
            System.out.println("\nImpossível realizar empréstimo! Livro não cadastrado no sistema.");
            salvarLog("Tentativa de empréstimo falhou (Não cadastrado) para o termo: " + busca);
        }
    }

    static void devolucoes(String[] livros, String livro, int[] qtd) {
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null && livros[i].equalsIgnoreCase(livro)) {
                qtd[i]++;
                System.out.println("\nDevolução feita com sucesso!");
                salvarLog("Devolução realizada para o livro: " + livros[i]);
                return;
            }
        }
        System.out.println("\nEsse livro não pertence à biblioteca.");
        salvarLog("Tentativa de devolução inválida para o termo: " + livro);
    }

    static void pesquisaPorAutor(String[] livros, String autor, int[] qtd) {
        boolean achou = false;
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null && autor != null) {
                if (livros[i].toLowerCase().contains(autor.toLowerCase())) {
                    System.out.println("Livro encontrado: " + livros[i] + " | Estoque: " + qtd[i]);
                    achou = true;
                }
            }
        }
        if (!achou) {
            System.out.println("\nNenhum livro correspondente a esse autor foi encontrado.");
        }
    }

    static void PesquisaPorTitulo(String[] livros, String titulo) {
        boolean achou = false;
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null && titulo != null) {
                if (livros[i].toLowerCase().contains(titulo.toLowerCase())) {
                    System.out.println("Livro encontrado: " + livros[i]);
                    achou = true;
                }
            }
        }
        if (!achou) {
            System.out.println("\nNenhum livro com esse título foi encontrado.");
        }
    }

    static void ControleDeQuantidade(String[] livros, int[] qtd, String livro, Scanner read) {
        boolean buscar = false;

        if (livro != null) {
            livro = livro.toLowerCase();
        }

        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null) {
                String livroAtual = livros[i].toLowerCase();
                if (livroAtual.equalsIgnoreCase(livro)) {
                    String op = lerString(read, "\nTítulo encontrado: " + livros[i] + "\nDeseja alterar a quantidade? (sim/não): ").toLowerCase();

                    if (op.startsWith("s")) {
                        int novaQtd = lerInteiro(read, "\nDigite a nova quantidade: ");
                        int qtdAntiga = qtd[i];
                        qtd[i] = novaQtd;
                        System.out.println("\nQuantidade alterada com sucesso!");
                        salvarLog("Controle de Estoque direto -> " + livros[i] + " alterado de " + qtdAntiga + " para " + novaQtd);
                    } else {
                        System.out.println("\nProcedimento cancelado.");
                        salvarLog("Controle de Estoque cancelado para o livro: " + livros[i]);
                    }
                    return;
                } else if (livroAtual.contains(livro)) {
                    System.out.println("\nTítulos encontrados: " + livros[i]);
                    buscar = true;
                }
            }
        }

        if (buscar) {
            String escolha = lerString(read, "\nDigite o nome do livro que deseja alterar do grupo listado: ");
            for (int j = 0; j < livros.length; j++) {
                if (livros[j] != null && escolha.equalsIgnoreCase(livros[j])) {
                    int novaQtd = lerInteiro(read, "\nInforme a nova quantidade: ");
                    int qtdAntiga = qtd[j];
                    qtd[j] = novaQtd;
                    System.out.println("\nQuantidade alterada com sucesso!");
                    salvarLog("Controle de Estoque por listagem -> " + livros[j] + " alterado de " + qtdAntiga + " para " + novaQtd);
                    return;
                }
            }
        }

        System.out.println("\nNão encontramos o livro informado no sistema.");
        salvarLog("Tentativa de alteração de estoque falhou. Livro não encontrado: " + livro);
    }


    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("--------------- Bem-vindo -------------");

        int tamanho = lerInteiro(read, "Para começar, digite o limite de armazenamento da biblioteca: ");
        while (tamanho <= 0) {
            System.out.println("Erro: O limite precisa ser maior que 0.");
            tamanho = lerInteiro(read, "Digite um limite válido: ");
        }

        String[] livros = new String[tamanho];
        int[] quantidades = new int[tamanho];

        int op = 0;
        salvarLog("Sistema de biblioteca iniciado. Capacidade alocada: " + tamanho);

        while (op != 8) {
            System.out.println("\n========================================");
            System.out.println("     SISTEMA DE BIBLIOTECA       ");
            System.out.println("========================================");
            System.out.println(" 1 - Cadastrar livros");
            System.out.println(" 2 - Buscar por autor");
            System.out.println(" 3 - Buscar por título");
            System.out.println(" 4 - Devoluções");
            System.out.println(" 5 - Empréstimos");
            System.out.println(" 6 - Controle de quantidade");
            System.out.println(" 7 - Salvar todos os livros em arquivo");
            System.out.println(" 8 - Sair");
            System.out.println("========================================");

            op = lerInteiro(read, "Escolha uma opção: ");

            switch (op) {
                case 1:
                    String tituloCad = lerString(read, "Digite o título e autor que deseja cadastrar: ");
                    int qtdLivro = lerInteiro(read, "Digite a quantidade de cópias: ");
                    cadastroDeLivros(livros, tituloCad, quantidades, qtdLivro);
                    salvarLog("Tentativa/Sucesso de cadastro -> Título: " + tituloCad + " | Cópias: " + qtdLivro);
                    break;

                case 2:
                    String buscaAutor = lerString(read, "Digite o nome do autor que deseja buscar: ");
                    pesquisaPorAutor(livros, buscaAutor, quantidades);
                    salvarLog("Pesquisa por autor realizada com o termo: '" + buscaAutor + "'");
                    break;

                case 3:
                    String buscaTitulo = lerString(read, "Digite o título (ou termo) que deseja buscar: ");
                    PesquisaPorTitulo(livros, buscaTitulo);
                    salvarLog("Pesquisa por título realizada com o termo: '" + buscaTitulo + "'");
                    break;

                case 4:
                    String devLivro = lerString(read, "Digite o título exato do livro a ser devolvido: ");
                    devolucoes(livros, devLivro, quantidades);
                    break;

                case 5:
                    String empLivro = lerString(read, "Digite o título ou autor para o empréstimo: ");
                    emprestimo(livros, empLivro, quantidades, read);
                    break;

                case 6:
                    String contLivro = lerString(read, "Digite o nome do livro para gerenciar estoque: ");
                    ControleDeQuantidade(livros, quantidades, contLivro, read);
                    break;

                case 7:
                    String caminhoArquivo = "acervo_livros.txt";
                    salvarLivrosEmArquivo(livros, quantidades, caminhoArquivo);
                    salvarLog("Todo o acervo da biblioteca foi exportado para o arquivo: " + caminhoArquivo);
                    break;

                case 8:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    salvarLog("Sistema encerrado normalmente pelo usuário.");
                    break;

                default:
                    System.out.println("\nOpção inexistente. Tente um número entre 1 e 8.");
                    salvarLog("Usuário inseriu uma opção inválida no menu principal: " + op);
                    break;
            }
        }
        read.close();
    }
