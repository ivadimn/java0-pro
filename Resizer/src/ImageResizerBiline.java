import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizerBiline {
    private int newWidth;
    private File[] files;
    private String dstFolder;

    public ImageResizerBiline(int newWidth, File[] files, String dstFolder) {
        this.newWidth = newWidth;
        this.files = files;
        this.dstFolder = dstFolder;
    }

    public void resize() {

        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage newImage = doResize(image, newWidth, newHeight);



                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);

            }
            System.out.println(Thread.currentThread().getName() + " was finished");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage doResize(BufferedImage image, int newW, int newH) {
        int h, w;
        float t, u;
        float spam;
        float k1, k2, k3, k4;
        int p1, p2, p3, p4;
        int red, green, blue;

        int oldW = image.getWidth();
        int oldH = image.getHeight();


        BufferedImage newImage = new BufferedImage(
                newW, newH, BufferedImage.TYPE_INT_RGB);

        for (int j = 0; j < newH; j++) {
            spam = (float) j / (float) (newH - 1) * (oldH - 1);
            h = (int) Math.floor(spam);
            if (h < 0) {
                h = 0;
            } else {
                if (h >= oldH - 1) {
                    h = oldH - 2;
                }
            }
            u = spam - h;

            for (int i = 0; i < newW; i++) {
                spam = (float) (i) / (float) (newW - 1) * (oldW - 1);
                w = (int) Math.floor(spam);
                if (w < 0) {
                    w = 0;
                } else {
                    if (w >= oldW - 1) {
                        w = oldW - 2;
                    }
                }
                t = spam - w;
                /* Коэффициенты */
                k1 = (1 - t) * (1 - u);
                k2 = t * (1 - u);
                k3 = t * u;
                k4 = (1 - t) * u;

                /* Окрестные пиксели: a[i][j] */
                //System.out.println(h + "   " + w);
                p1 = image.getRGB(w, h) & 0xffffffff;
                p2 = image.getRGB(w + 1, h) & 0xffffffff;
                p3 = image.getRGB(w + 1, h + 1) & 0xffffffff;
                p4 = image.getRGB(w + 1, h) & 0xffffffff;

                /* Компоненты */
                blue = (int) (p1 * k1 + p2 * k2 + p3 * k3 + p4 * k4);
                green = (int) ((p1 >> 8) * k1 + (p2 >> 8) * k2 + (p3 >> 8) * k3 + (p4 >> 8) * k4);
                red = (int)((p1 >> 16) * k1 + (p2 >> 16) * k2 + (p3 >> 16) * k3 + (p4 >> 16) * k4);

                newImage.setRGB(i, j,  (red << 16) | (green << 8) | (blue));

            }
        }

        return newImage;
    }
}
