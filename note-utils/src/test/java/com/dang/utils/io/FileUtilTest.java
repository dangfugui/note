package com.dang.utils.io;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * @Date Create in 2018/1/5
 */
public class FileUtilTest {

    @Before
    public void before() throws InterruptedException {
        //        Thread.sleep(10*1000);
    }

    @Test
    public void copyByJava() throws Exception { // 25  13
        File f = new File("D:\\download\\迅雷\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字"
                + ".BTBA\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字.BTBA.mp4");
        File a = new File(f.getPath() + ".copy");
        if (a.exists()) {
            a.delete();
        }
        FileUtil.copyByJava(f, a);
    }

    @Test
    public void copyByChannel() throws IOException {   //16  11
        File f = new File("D:\\download\\迅雷\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字"
                + ".BTBA\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字.BTBA.mp4");
        File a = new File(f.getPath() + ".copy");
        if (a.exists()) {
            a.delete();
        }
        FileUtil.copyByChannel(f, a);
    }

    @Test
    public void copyByImage() throws IOException, InterruptedException {    // 42   52
        File f = new File("D:\\download\\迅雷\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字"
                + ".BTBA\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字.BTBA.mp4");
        File a = new File(f.getPath() + ".copy");
        if (a.exists()) {
            a.delete();
        }
        FileUtil.copyByImage(f, a);
        Thread.sleep(10 * 1000);
    }

    @Test
    public void forChannel() throws IOException {       // 17  8   9
        File f = new File("D:\\download\\迅雷\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字"
                + ".BTBA\\【BT吧-www.btba.com.cn】夺金四贱客.Vier gegen die Bank.2017.1080p国德双语中字.BTBA.mp4");
        File a = new File(f.getPath() + ".copy");
        if (a.exists()) {
            a.delete();
        }
        FileUtil.forChannel(f, a);
    }

    @Test
    public void copy() throws Exception {
        int count = FileUtil.copy(new File("D:\\home\\video.mp4"), "D:\\home2\\video\\");
        System.out.println(count);
    }
}