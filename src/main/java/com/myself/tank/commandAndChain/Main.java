package com.myself.tank.commandAndChain;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] arg){
        Chain c = new Chain();
        c.add(new DoFilter());
        c.add(new UnFilter());

        DoIt doIt = new DoIt();
        Undo undo = new Undo();
        StringBuilder sb = new StringBuilder("c");
        c.chain(doIt,undo,c,sb);
        System.out.println(sb);
    }
}

interface CommandFilter{
    void doFilter(DoIt di,Undo ud,Chain chain,StringBuilder sb);
}

class DoFilter implements CommandFilter{

    @Override
    public void doFilter(DoIt di,Undo ud,Chain chain,StringBuilder sb) {
        di.doIt(sb);
        chain.chain(di, ud, chain, sb);
        ud.unDo(sb);
    }
}
class UnFilter implements CommandFilter{

    @Override
    public void doFilter(DoIt di,Undo ud,Chain chain,StringBuilder sb) {
        di.doIt(sb);
        chain.chain(di, ud, chain, sb);
        ud.unDo(sb);
    }
}


class Chain{
    List<CommandFilter> cf = new ArrayList<>();
    int index = 0;

    public void add(CommandFilter filter){
        cf.add(filter);
    }

    public void chain(DoIt di,Undo ud,Chain chain,StringBuilder sb){
        if (index == cf.size()) return;
        CommandFilter f = cf.get(index);
        index ++;
        f.doFilter(di,ud,chain,sb);
    }

}
