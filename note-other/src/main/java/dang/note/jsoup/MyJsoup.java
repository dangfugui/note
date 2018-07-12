package dang.note.jsoup;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by dangqihe on 2016/9/29.
 */
public class MyJsoup {
    @Test
    public void test() {

        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc);
    }

    //拿到body
    @Test
    public void body() {
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        System.out.println(body);
    }

    @Test
    public void connect() throws IOException {
        Document doc = Jsoup.connect("http://www.jd.com/").get();
        String title = doc.title();
        System.out.println(title);
    }

    @Test
    public void connectData() throws IOException {
        Document doc = Jsoup.connect("http://jd.com/").data("user", "dangqihe")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();
        System.out.println(doc.title());
    }

    @Test
    public void testFile() {
        String fileName = "data/jd.com.htm";
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = this.getClass().getResource("/");
        System.out.println(url);
        File file = new File(url.toString());
        System.out.println(file.getParentFile().getParentFile().getPath());
        String htmlPath = file.getParentFile().getParentFile().getPath() + "/src/main/respurces/jd.com.htm";
        File html = new File(htmlPath);
        System.out.println(html.getPath());
        String url2 = this.getClass().getResource("/data/jd.com.htm").getPath();
        System.out.println(url2);
    }

    @Test
    public void parseFile() throws IOException {
        String path = this.getClass().getResource("/data/jd.com.htm").getPath();
        File file = new File(path);
        System.out.println(file.getPath());
        Document doc = Jsoup.parse(new FileInputStream(file), "gbk", "http://jd.com");

        System.out.println(doc.title());
    }

    @Test
    public void Elements() throws IOException {
        String path = this.getClass().getResource("/data/jd.com.htm").getPath();
        Document doc = Jsoup.parse(new File(path), "gbk", "http://jd.com");
        System.out.println(doc.title());
        Element content = doc.getElementsByTag("body").first();
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String href = link.attr("href");
            String text = link.text();
            System.out.println(text + ">>>>" + href);
        }
    }
}