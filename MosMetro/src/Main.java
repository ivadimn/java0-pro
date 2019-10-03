import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static final String PATH = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
    public static void main(String[] args) throws IOException {
        URL url = new URL(PATH);
        Document doc = Jsoup.parse(url, 10000);
        Elements elements = doc.select("table");
        Element table = elements.stream()
                .filter(e -> e.className().equalsIgnoreCase("standard sortable"))
                .findFirst().get();
        Elements trs = table.select("tr");
        for (Element tr : trs) {
            System.out.println(tr.select("td[data-sort-value]").select("span.sortkey").text());
            Elements tds = tr.select("td");
            tds.forEach(td -> System.out.println(td.text()));
        }
        System.out.println(trs.size());
    }
}
