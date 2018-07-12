package dang.work.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by dangqihe on 2016/10/18.
 */
public class YuQingP2P {
    @Test
    public void test() throws IOException, InterruptedException {
        String test = "(?<=发布时间:.).+";
        for (int i = 10; i > 0; i--) {
            String url = "http://www.wdzj.com/front_search-list?type=1&key=%25E5%2580%259F%25E5%2591%2597";
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elements = doc.select("div.text");////ul[@class='zllist']/li/div/h3/a
            System.out.println(elements.size());
            for (Element e : elements) {
                Elements te = e.select("p a");
                System.out.println(te.attr("href"));
            }
            Thread.sleep(100);
        }
    }

    @Test
    public void test1() throws IOException {
        String url = "http://www.p2peye.com/search.php?mod=portal&srchtxt=app";
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Elements sel = doc.select("div.ui-keynews");
        System.out.println(sel.size());

    }

    @Test
    public void utf() throws UnsupportedEncodingException {
        String str = "征信";
        String s = "征信";
        byte[] b = s.getBytes("utf-8");//编码
        String sa = new String(b, "gbk");//解码:用什么字符集编码就用什么字符集解码
        System.out.println(sa);

        b = sa.getBytes("utf-8");//编码
        sa = new String(b, "utf-8");//解码
        System.err.println(sa);
    }
}
