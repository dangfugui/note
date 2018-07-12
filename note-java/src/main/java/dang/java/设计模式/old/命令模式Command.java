package com.dang.java.设计模式.old;

import java.util.ArrayList;
import java.util.List;

public class 命令模式Command {
    public static void main(String[] args) {
        MM1 mm = new MM1();
        mm.order(new Boy1());
    }
}

class MM1 {//

    public void order(Boy1 boy) {
        Command c1 = new ShoppingCommand();
        boy.addCommand(c1);
        Command c2 = new ShoppingCommand();
        boy.addCommand(c2);
        boy.executeCommands();
    }
}

class Boy1 {
    private List<Command> b = new ArrayList<Command>();

    public void addCommand(Command c) {
        b.add(c);
    }

    public void executeCommands() {
        for (Command command : b) {
            command.unDo();    //保存到栈   好实现unDo
        }
    }
}

abstract class Command {
    public abstract void execute();

    public abstract void unDo();
}

class ShoppingCommand extends Command {
    @Override
    public void execute() {
        System.out.println("zoo");
    }

    @Override
    public void unDo() {
        System.out.println("undo zoo");
    }
}
