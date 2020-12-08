package com.myself.tank.commandAndChain.wrong;

public class DoIt extends Command2{
    @Override
    void doIt(StringBuilder str) {
        str.append("a");
    }

    @Override
    void unDo(StringBuilder str) {
    }

    public static void main(String[] arg){
        StringBuilder sb = new StringBuilder("c");
        Command2 c = new DoIt();
        for (int i = 0;i < 3;i ++){
            c.doIt(sb);
            c.unDo(sb);
        }
        System.out.println(sb.toString());
    }
}
