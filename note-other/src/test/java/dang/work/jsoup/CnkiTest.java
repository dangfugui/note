package dang.work.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dangqihe on 2016/10/25.
 */
public class CnkiTest {

    String str = "\\w+";

    @Test
    public void test() throws IOException {
        String url =
                "http://papers.cnki.net/View/DataCenter/Scholar"
                        + ".ashx?nmt=nm&sm=1&nmv=&id=SC&db=0&cp=57&p=1&uid=-1&ut=北京师范大学";
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Elements trs = doc.select("div.list");
        for (Element e : trs) {
            Elements te = e.select("tbody tr td:gt(0):lt(2)");//td.first
            System.out.println("text==" + te.text());
        }

    }
}
