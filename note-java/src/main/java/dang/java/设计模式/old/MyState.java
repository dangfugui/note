package com.dang.java.设计模式.old;

//状态
public class MyState {

}

class MM2 {
    public MMState state = new MMHappyState();

    public void smile() {
        state.smile();
    }

    public void cry() {
        state.cry();
    }

    public void say() {
        state.say();
    }
}

abstract class MMState {
    public abstract void cry();

    public abstract void say();

    public abstract void smile();
}

class MMHappyState extends MMState {
    @Override
    public void cry() {

    }

    @Override
    public void say() {

    }

    @Override
    public void smile() {
        // TODO Auto-generated method stub
    }

}

class MMUnHappyState extends MMState {
    @Override
    public void cry() {

    }

    @Override
    public void say() {

    }

    @Override
    public void smile() {
        // TODO Auto-generated method stub
    }
}

class Boy2 {

}
