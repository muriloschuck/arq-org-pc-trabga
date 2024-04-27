package org.unisinos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final String caminhoArquivoInstrucoes = "src/main/resources/instructions.txt";
    private static List<String> listaOperacoes = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException {
        listaOperacoes = carregaArquivoEmLista(caminhoArquivoInstrucoes);

        while(true) {

            sc.next();
        }
    }

    private static InstructionStruct writeBack(final InstructionStruct struct) {
        return null;
    }

    private static InstructionStruct memoryAccess(final InstructionStruct struct) {
        return null;
    }

    private static InstructionStruct executeAddrCalc(final InstructionStruct struct) {
        return null;
    }

    private static InstructionStruct instructionDecode(final InstructionStruct struct) {
        return null;
    }

    private static InstructionStruct instructionFetch(final InstructionStruct struct) {
        return null;
    }

    private static List<String> carregaArquivoEmLista(final String caminhoArquivo) throws FileNotFoundException {
        final List<String> tempListaOperacoes = new ArrayList<>();
        final File arquivo = new File(caminhoArquivo);
        final Scanner fileScanner = new Scanner(arquivo);

        while (fileScanner.hasNextLine()) {
            String linha = fileScanner.nextLine();
            tempListaOperacoes.add(linha);
        }

        fileScanner.close();
        return tempListaOperacoes;
    }

}