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
    private static int pc = 0;
    private static InstructionStruct entradaInstructionFetch;
    private static InstructionStruct saidaInstructionFetch = null, entradaInstructionDecode = null,
            saidaInstructionDecode = null, entradaExecuteAddrCalc = null, saidaExecuteAddrCalc = null,
            entradaMemoryAccess = null, saidaMemoryAccess = null, entradaWriteBack = null, saidaWriteBack = null;

    public static void main(String[] args) throws FileNotFoundException {
        listaOperacoes = carregaArquivoEmLista(caminhoArquivoInstrucoes);


        // remove later
        InstructionStruct structTest = new InstructionStruct(2, 0, 0,0,0,0,0, true);

        while(true) {
            entradaInstructionFetch = structTest;
            entradaInstructionDecode = saidaInstructionFetch;
            entradaExecuteAddrCalc = saidaInstructionDecode;
            entradaMemoryAccess = saidaExecuteAddrCalc;
            entradaWriteBack = saidaMemoryAccess;

            // process stuff
//            saidaInstructionFetch = instructionFetch(structTest); // change
//            saidaInstructionDecode = instructionDecode(entradaInstructionDecode);
//            saidaExecuteAddrCalc = executeAddrCalc(entradaExecuteAddrCalc);
//            saidaMemoryAccess = memoryAccess(entradaMemoryAccess);
//            saidaWriteBack = writeBack(entradaWriteBack);

            sc.nextLine();

            System.out.println("saidaInstructionFetch: " + (saidaInstructionFetch != null ? saidaInstructionFetch.getOpcode() : null));
            System.out.println("saidaInstructionDecode: " + (saidaInstructionDecode != null ? saidaInstructionDecode.getOpcode() : null));
            System.out.println("saidaExecuteAddrCalc: " + (saidaExecuteAddrCalc != null ? saidaExecuteAddrCalc.getOpcode() : null));
            System.out.println("saidaMemoryAccess: " + (saidaMemoryAccess != null ? saidaMemoryAccess.getOpcode() : null));
            System.out.println("saidaWriteBack: " + (saidaWriteBack != null ? saidaWriteBack.getOpcode() : null));
        }
    }

    private static void writeBack(final InstructionMemoryAccessStruct struct) {

    }

    private static InstructionMemoryAccessStruct memoryAccess(final InstructionExecuteStruct struct) {
        return null;
    }

    private static InstructionExecuteStruct executeAddrCalc(final InstructionDecodeStruct struct) {
        return null;
    }

    private static InstructionDecodeStruct instructionDecode(final String instruction) {
        return null;
    }

    private static String instructionFetch(final int pc) {
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