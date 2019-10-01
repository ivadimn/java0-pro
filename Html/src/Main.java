import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static final String PATH = "https://lenta.ru/";
    public static void main(String[] args) throws IOException {
        URL url = new URL(PATH);
        Document doc = Jsoup.parse(url, 10000);
        Elements elements = doc.select("img");
        elements.forEach(e -> System.out.println(e.attr("src")));
        String ss = "aaaa/123456.png";
        System.out.println(ss.substring(ss.lastIndexOf("/") + 1));
    }

    public static void getImage(String path) {
        BufferedImage image;
        try {
            URL url = new URL(path);
            image = ImageIO.read(url);

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
