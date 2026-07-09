import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;




    static void desenharForca(int erros) {
        System.out.println("  _______");
        System.out.println("  |/      |");


        if (erros >= 1) {
            System.out.println("  |       O");
        } else {
            System.out.println("  |");
        }

        if (erros == 2) {
            System.out.println("  |       |");
            System.out.println("  |       |");
        } else if (erros == 3) {
            System.out.println("  |      \\|");
            System.out.println("  |       |");
        } else if (erros >= 4) {
            System.out.println("  |      \\|/");
            System.out.println("  |       |");
        } else {
            System.out.println("  |");
            System.out.println("  |");
        }


        if (erros == 5) {
            System.out.println("  |      /");
        } else if (erros >= 6) {
            System.out.println("  |      / \\");
        } else {
            System.out.println("  |");
        }

        System.out.println("  |");
        System.out.println("__|___");
    }

    public static void main(String[] args) {
        boolean jogoEncerrado = false;
        int quantidadeErros = 0;
        String palavraSecreta;
        Scanner scanner = new Scanner(System.in);
        char letraEscolhida;
        String letrasErradas = "";


        String letrasTentadas = "";

        do {
            System.out.println("Digite a palavra que deseja: ");
            palavraSecreta = scanner.nextLine().toLowerCase();
        } while (palavraSecreta.isEmpty());

        char[] palavraTracada = new char[palavraSecreta.length()];
        char[] caracteresPalavraSecreta = palavraSecreta.toCharArray();
        System.out.println("  _______");
        System.out.println("  |/      |");

        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) != '_') {
                palavraTracada[i] = '_';
                if (palavraSecreta.charAt(i) == ' ' || palavraSecreta.charAt(i) == '-') {
                    palavraTracada[i] = '-';
                }
            }
        }

        while (!jogoEncerrado) {
            boolean encontrouLetra = false;
            int contadorLetrasReveladas = 0;

            System.out.println("\n=================================");
            desenharForca(quantidadeErros);
            System.out.println("=================================");

            System.out.print("Palavra atual: ");
            for (int i = 0; i < palavraTracada.length; i++) {
                System.out.print(palavraTracada[i] + " ");
            }
            System.out.println();

            if (quantidadeErros > 0) {
                System.out.println("Letras erradas: [ " + letrasErradas + "]");
                System.out.println("Erros restantes: " + (6 - quantidadeErros));
            }
            System.out.println("---------------------------------");

            System.out.print("Digite a letra que deseja: ");
            String entradaUsuario = scanner.nextLine();
            while (entradaUsuario.isEmpty()) {
                System.out.print("Digite a letra que deseja: ");
                entradaUsuario = scanner.nextLine();
            }
            letraEscolhida = entradaUsuario.toLowerCase().charAt(0);


            if (letrasTentadas.contains(String.valueOf(letraEscolhida))) {
                System.out.println("\n Você já tentou a letra '" + letraEscolhida + "'. Escolha outra!");
                letraEscolhida = scanner.nextLine().toLowerCase().charAt(0);
            }

            letrasTentadas += letraEscolhida;

            for (int i = 0; i < palavraSecreta.length(); i++) {
                if (caracteresPalavraSecreta[i] == letraEscolhida) {
                    palavraTracada[i] = letraEscolhida;
                    encontrouLetra = true;
                }
            }

            System.out.println();
            if (!encontrouLetra) {
                letrasErradas += letraEscolhida + " ";
                quantidadeErros++;
                System.out.println("Você errou a letra '" + letraEscolhida + "'!");
            } else {
                System.out.println("Você acertou a letra '" + letraEscolhida + "'!");
            }

            for (int i = 0; i < palavraSecreta.length(); i++) {
                if (palavraTracada[i] != '_') {
                    contadorLetrasReveladas++;
                }
            }

            if (contadorLetrasReveladas == palavraSecreta.length()) {
                jogoEncerrado = true;
                System.out.println("PARABÉNS! Você ganhou o jogo!");
                System.out.print("A palavra era: ");
                for (int i = 0; i < palavraTracada.length; i++) {
                    System.out.print(palavraTracada[i]);
                }
                System.out.println("\n");
            }

            if (quantidadeErros >= 6 && !jogoEncerrado) {
                System.out.println("\n=================================");
                desenharForca(quantidadeErros);
                System.out.println("=================================");
                System.out.println("\nFORCA! Você atingiu o limite de erros e perdeu o jogo.");
                System.out.println("A palavra secreta era: " + palavraSecreta);
                jogoEncerrado = true;
            }
        }
        scanner.close();
    }
