package org.unisinos;

import java.io.*;
import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static int[] registers = new int[32]; // Registradores MIPS
    private static int pc = 0;
    static String[] instructions = new String[100]; // Supondo que o arquivo tenha no máximo 100 linhas
    private static Map<String, Integer> labels = new HashMap<>(); // Mapa para armazenar rótulos e seus valores
    private static List<String> supportedOperations = new ArrayList<>(Arrays.asList("addi", "add", "sub", "subi", "beq", "j", "noop"));

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
            saidaInstructionExecute = instructionExecute(entradaInstructionExecute);
            saidaMemoryAccess = instructionMemoryAccess(entradaMemoryAccess);
            instructionWriteback(entradaWriteBack);



            System.out.println("Instruction Fetch: " + pc);
            System.out.println("Instruction Decode: " + entradaInstructionDecode);
            System.out.println("Instruction Execute: " + entradaInstructionExecute);
            System.out.println("Instruction Memory Access: " + entradaMemoryAccess);
            System.out.println("Instruction Write Back: " + entradaWriteBack);

            pc++;

            System.out.println("\nPressione enter para continuar ou digite 'exit' para sair.");
            if(sc.nextLine() == "exit") {
                break;
            }

        }
    }

    private static void processLabels(String[] instructions, int numInstructions) {
        for (int i = 0; i < numInstructions; i++) {
            String instruction = instructions[i];
            String[] parts = instruction.split(" ");
            String label = parts[0];

            if (parts.length > 1 && parts[1].equals(".fill")) {
                int value = Integer.parseInt(parts[2]);
                labels.put(label, value);
                continue;
            }

            if (!supportedOperations.contains(label)){
                labels.put(label, i+1);
                instructions[i] = instructions[i].substring(label.length() + 1);
            }

        }
    }

    private static void instructionWriteback(InstructionMemoryAccessStruct struct) {
        if(struct != null) {
            registers[struct.getDestinyRegister()] = struct.getAluResult();
        }
    }

    private static InstructionMemoryAccessStruct instructionMemoryAccess(InstructionExecuteStruct struct) {
        if(struct != null) {
            return new InstructionMemoryAccessStruct(struct.getOpcode() ,struct.getDestinationRegister(),0, struct.getALUResult());
        }
        return null;
    }

    private static InstructionExecuteStruct instructionExecute(InstructionDecodeStruct struct) {
        if(struct != null) {
            switch (struct.getOpcode()) {
                case "addi":
                    return new InstructionExecuteStruct(struct.getOpcode(), struct.getDestinationRegister(),0,struct.getOffsetValue()+struct.getValueB(),false);
            }
        }
        return null;

    }

    private static InstructionDecodeStruct instructionDecode(String actualInstruction) {
        //Split the string but assert it is not null before
        if(actualInstruction != null) {
            String[] parts = actualInstruction.split(" ");
            String opcode = parts[0];

            switch (opcode) {
                case "addi":
                    int destRegister = Integer.parseInt(parts[2].substring(1));
                    int sourceRegister = Integer.parseInt(parts[1].substring(1));
                    int immediateValue;
                    if (Character.isDigit(parts[3].charAt(0)) || parts[3].charAt(0) == '-') {
                        immediateValue = Integer.parseInt(parts[3]);
                    } else {
                        immediateValue = labels.get(parts[3]);
                    }
                    return new InstructionDecodeStruct(opcode,destRegister, immediateValue,sourceRegister,0);
            }
        }
        return null;
    }

    private static String instructionFetch(final int pc) {
        return instructions[pc];
    }

}