package com.myself.tank.commandAndChain.right;

public class InsertCommand extends Command {

    public Content c;

    public Content getC() {
        return c;
    }

    String strToInsert = " world";

    public InsertCommand(Content c){
        this.c = c;
    }

    @Override
    public void doIt() {
        c.name = c.name + strToInsert;
    }

    @Override
    public void unDo() {
        c.name = c.name.substring(0,c.name.length() - strToInsert.length());
    }
}
