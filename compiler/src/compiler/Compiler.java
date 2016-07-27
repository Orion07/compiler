/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author Orion
 */
public class Compiler {
    public BST command = new BST();
    public BST memoryMap = new BST();
    public BST opcodes = new BST();
    public int AC = 0;

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }
    public int SP = 0;
    public int C = 0;
    public int PC = 0;
    public Compiler()
    {
        loadCommands();
    }
   

    public void loadCommands()
    {
       command.put("AND",0x0);
       command.put("ADD",0x1000);
       command.put("LDA",0x2000);
       command.put("STA",0x3000);
       command.put("BUN",0x4000);
       command.put("BSA",0x5000);
       command.put("ISZ",0x6000);
       
       command.put("SPA",0x7400);
       command.put("SNA",0x7200);
       command.put("SZA",0x7100);
       command.put("SZE",0x7080);
       command.put("CLA",0x7040);
       command.put("CLE",0x7020);
       command.put("CMA",0x7010);
       command.put("CME",0x7008);
       command.put("INC",0x7004);
       command.put("CIR",0x7002);
       command.put("CIL",0x7001);
       
       command.put("SPS",0x7840);
       command.put("SPI",0x7820);
       command.put("PUSH",0x7810);
       command.put("POP",0x7808);
       command.put("SPM",0x7804);
       command.put("ISP",0x7802);
       command.put("RET",0x7801);    
       
    }
    public void AND(String memory)
    {
        AC = (short) (AC & (Short)memoryMap.get(memory));
    }
    public void ADD(String memory)
    {
        AC = (short) (AC + (Short)memoryMap.get(memory));
    }   
}
