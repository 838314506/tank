package com.myself.tank.commandAndChain.right;

public class CopyCommand extends Command{

    public Content c;

    public Content getC() {
        return c;
    }

    String copyStr;

    public CopyCommand(Content c){
        this.c = c;
    }

    @Override
    public void doIt() {
        copyStr = c.name;
        c.name = c.name + copyStr;
    }

    @Override
    public void unDo() {
        c.name = copyStr;
    }
}
