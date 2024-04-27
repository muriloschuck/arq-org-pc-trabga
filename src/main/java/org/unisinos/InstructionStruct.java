package org.unisinos;

public class InstructionStruct {
    private int opcode;
    private int op1;
    private int op2;
    private int op3;
    private int temp1;
    private int temp2;
    private int temp3;
    private boolean valida;

    public InstructionStruct(final int opcode, final int op1, final int op2, final int op3, final int temp1, final int temp2, final int temp3, final boolean valida) {
        this.opcode = opcode;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.valida = valida;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }

    public int getOp1() {
        return op1;
    }

    public void setOp1(final int op1) {
        this.op1 = op1;
    }

    public int getOp2() {
        return op2;
    }

    public void setOp2(final int op2) {
        this.op2 = op2;
    }

    public int getOp3() {
        return op3;
    }

    public void setOp3(final int op3) {
        this.op3 = op3;
    }

    public int getTemp1() {
        return temp1;
    }

    public void setTemp1(final int temp1) {
        this.temp1 = temp1;
    }

    public int getTemp2() {
        return temp2;
    }

    public void setTemp2(final int temp2) {
        this.temp2 = temp2;
    }

    public int getTemp3() {
        return temp3;
    }

    public void setTemp3(final int temp3) {
        this.temp3 = temp3;
    }

    public boolean isValida() {
        return valida;
    }

    public void setValida(final boolean valida) {
        this.valida = valida;
    }
}
