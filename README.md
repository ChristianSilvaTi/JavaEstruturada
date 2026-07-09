# Lógica de Programação e Estruturas Fundamentais em Java 

Este repositório reúne uma coleção de sistemas baseados em console desenvolvidos em Java Puro (Core). O objetivo principal deste projeto é consolidar conceitos avançados de lógica estruturada, manipulação de fluxos de dados, persistência em arquivos locais e boas práticas de codificação.

---

## Sistemas Inclusos no Projeto

* **Biblioteca (Livraria):** Gerenciador de acervo e empréstimo de livros utilizando vetores/matrizes para catalogação e controle de estoque literário.
* **Cinema:** Gerenciador dinâmico de reserva de assentos em salas de cinema com controle de ocupação e prevenção de reservas duplicadas.
* **Agenda:** Sistema de contatos com matrizes dinâmicas que crescem e diminuem o tamanho físico na memória em tempo de execução conforme a necessidade do usuário.
* **Jogo da Forca:** O clássico jogo da forca com renderização de gráficos em arte ASCII no terminal, controle de tentativas e bloqueio de palpites repetidos.
* **Escola / Registro:** Sistema para leitura, cálculo de médias e escrita de notas/boletins de alunos em arquivos externos.
* **Editor de Texto Simples:** Ferramenta para manipulação e leitura de fluxo de caracteres direto no terminal.

---

## ️ Conceitos Técnicos Aplicados

O projeto foi construído sem o uso de frameworks externos, focando puramente nos recursos nativos da linguagem Java (OpenJDK 26):

* **Modularização com Métodos Estáticos (`static`):** Organização do código em blocos lógicos isolados e reutilizáveis, garantindo que o método `main` atue estritamente como o maestro/fluxo principal do sistema.
* **Persistência de Dados em Arquivo:** Escrita e leitura em arquivos de texto locais utilizando as classes estruturadas `BufferedWriter`, `FileWriter` e tratamento robusto de exceções com `IOException`.
* **Estruturas Condicionais Avançadas:** Uso estratégico de `if/else` encadeados e seletores múltiplos `switch-case` para controle de menus e fluxos de decisão.
* **Estruturas de Repetição:** Controle de loops iterativos utilizando `for` (para varredura de dados conhecidos) e `while` / `do-while` (para menus e validações por tempo indeterminado).
* **Vetores e Matrizes (Arrays Multidimensionais):** Manipulação de estruturas de dados unidimensionais e bidimensionais para representação de layouts (como as poltronas do cinema) e tabelas de informações (como nome e telefone na agenda).
* **Manipulação e Comparação de Strings:** Uso intensivo de métodos nativos da classe `String` como `.equalsIgnoreCase()`, `.trim()`, `.contains()`, `.toLowerCase()` e `.toUpperCase()` para tratamento e validação de textos digitados pelo usuário.
* **Leitura Segura de Dados (Garantia de Tipos):** Implementação de métodos de captura robustos usando `Scanner` e métodos como `.hasNextInt()` e `.hasNextDouble()`. O sistema impede travamentos (*crashes*) se o usuário digitar uma letra onde o programa esperava um número inteiro ou decimal (`double`), além de bloquear campos em branco ou números negativos.

---

##  Como Executar os Sistemas

1. Certifique-se de ter o Java JDK instalado em sua máquina (versão 8 ou superior).
2. Abra o projeto na sua IDE de preferência (altamente recomendado: **IntelliJ IDEA**).
3. Garanta que a pasta `src` esteja configurada como a raiz do código-fonte (*Sources Root*).
4. Abra o arquivo do sistema que deseja testar (ex: `Biblioteca.java` ou `Cinema.java`).
5. Clique com o botão direito do mouse dentro do editor de código e selecione **Run** (Executar).

---

## 🏷️ Tags / Tópicos do Repositório
`java` • `logic-programming` • `algorithms` • `core-java` • `console-application` • `programming-concepts`