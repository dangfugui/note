package dang.note.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by dangqihe on 2016/9/29.
 */
public class MyJsoupElement {
    private Document doc;

    @Before
    public void before() throws IOException {
        String path = this.getClass().getResource("/data/xxx.com.htm").getPath();
        doc = Jsoup.parse(new File(path), "gbk", "http://xxx.com");
    }

    @Test
    public void select() {
        Elements links = doc.select("a[href]");
        System.out.println(links.size());
    }

    @Test
    public void attr() {
        doc.select("div a").attr("rel", "dang");
        Elements elements = doc.select("div a");
        System.out.println(elements.size());
        System.out.println(elements.get(0).attr("rel"));
    }

    @Test
    public void html() {
        Element div = doc.select("div").first();
        div.html("<p>dang</p>");
        div.prepend("<p>prepend</p>");
        div.append("<p>append</p>");
        div.wrap("<dang></dang>");
        System.out.println(div.html());
        System.out.println(div.parent().tagName());
        div.text("text");
        System.out.println(div.html());

    }

    @Test
    public void clean() {
        String unsafe =
                "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
        String safe = Jsoup.clean(unsafe, Whitelist.basic());
        System.out.println(safe);// now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>
    }
}
