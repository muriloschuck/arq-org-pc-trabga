package org.unisinos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MIPSProcessorSimulation {

    private static int[] registers = new int[32]; // Registradores MIPS
    private static int pc = 0; // Contador de programa
    private static boolean halted = false; // Indica se o programa foi encerrado
    private static List<String> supportedOperations = new ArrayList<>(Arrays.asList("addi", "add", "sub", "subi", "beq", "j", "noop"));

    private static Map<String, Integer> labels = new HashMap<>(); // Mapa para armazenar rótulos e seus valores

    public static void main(String[] args) {
        try {
            // Ler o arquivo de entrada
            BufferedReader reader = new BufferedReader(new FileReader("instrucoes.txt"));
            String line;

            // Carregar as instruções em uma matriz de strings
            String[] instructions = new String[100]; // Supondo que o arquivo tenha no máximo 100 linhas
            int numInstructions = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Ignorar linhas em branco
                    instructions[numInstructions++] = line;
                }
            }
            reader.close();

            // Processar as instruções para buscar e armazenar valores de rótulos
            processLabels(instructions, numInstructions);

            // Iniciar a execução das instruções
            while (!halted && pc < numInstructions) {
                String instruction = instructions[pc];
                executeInstruction(instruction);
                pc++;
            }

            // Exibir o estado final dos registradores
            System.out.println("Estado final dos registradores:");
            for (int i = 0; i < 32; i++) {
                System.out.println("R" + i + ": " + registers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            }

        }
    }

    private static void executeInstruction(String instruction) {
        String[] parts = instruction.split(" ");
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
                registers[destRegister] = registers[sourceRegister] + immediateValue;
                break;
            case "beq":
                int register1 = Integer.parseInt(parts[1]);
                int register2 = Integer.parseInt(parts[2]);
                int offset;
                if (Character.isDigit(parts[3].charAt(0)) || parts[3].charAt(0) == '-') {
                    offset = Integer.parseInt(parts[3]);
                } else {
                    offset = labels.get(parts[3]) - pc - 1;
                }
                if (registers[register1] == registers[register2]) {
                    pc += offset;
                }
                break;
            case "halt":
                halted = true;
                break;
            case "noop":
                // Não faz nada
                break;
        }
    }
}
