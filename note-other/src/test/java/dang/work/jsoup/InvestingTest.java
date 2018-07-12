package dang.work.jsoup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import dang.note.httpclient.HttpClientUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dangqihe on 2016/10/17.
 */
public class InvestingTest {
    @Test
    public void test() throws IOException {
        String url = "http://www.investing.com/indices/smallcap-2000-historical-data";
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Elements trs = doc.select("table#curr_table tbody tr:lt(1)");
        for (Element e : trs) {
            Elements te = e.select("td.first");//td.first
            System.out.println(te.text());
        }

    }

    @Test
    public void test2() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("http://www.investing.com/indices/switzerland-20-historical-data");
        System.out.println("reponse content:" + responseContent);
    }

    @Test
    public void testMongon() {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient("172.24.7.58", 27017);
        //        MongoClient client=new MongoClient()MongoClient
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("dangDB");
    }
}
