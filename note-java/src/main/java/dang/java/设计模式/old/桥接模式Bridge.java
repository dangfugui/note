package com.dang.java.设计模式.old;

//gift  和 giftImpl  相互排列组合//
public class 桥接模式Bridge {

}

class Boy {
    public void pursue(MM mm) {
        Gift g = new WarmGift(new Flower());
    }
}

class MM {

}

class Gift {
    GiftImpl impl;        //聚合代替继承
}

class GiftImpl {

}

class Flower extends GiftImpl {

}

class WarmGift extends Gift {//温暖的礼物

    public WarmGift(GiftImpl gift) {
        this.impl = gift;
    }
}
