package dang.work.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dangqihe on 2016/10/15.
 */
public class SogouTest {
    @Test
    public void test() throws IOException {
        String url = "http://news.p2peye.com/article-486885-1.html";
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Elements elements = doc.select("td#article_content p");
        for (Element element : elements) {
            //String html=element.attr("href");
            System.out.println(element.text());
        }
    }
}
