import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

    static void inicializarMapa(String[][] poltronas) {
        int contador = 1;
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                poltronas[i][j] = String.valueOf(contador);
                contador++;
            }
        }
    }

    static void exibirMapa(String[][] poltronas) {
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                System.out.print(poltronas[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void reservarAssento(String[][] poltronas, int poltronaDesejada, int totalAssentos) {
        if (poltronaDesejada < 1 || poltronaDesejada > totalAssentos) {
            System.out.println("Erro: Esta poltrona não existe no cinema!");
            return;
        }

        boolean encontrou = false;
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                if (poltronas[i][j].equalsIgnoreCase(String.valueOf(poltronaDesejada)) && !encontrou) {
                    poltronas[i][j] = "X";
                    System.out.println("Poltrona " + poltronaDesejada + " reservada com sucesso!");
                    encontrou = true;
                } else if (poltronas[i][j].equals("X") && (i * poltronas[0].length + j + 1) == poltronaDesejada) {
                    System.out.println("Erro: A poltrona " + poltronaDesejada + " já está ocupada!");
                    return;
                }
            }
        }
    }

    static void cancelarReserva(String[][] poltronas, int poltronaReservada, int totalAssentos) {
        if (poltronaReservada < 1 || poltronaReservada > totalAssentos) {
            System.out.println("Erro: Esta poltrona não existe no cinema!");
            return;
        }

        boolean encontrou = false;
        int contador = 1;

        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                if (contador == poltronaReservada) {
                    if (poltronas[i][j].equalsIgnoreCase("X") && !encontrou) {
                        poltronas[i][j] = String.valueOf(poltronaReservada);
                        System.out.println("Reserva da poltrona " + poltronaReservada + " cancelada!");
                        encontrou = true;
                    } else {
                        System.out.println("Aviso: A poltrona " + poltronaReservada + " não possui uma reserva ativa para ser cancelada.");
                        return;
                    }

                }
                contador++;
            }
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

    static void salvarLog(String acao) {
        String caminhoLog = "historico_acoes.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoLog, true))) {
            writer.write(" AÇÃO: " + acao + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico de ações: " + e.getMessage());
        }
    }

    static void salvarDisposicao(String[][] poltronas) {
        String caminhoLog = "historico_acoes.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoLog, true))) {
            writer.write("\n--- DISPOSIÇÃO DO CINEMA ---\n");
            for (int i = 0; i < poltronas.length; i++) {
                for (int j = 0; j < poltronas[i].length; j++) {
                    writer.write(poltronas[i][j] + "\t");
                }
                writer.write("\n");
            }
            writer.write("----------------------------\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico de ações: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linhas = lerInteiro(scanner, "Digite o número de fileiras horizontais que seu cinema possui: ");
        int colunas = lerInteiro(scanner, "Digite o número de fileiras verticais que seu cinema possui: ");

        String[][] cinema = new String[linhas][colunas];
        inicializarMapa(cinema);
        int totalAssentos = linhas * colunas;

        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n========================================");
            System.out.println("     SISTEMA DE CINEMA       ");
            System.out.println("========================================");
            System.out.println(" 1 - Mostrar assentos ");
            System.out.println(" 2 - Reservar ");
            System.out.println(" 3 - Cancelar reserva ");
            System.out.println(" 4 - Sair");
            System.out.println("========================================");
            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    exibirMapa(cinema);
                    salvarDisposicao(cinema);
                    salvarLog("Visualização do mapa de assentos consultada.");
                    break;

                case 2:
                    int reservar = lerInteiro(scanner, "Digite o número da cadeira que deseja reservar: ");
                    reservarAssento(cinema, reservar, totalAssentos);
                    salvarLog("Tentativa de reserva da cadeira: " + reservar);
                    break;

                case 3:
                    int cancelar = lerInteiro(scanner, "Digite o número da cadeira que foi feito a reserva: ");
                    cancelarReserva(cinema, cancelar, totalAssentos);
                    salvarLog("Tentativa de cancelamento da cadeira: " + cancelar);
                    break;

                case 4:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    salvarLog("Encerrando programa...");
                    break;

                default:
                    System.out.println("\nOpção inexistente. Tente um número entre 1 e 4.");
                    salvarLog("Usuário digitou uma opção inexistente no menu principal: " + opcao);
                    break;
            }
        }
        scanner.close();
    }