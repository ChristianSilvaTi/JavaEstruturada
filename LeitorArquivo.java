import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

    static int contarPalavras(String caminhoArquivo, int contador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    String[] palavras = linha.trim().split("\\s+");
                    contador += palavras.length;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return contador;
    }

    static int contarCaracteres(String caminhoArquivo, int contador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) != ' ') {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return contador;
    }

    static int contarLinhas(String caminhoArquivo, int contador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            while (reader.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return contador;
    }

    static void substituirPalavra(String caminhoArquivo, String palavraAntiga, String palavraNova) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean encontrou = false;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains(palavraAntiga)) {
                    System.out.println(linha.replace(palavraAntiga, palavraNova));
                    encontrou = true;
                }
            }
            if (!encontrou) {
                System.out.println("A palavra '" + palavraAntiga + "' não foi encontrada no arquivo.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    static void converterParaMinusculo(String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    static void converterParaMaiusculo(String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
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
        String caminhoDoArquivo = lerString(scanner, "Para começar, digite o caminho até seu arquivo: ");

        int opcao = 0;
        while (opcao != 7) {
            System.out.println("\n========================================");
            System.out.println("         LEITORA DE ARQUIVOS         ");
            System.out.println("========================================");
            System.out.println(" 1 - Contar linhas do arquivo");
            System.out.println(" 2 - Contar palavras do arquivo");
            System.out.println(" 3 - Contar caracteres");
            System.out.println(" 4 - Substituir palavra");
            System.out.println(" 5 - Converter para minúsculo");
            System.out.println(" 6 - Converter para maiúsculo");
            System.out.println(" 7 - Sair");
            System.out.println("========================================");

            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    int totalLinhas = contarLinhas(caminhoDoArquivo, 0);
                    System.out.println("Total de linhas encontradas: " + totalLinhas);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 2:
                    int totalPalavras = contarPalavras(caminhoDoArquivo, 0);
                    System.out.println("Total de palavras encontradas: " + totalPalavras);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 3:
                    int totalCaracteres = contarCaracteres(caminhoDoArquivo, 0);
                    System.out.println("Total de caracteres encontrados: " + totalCaracteres);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 4:
                    String palavraAlvo = lerString(scanner, "Insira a palavra que quer que seja substituída: ");
                    String palavraSubstituta = lerString(scanner, "Insira a palavra que quer usar para substituir: ");
                    substituirPalavra(caminhoDoArquivo, palavraAlvo, palavraSubstituta);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 5:
                    converterParaMinusculo(caminhoDoArquivo);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 6:
                    converterParaMaiusculo(caminhoDoArquivo);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 7:
                    System.out.println("Encerrando o programa... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha um número entre 1 e 7.");
                    break;
            }
        }
        scanner.close();
    }



