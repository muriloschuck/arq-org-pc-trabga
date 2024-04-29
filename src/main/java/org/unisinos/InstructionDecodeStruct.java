package org.unisinos;

public class InstructionDecodeStruct {
    private String opcode;
    private int destinationRegister;
    private int offset;
    private int valueB;
    private int valueA;
    public InstructionDecodeStruct(String opcode, int destinationRegister, int offset, int valueB, int valueA) {
        this.opcode = opcode;
        this.destinationRegister = destinationRegister;
        this.offset = offset;
        this.valueB = valueB;
        this.valueA = valueA;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public int getDestinationRegister() {
        return destinationRegister;
    }

    public void setDestinationRegister(int destinationRegister) {
        this.destinationRegister = destinationRegister;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getValueB() {
        return valueB;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public int getValueA() {
        return valueA;
    }

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    @Override
    public String toString() {
        return "InstructionDecodeStruct{" +
                "opcode='" + opcode + '\'' +
                ", destinationRegister=" + destinationRegister +
                ", offset=" + offset +
                ", valueB=" + valueB +
                ", valueA=" + valueA +
                '}';
    }
}
