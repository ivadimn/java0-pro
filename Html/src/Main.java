import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static final String PATH = "https://lenta.ru/";
    public static final String PATH1 = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";

    public static void main(String[] args) throws IOException {
        URL url = new URL(PATH1);
        /*Document doc = Jsoup.parse(url, 10000);
        Elements elements = doc.select("img");
        for (Element e : elements) {
            String src = e.attr("src");
            if (!src.contains("http")) {
                src = PATH + src;
            }
            saveImage(src);
        }*/
        parseMetro(url);
    }

    public static void saveImage(String path) {
        try {
            URL url = new URL(path);
            InputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream out = new FileOutputStream("images" + path.substring(path.lastIndexOf("/")));
            byte[] buffer = new byte[4096];
            int n = 0;
            while((n = in.read(buffer, 0, 4096)) != -1) {
                out.write(buffer, 0, n);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("Сохранено - " + "images" + path.substring(path.lastIndexOf("/")));
        }
        catch (IOException ex) {
            System.out.println("Не удалось получить изображение = " + ex.getMessage());
        }
    }

    public static void parseMetro(URL url) throws IOException {
        Document doc = Jsoup
                        .connect(PATH1)
                        .maxBodySize(0)
                        .get();
        Elements elements = doc.select("table.standard");
        System.out.println(elements.size());
    }
}
