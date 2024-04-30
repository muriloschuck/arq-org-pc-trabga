# MIPS Processor Simulation

Este é um programa Java para simular o processamento de um processador MIPS simplificado. Ele lê instruções de um arquivo, interpreta-as e simula a execução em um ambiente de simulação.

## Funcionalidades

- Leitura de instruções de um arquivo.
- Interpretação das instruções e simulação do processamento MIPS.
- Rastreamento do estado dos registradores e dos pulsos de clock durante a execução.

## Como usar

1. Compile o código Java.
2. Execute o programa Java compilado.

## Pré-requisitos

- JDK (Java Development Kit) instalado para compilação e execução.
- Arquivo de instruções contendo as instruções MIPS a serem processadas.

## Instruções do Arquivo

- O arquivo de instruções deve conter as instruções MIPS, uma por linha.
- O programa suporta rótulos definidos usando a diretiva `.fill`.
- As instruções suportadas são `addi`, `add`, `sub`, `subi`, `beq`, `j`, `noop` e `halt`.

## Exemplo de Uso

1. Compile o código Java:

   ```bash
   javac Main.java
   ```

2. Execute o programa Java compilado:
    ```bash
    java Main
    ```
3. Insira o nome do arquivo de instruções quando solicitado.
4. O programa executará a simulação e exibirá o estado dos registradores e os pulsos de clock.
