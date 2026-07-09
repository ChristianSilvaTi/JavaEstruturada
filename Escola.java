import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

    static void salvarLog(String acao) {
        String caminhoLog = "historico_sistema_alunos.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoLog, true))) {
            writer.write("AÇÃO: " + acao + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico de ações: " + e.getMessage());
        }
    }

    static void salvarBoletimEmArquivo(String nome, int matricula, String materia, double[] notas, double media, double notaTotal, boolean aprovado) {
        String nomeArquivo = "boletim_" + nome.toLowerCase().replace(" ", "_") + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, false))) {
            writer.write("========================================\n");
            writer.write("           BOLETIM ESCOLAR              \n");
            writer.write("========================================\n");
            writer.write("Aluno: " + nome + "\n");
            writer.write("Matrícula: " + matricula + "\n");
            writer.write("Materia: " + materia + "\n");
            writer.write("========================================\n");

            writer.write("Notas: [ ");
            for (double nota : notas) {
                writer.write(nota + "  ");
            }
            writer.write("]\n");

            writer.write(String.format("Nota Total: %.2f\n", notaTotal));
            writer.write(String.format("Média Final: %.2f\n", media));
            writer.write("========================================\n");
            writer.write("Situação: " + (aprovado ? "APROVADO" : "REPROVADO") + "\n");
            writer.write("========================================\n");

            System.out.println("\nBoletim exportado com sucesso para: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo do boletim: " + e.getMessage());
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

    static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);

            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } else {
                System.out.println("Entrada inválida! Digite um número.");
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

    static void cadastrarAlunos(String[] alunosNome, int[] matricula, String[] materia, Scanner scanner) {
        for (int i = 0; i < alunosNome.length; i++) {
            System.out.printf("\n--- Cadastro do %02dº Aluno ---\n", i + 1);
            alunosNome[i] = lerString(scanner, "Nome: ");
            matricula[i] = lerInteiro(scanner, "Matrícula: ");
            materia[i] = lerString(scanner, "Matéria: ");
        }
    }

    static void cadastrarNotas(double[][] notasAlunos, Scanner scanner) {
        for (int i = 0; i < notasAlunos.length; i++) {
            System.out.println("\n--- Notas do Aluno " + (i + 1) + " ---");
            for (int j = 0; j < notasAlunos[i].length; j++) {
                String mensagem = "Digite a nota " + (j + 1) + ": ";
                notasAlunos[i][j] = lerDouble(scanner, mensagem);
            }
        }
    }

    static double[] calcularMedias(double[][] notasAlunos) {
        double[] listaMedias = new double[notasAlunos.length];
        for (int i = 0; i < notasAlunos.length; i++) {
            double soma = 0;
            for (int j = 0; j < notasAlunos[i].length; j++) {
                soma += notasAlunos[i][j];
            }
            if (soma > 0) {
                listaMedias[i] = soma / notasAlunos[i].length;
            } else {
                listaMedias[i] = 0;
            }
        }
        return listaMedias;
    }

    static void exibirMenorMedia(double[] listaMedias, String[] alunosNome) {
        double menor = Double.MAX_VALUE;
        int indiceMenor = 0;
        for (int i = 0; i < listaMedias.length; i++) {
            if (listaMedias[i] < menor) {
                menor = listaMedias[i];
                indiceMenor = i;
            }
        }
        System.out.printf("\nMenor média: %.2f do aluno %s\n", menor, alunosNome[indiceMenor]);
    }

    static void exibirMaiorMedia(double[] listaMedias, String[] alunosNome) {
        double maior = -Double.MAX_VALUE;
        int indiceMaior = 0;
        for (int i = 0; i < listaMedias.length; i++) {
            if (listaMedias[i] > maior) {
                maior = listaMedias[i];
                indiceMaior = i;
            }
        }
        System.out.printf("\nMaior média: %.2f do aluno %s\n", maior, alunosNome[indiceMaior]);
    }

    static void exibirBoletim(double[][] notasAlunos, String[] alunosNome, String[]materias, double[] listaMedias, int[] matricula, String busca, double limiteAprovacao, Scanner scanner) {
        boolean encontrou = false;
        int idx = 0;
        double notaTotal = 0;

        for (int i = 0; i < alunosNome.length; i++) {
            if (alunosNome[i] != null && busca.equalsIgnoreCase(alunosNome[i])) {
                idx = i;
                encontrou = true;
                break;
            }
        }

        if (encontrou) {
            System.out.println("\n========================================");
            System.out.println("Aluno: " + alunosNome[idx]);
            System.out.println("Matrícula: " + matricula[idx]);
            System.out.println("Matéria: " + materias[idx]);
            System.out.println("========================================");

            System.out.print("Notas: [ ");
            for (int j = 0; j < notasAlunos[idx].length; j++) {
                notaTotal += notasAlunos[idx][j];
                System.out.print(notasAlunos[idx][j] + "  ");
            }
            System.out.println("]");
            System.out.printf("Média: %.2f\n", listaMedias[idx]);
            System.out.println("========================================");
            System.out.printf("Nota total: %.2f\n", notaTotal);

            boolean aprovado = notaTotal >= limiteAprovacao;
            if (aprovado) {
                System.out.println(" -- APROVADO -- ");
            } else {
                System.out.println(" -- REPROVADO -- ");
            }
            System.out.println("========================================");

            String resposta = lerString(scanner, "Deseja salvar e imprimir o boletim deste aluno em arquivo? (sim/não): ").toLowerCase();
            if (resposta.startsWith("s")) {
                salvarBoletimEmArquivo(alunosNome[idx], matricula[idx], materias[idx], notasAlunos[idx], listaMedias[idx], notaTotal, aprovado);
                salvarLog("Boletim impresso em arquivo externo para o aluno: " + alunosNome[idx]);
            }
        } else {
            System.out.println("\nAluno não existe ou digitação incorreta...");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tamanho = lerInteiro(scanner, "Para começar, digite a quantidade de alunos que deseja inserir: ");
        int tamanhoNotas = lerInteiro(scanner, "Digite a quantidade de avaliações que possuem: ");
        double limiteAprovacao = lerDouble(scanner, "Digite a nota total necessária para aprovação: ");

        String[] alunosNome = new String[tamanho];
        int[] matricula = new int[tamanho];
        String[]materias = new String[tamanho];
        double[][] notasAlunos = new double[tamanho][tamanhoNotas];
        double[] listaMedias;

        cadastrarAlunos(alunosNome, matricula, materias, scanner);
        cadastrarNotas(notasAlunos, scanner);
        listaMedias = calcularMedias(notasAlunos);

        salvarLog("Sistema escolar iniciado. Alunos alocados: " + tamanho + " | Avaliações por aluno: " + tamanhoNotas);

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n========================================");
            System.out.println("            SISTEMA DE ALUNOS           ");
            System.out.println("========================================");
            System.out.println(" 1 - Médias dos alunos");
            System.out.println(" 2 - Maior média");
            System.out.println(" 3 - Menor média");
            System.out.println(" 4 - Boletim (Visualizar e Imprimir)");
            System.out.println(" 5 - Sair");
            System.out.println("========================================");

            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("\n----Médias----");
                    for (int i = 0; i < listaMedias.length; i++) {
                        System.out.printf("Aluno: %-20s | Média: %.2f\n", alunosNome[i], listaMedias[i]);
                    }
                    salvarLog("Visualização geral das médias dos alunos consultada.");
                    break;

                case 2:
                    exibirMaiorMedia(listaMedias, alunosNome);
                    salvarLog("Consulta de maior média do sistema acionada.");
                    break;

                case 3:
                    exibirMenorMedia(listaMedias, alunosNome);
                    salvarLog("Consulta de menor média do sistema acionada.");
                    break;

                case 4:
                    String busca = lerString(scanner, "Digite o nome do aluno para gerar boletim: ");
                    salvarLog("Busca por boletim acionada para o aluno: " + busca);
                    exibirBoletim(notasAlunos, alunosNome, materias, listaMedias, matricula, busca, limiteAprovacao, scanner);
                    break;

                case 5:
                    System.out.println("Encerrando o programa...");
                    salvarLog("Sistema encerrado normalmente pelo usuário.");
                    break;

                default:
                    System.out.println("\nOpção inválida. Digite um número entre 1 e 5.");
                    salvarLog("Opção inválida digitada no menu principal: " + opcao);
                    break;
            }
        }
        scanner.close();
    }