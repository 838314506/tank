package com.myself.tank.commandAndChain;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] arg){

    }
}

interface CommandFilter{
    void doFilter(Command2 command);
}

class DoIt implements CommandFilter{

    @Override
    public void doFilter(Command2 command) {
//        command.doIt();
    }
}
class UnDo implements CommandFilter{

    @Override
    public void doFilter(Command2 command) {
//        command.unDo();
    }
}


class Chain{
    List<CommandFilter> cf = new ArrayList<>();
    int index = 0;

    public void add(CommandFilter filter){
        cf.add(filter);
    }

    public void chain(Command2 c){
        if (index == cf.size()) return;
        CommandFilter f = cf.get(index);
        f.doFilter(c);
        index ++;
    }

}
