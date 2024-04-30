package org.unisinos;

import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final int[] registers = new int[32]; // Registradores MIPS
    private static int pc = 0;
    static String[] instructions = new String[100]; // Supondo que o arquivo tenha no máximo 100 linhas
    private static final Map<String, Integer> labels = new HashMap<>(); // Mapa para armazenar rótulos e seus valores
    private static final List<String> supportedOperations = new ArrayList<>(Arrays.asList("addi", "add", "sub", "subi", "beq", "j", "noop"));
    private static boolean halted = false; // Indica se o programa foi encerrado

    private static String saidaInstructionFetch = null;
    private static InstructionDecodeStruct saidaInstructionDecode = null;
    private static InstructionExecuteStruct saidaInstructionExecute = null;
    private static InstructionMemoryAccessStruct saidaMemoryAccess = null;

    public static void main(String[] args) {
        // Ler o arquivo e carregar as instruções em uma matriz de strings
        int numInstructions = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/instructions-sub-j.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Ignorar linhas em branco
                    instructions[numInstructions++] = line;
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        processLabels(instructions, numInstructions);

        while(true) {
            String entradaInstructionDecode = saidaInstructionFetch;
            InstructionDecodeStruct entradaInstructionExecute = saidaInstructionDecode;
            InstructionExecuteStruct entradaMemoryAccess = saidaInstructionExecute;
            InstructionMemoryAccessStruct entradaWriteBack = saidaMemoryAccess;

            // Processa instrucoes
            saidaInstructionFetch = instructionFetch(pc);
            saidaInstructionDecode = instructionDecode(entradaInstructionDecode);
            saidaInstructionExecute = instructionExecute(entradaInstructionExecute);
            saidaMemoryAccess = instructionMemoryAccess(entradaMemoryAccess);
            instructionWriteback(entradaWriteBack);

            System.out.println("Instruction Fetch: " + pc);
            System.out.println("Instruction Decode: " + entradaInstructionDecode);
            System.out.println("Instruction Execute: " + entradaInstructionExecute);
            System.out.println("Instruction Memory Access: " + entradaMemoryAccess);
            System.out.println("Instruction Write Back: " + entradaWriteBack);
            System.out.println("Registers: " + Arrays.toString(registers));

            if (halted) {
                System.out.println("Programa encerrado.");
                break;
            }

            System.out.println("\nPressione enter para continuar ou digite 'exit' para sair.");
            if (Objects.equals(sc.nextLine(), "exit")) {
                break;
            }

            pc++;
        }
    }

    private static void processLabels(String[] instructions, int numInstructions) {
        for (int i = 0; i < numInstructions; i++) {
            final String instruction = instructions[i];
            final String[] parts = instruction.split(" ");
            final String label = parts[0];

            if (parts.length > 1 && parts[1].equals(".fill")) {
                int value = Integer.parseInt(parts[2]);
                labels.put(label, value);
                instructions[i] = "noop";
                continue;
            }

            if (!supportedOperations.contains(label)){
                labels.put(label, i);
                instructions[i] = instructions[i].substring(label.length() + 1);
            }

        }
    }

    private static void instructionWriteback(final InstructionMemoryAccessStruct struct) {
        if (struct != null) {
            if (struct.getOpcode().equals("halt")) {
                halted = true;
                return;
            }

            if (struct.getOpcode().equals("noop") || struct.getOpcode().equals("j") || struct.getOpcode().equals("beq")) {
                return;
            }

            // escreve no registrador
            registers[struct.getDestinyRegister()] = struct.getAluResult();
        }
    }

    private static InstructionMemoryAccessStruct instructionMemoryAccess(final InstructionExecuteStruct struct) {
        if (struct != null) {
            return new InstructionMemoryAccessStruct(struct.getOpcode() ,struct.getDestinationRegister(),0, struct.getALUResult());
        }

        return null;
    }

    private static InstructionExecuteStruct instructionExecute(final InstructionDecodeStruct struct) {
        if (struct != null) {
            switch (struct.getOpcode()) {
                case "addi":
                    return new InstructionExecuteStruct(struct.getOpcode(), struct.getDestinationRegister(),0,struct.getOffsetValue()+registers[struct.getValueA()],false);
                case "add":
                    return new InstructionExecuteStruct(struct.getOpcode(), struct.getDestinationRegister(),0, registers[struct.getValueA()]+registers[struct.getValueB()],false);
                case "subi":
                    return new InstructionExecuteStruct(struct.getOpcode(), struct.getDestinationRegister(),0,registers[struct.getValueA()]-struct.getOffsetValue(),false);
                case "sub":
                    return new InstructionExecuteStruct(struct.getOpcode(), struct.getDestinationRegister(),0, registers[struct.getValueA()]-registers[struct.getValueB()],false);
                case "beq":
                    if (registers[struct.getValueA()] == registers[struct.getValueB()]) {
                        pc = struct.getOffsetValue() - 1;
                    }
                    return new InstructionExecuteStruct(struct.getOpcode(), 0,0,0,false);
                case "j":
                    pc = struct.getOffsetValue() - 1;
                    return new InstructionExecuteStruct(struct.getOpcode(), 0,0,0,false);
                case "noop", "halt":
                    return new InstructionExecuteStruct(struct.getOpcode(), 0,0,0,false);
            }
        }

        return null;
    }

    private static InstructionDecodeStruct instructionDecode(final String actualInstruction) {
        if (actualInstruction != null) {
            final String[] parts = actualInstruction.split(" ");
            final String opcode = parts[0];

            switch (opcode) {
                case "addi", "subi":
                    final int destRegister = Integer.parseInt(parts[2].substring(1));
                    final int sourceRegister = Integer.parseInt(parts[1].substring(1));
                    final int immediateValue = getLabelValue(parts[3]);
                    return new InstructionDecodeStruct(opcode, destRegister, immediateValue,sourceRegister,0);
                case "add", "sub":
                    final int destRegisterAdd = Integer.parseInt(parts[1]);
                    final int sourceRegisterAdd = Integer.parseInt(parts[2]);
                    final int sourceRegisterAdd2 = Integer.parseInt(parts[3]);
                    return new InstructionDecodeStruct(opcode, destRegisterAdd,  0, sourceRegisterAdd, sourceRegisterAdd2);
                case "beq":
                    int register1 = Integer.parseInt(parts[1]);
                    int register2 = Integer.parseInt(parts[2]);
                    int label = getLabelValue(parts[3]);
                    return new InstructionDecodeStruct(opcode, 0, label, register1, register2);
                case "j":
                    int labelJ = getLabelValue(parts[1]);
                    return new InstructionDecodeStruct(opcode, 0, labelJ, 0, 0);
                case "noop", "halt":
                    return new InstructionDecodeStruct(opcode, 0, 0, 0, 0);
            }
        }

        return null;
    }

    private static int getLabelValue(final String labelString) {
        return Character.isDigit(labelString.charAt(0)) || labelString.charAt(0) == '-' ? Integer.parseInt(labelString) : labels.get(labelString);
    }

    private static String instructionFetch(final int pc) {
        return instructions[pc];
    }

}