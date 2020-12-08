package com.myself.tank.commandAndChain.wrong;

public class Undo extends Command2{
    @Override
    void doIt(StringBuilder str) {
    }

    @Override
    void unDo(StringBuilder str) {
        str.deleteCharAt(str.length() - 1);
    }
}
