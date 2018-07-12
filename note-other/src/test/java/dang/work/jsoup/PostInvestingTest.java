package dang.work.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dangqihe on 2016/10/15.
 */
public class PostInvestingTest {
    @Test
    public void test() throws IOException {
        //http://www.investing.com/instruments/HistoricalDataAjax?action=historical_data&curr_id=170&end_date=10/15
        // /2016&interval_sec=Daily&st_date=09/15/1971
        Document doc = Jsoup.connect("http://www.investing.com/instruments/HistoricalDataAjax")
                .data("action", "historical_data")
                .data("curr_id", "170")
                .data("end_date", "10/15/2016")
                .data("interval_sec", "Daily")
                .data("st_date", "09/15/2016")
                .userAgent("Mozilla")
                .timeout(3000)
                .post();
        Elements trs = doc.select("tr");
        System.out.println(trs.size());
        System.out.println(doc.title());
    }
}
