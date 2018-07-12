package com.dang.java.设计模式.行为型模式.两个类之间;

public class 命令模式 { // Command
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command cmd = new MyCommand(receiver);
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}

class Invoker { // 调用者（司令员）
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        command.exe();
    }
}

interface Command { // 命令
    public void exe();
}

class Receiver {    // 被调用者（士兵）
    public void action() {
        System.out.println("command received!");
    }
}

class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}