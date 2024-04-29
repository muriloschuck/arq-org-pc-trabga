package org.unisinos;

public class InstructionExecuteStruct {
    private String opcode;
    private int destinationRegister;
    private int valB;
    private int ALUResult;
    private boolean equals;

    public InstructionExecuteStruct(String opcode, int destinationRegister, int valB, int ALUResult, boolean equals) {
        this.opcode = opcode;
        this.destinationRegister = destinationRegister;
        this.valB = valB;
        this.ALUResult = ALUResult;
        this.equals = equals;
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

    public int getValB() {
        return valB;
    }

    public void setValB(int valB) {
        this.valB = valB;
    }

    public int getALUResult() {
        return ALUResult;
    }

    public void setALUResult(int ALUResult) {
        this.ALUResult = ALUResult;
    }

    public boolean isEquals() {
        return equals;
    }

    public void setEquals(boolean equals) {
        this.equals = equals;
    }

    @Override
    public String toString() {
        return "InstructionExecuteStruct{" +
                "opcode='" + opcode + '\'' +
                ", destinationRegister=" + destinationRegister +
                ", valB=" + valB +
                ", ALUResult=" + ALUResult +
                ", equals=" + equals +
                '}';
    }
}
