package com.myself.tank.commandAndChain.right;

public class DeleteCommand extends Command {

    public Content c;

    public Content getC() {
        return c;
    }

    String deleteChar;

    public DeleteCommand(Content c){
        this.c = c;
    }
    @Override
    public void doIt() {
        deleteChar = c.name.substring(c.name.length() - 1,c.name.length() );
        c.name = c.name.substring(0,c.name.length() - 2);
    }

    @Override
    public void unDo() {
        c.name = c.name + deleteChar;
    }
}
