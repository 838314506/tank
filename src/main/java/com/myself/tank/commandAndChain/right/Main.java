package com.myself.tank.commandAndChain.right;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
//        List<Command> commands = new ArrayList<>();
//        Content c = new Content();
//        commands.add(new InsertCommand(c));
//        commands.add(new DeleteCommand(c));
//        commands.add(new CopyCommand(c));
//
//        for (Command co : commands){
//            co.doIt();
//            System.out.println(co.getClass() + "--------》" + c.name);
//        }
//
//        for (Command co : commands){
//            co.unDo();
//            System.out.println(co.getClass() + "--------》" + c.name);
//        }

        Content c = new Content();

        Command insertCommand = new InsertCommand(c);
        insertCommand.doIt();
        insertCommand.unDo();

        Command copyCommand = new CopyCommand(c);
        copyCommand.doIt();
        copyCommand.unDo();

        Command deleteCommand = new DeleteCommand(c);
        deleteCommand.doIt();
        deleteCommand.unDo();

        List<Command> commands = new ArrayList<>();
        commands.add(new InsertCommand(c));
        commands.add(new CopyCommand(c));
        commands.add(new DeleteCommand(c));

        for(Command comm : commands) {
            comm.doIt();
        }


        System.out.println(c.name);

        for(int i= commands.size()-1; i>=0; i--) {
            commands.get(i).unDo();
        }


        System.out.println(c.name);
    }

}
