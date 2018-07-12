package dang.work.xppath;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by dangqihe on 2016/10/15.
 */
public class SogouTest {
    @Test
    public void crawler() throws IOException {
        String url = "http://www.sogou.com/web?query=%E5%A4%A9%E4%B8%8B%E6%96%B0%E9%97%BB&page=5";
        Document doc = Jsoup.connect(url).get();
        Document.OutputSettings out = doc.outputSettings();
        Elements elements = doc.select("#pagebar_container a[href]");
        for (Element element : elements) {
            String html = element.attr("href");
            System.out.println(html);
        }
    }

    @Test
    public void init() throws Exception {
        // 创建Document对象
        //        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //        dbf.setValidating(false);
        //        DocumentBuilder db = dbf.newDocumentBuilder();
        //        String path="D:\\Data\\web\\天下新闻 - 搜狗搜索.htm";
        //        File file=new File(path);
        //        System.out.println(file.getPath());
        //        org.w3c.dom.Document doc = db.parse(new FileInputStream(file));

        //        // 创建XPath对象
        //        XPathFactory factory = XPathFactory.newInstance();
        //        XPath xpath = factory.newXPath();
        //        NodeList nodeList = (NodeList) xpath.evaluate("//*[@id='pagebar_container']/a",
        //                doc, XPathConstants.NODESET);
        //        for (int i = 0; i < nodeList.getLength(); i++) {
        //            System.out.println(nodeList.item(i).getNodeName() + "-->"
        //                    + nodeList.item(i).getTextContent());
        //        }
        //        System.out.println();
    }
}
