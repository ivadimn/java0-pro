import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static final String PATH = "https://lenta.ru/";
    public static void main(String[] args) throws IOException {
        URL url = new URL(PATH);
        Document doc = Jsoup.parse(url, 10000);
        Elements elements = doc.select("img");
        elements.forEach(e -> getImage(e.attr("src")));
    }

    public static void getImage(String path) {
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
}
