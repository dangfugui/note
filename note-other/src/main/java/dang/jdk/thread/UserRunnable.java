package dang.jdk.thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by dangqihe on 2017/2/7.
 */
public class UserRunnable {

    public static void main(String ages[]) {
        for (int i = 0; i < 100; i++) {
            Keyword keyword = new Keyword();
            keyword.page = i;
            new Thread(keyword).start();
        }
        System.out.println("end");
    }
}

class Keyword implements Runnable {
    public int page = 0;

    public void run() {
        Document doc = null;
        try {
            doc = Jsoup.connect(
                    "http://g.jsf.jd.local/com.jd.jrdp.clearsky.service"
                            + ".KeywordService/keywordService/getProjectKeywords/12624?pd=16&pn="
                            + page + "&ps=2").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("page:" + page + ">>>" + doc.select("body"));
    }
}
