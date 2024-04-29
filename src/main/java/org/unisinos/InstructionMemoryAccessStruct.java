package org.unisinos;

public class InstructionMemoryAccessStruct {
    private String opcode;
    private int destinyRegister;
    private int memoryData;
    private int aluResult;

    public InstructionMemoryAccessStruct(String opcode, int destinyRegister, int memoryData, int aluResult) {
        this.opcode = opcode;
        this.destinyRegister = destinyRegister;
        this.memoryData = memoryData;
        this.aluResult = aluResult;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public int getDestinyRegister() {
        return destinyRegister;
    }

    public void setDestinyRegister(int destinyRegister) {
        this.destinyRegister = destinyRegister;
    }

    public int getMemoryData() {
        return memoryData;
    }

    public void setMemoryData(int memoryData) {
        this.memoryData = memoryData;
    }

    public int getAluResult() {
        return aluResult;
    }

    public void setAluResult(int aluResult) {
        this.aluResult = aluResult;
    }

    @Override
    public String toString() {
        return "InstructionMemoryAccessStruct{" +
                "opcode='" + opcode + '\'' +
                ", destinyRegister=" + destinyRegister +
                ", memoryData=" + memoryData +
                ", aluResult=" + aluResult +
                '}';
    }
}
