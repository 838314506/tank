package com.myself.tank.commandAndChain;

public class StringCommand2 extends Command2{
    @Override
    void doIt(StringBuilder str) {
        str.append("a");
    }

    @Override
    void unDo(StringBuilder str) {
        str.deleteCharAt(str.length() - 1);
    }

    public static void main(String[] arg){
        StringBuilder sb = new StringBuilder("c");
        Command2 c = new StringCommand2();
        for (int i = 0;i < 3;i ++){
            c.doIt(sb);
            c.unDo(sb);
        }
        System.out.println(sb.toString());
    }
}
