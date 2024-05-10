# MIPS Processor Simulation

This is a Java program to simulate the processing of a simplified MIPS processor. It reads instructions from a file, interprets them, and simulates execution in a simulation environment.

## Functionalities

- Reading and interpreting instructions from a file.
- Tracking the state of registers and clock pulses during execution.

## Prerequisites

- JDK 17 (Java Development Kit) installed for compilation and execution.
- Instruction file containing the MIPS instructions to be processed (an example is provided at resources folder)

## How to Use

1. Compile the Java code:

   ```bash
   javac Main.java
   ```

2. Execute the compiled Java program:
    ```bash
    java Main
    ```

3. The program will run the simulation and display the state of the registers and clock pulses. Press `Enter` to go to the next clock pulse.

## File Instructions

- The instruction file should contain MIPS instructions, one per line.
- The program supports labels defined using the `.fill` directive.
- Supported instructions are `addi`, `add`, `sub`, `subi`, `beq`, `j`, `noop` and `halt`.
