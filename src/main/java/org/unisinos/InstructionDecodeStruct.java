package org.unisinos;

public class InstructionDecodeStruct {
    private String opcode;
    private int destinationRegister;
    private int offsetValue;
    private int valueA;
    private int valueB;
    public InstructionDecodeStruct(String opcode, int destinationRegister, int offsetValue, int valueA, int valueB) {
        this.opcode = opcode;
        this.destinationRegister = destinationRegister;
        this.offsetValue = offsetValue;
        this.valueA = valueA;
        this.valueB = valueB;
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

    public int getOffsetValue() {
        return offsetValue;
    }

    public void setOffsetValue(int offsetValue) {
        this.offsetValue = offsetValue;
    }

    public int getValueA() {
        return valueA;
    }

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public int getValueB() {
        return valueB;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    @Override
    public String toString() {
        return "InstructionDecodeStruct{" +
                "opcode='" + opcode + '\'' +
                ", destinationRegister=" + destinationRegister +
                ", offset=" + offsetValue +
                ", valueB=" + valueA +
                ", valueA=" + valueB +
                '}';
    }
}
