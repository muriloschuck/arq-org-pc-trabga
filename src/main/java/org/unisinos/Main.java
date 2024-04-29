package org.unisinos;

import java.io.*;
import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static int[] registers = new int[32]; // Registradores MIPS
    private static int pc = 0; // Contador de programa
    static String[] instructions = new String[100]; // Supondo que o arquivo tenha no máximo 100 linhas
    private static Map<String, Integer> labels = new HashMap<>(); // Mapa para armazenar rótulos e seus valores

    private static int entradaInstructionFetch = 0;
    private static String saidaInstructionFetch = null;
    private static String entradaInstructionDecode = null;
    private static InstructionDecodeStruct saidaInstructionDecode = null;
    private static InstructionDecodeStruct entradaInstructionExecute = null;
    private static InstructionExecuteStruct saidaInstructionExecute = null;
    private static InstructionExecuteStruct entradaMemoryAccess = null;
    private static InstructionMemoryAccessStruct saidaMemoryAccess = null;
    private static InstructionMemoryAccessStruct entradaWriteBack = null;





    public static void main(String[] args) throws FileNotFoundException {
        try {
            // Ler o arquivo de entrada
            BufferedReader reader = new BufferedReader(new FileReader("instrucoes.txt"));
            String line;

            // Carregar as instruções em uma matriz de strings

            int numInstructions = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Ignorar linhas em branco
                    instructions[numInstructions++] = line;
                }
            }
            reader.close();

            // Processar as instruções para buscar e armazenar valores de rótulos
            processLabels(instructions, numInstructions);

        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            entradaInstructionDecode = saidaInstructionFetch;
            entradaInstructionExecute = saidaInstructionDecode;
            entradaMemoryAccess = saidaInstructionExecute;
            entradaWriteBack = saidaMemoryAccess;

            // process stuff
            saidaInstructionFetch = instructionFetch(pc); // change
            saidaInstructionDecode = instructionDecode(entradaInstructionDecode);
            //saidaExecuteAddrCalc = executeAddrCalc(entradaExecuteAddrCalc);
            //saidaMemoryAccess = memoryAccess(entradaMemoryAccess);
            //saidaWriteBack = writeBack(entradaWriteBack);

            sc.nextLine();

        }
    }

    private static void processLabels(String[] instructions, int numInstructions) {
        for (int i = 0; i < numInstructions; i++) {
            String instruction = instructions[i];
            String[] parts = instruction.split(" ");
            if (parts.length > 1 && parts[1].equals(".fill")) {
                String label = parts[0];
                int value = Integer.parseInt(parts[2]);
                labels.put(label, value);
            }
        }
    }

    private static void instructionWriteback() {
        InstructionMemoryAccessStruct struct = null;
    }

    private static void instructionMemoryAccess() {
        final InstructionExecuteStruct struct;
    }

    private static void instructionExecute() {
        final InstructionDecodeStruct struct;
    }

    private static InstructionDecodeStruct instructionDecode(String actualInstruction) {
        //Split the string but assert it is not null before
        return null;
    }

    private static String instructionFetch(final int pc) {
        return instructions[pc];
    }

}