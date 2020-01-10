import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizerSt  extends Thread {

    private int newWidth;
    private File[] files;
    private String dstFolder;

    public ImageResizerSt(int newWidth, File[] files, String dstFolder) {
        this.newWidth = newWidth;
        this.files = files;
        this.dstFolder = dstFolder;
    }

    @Override
    public void run() {
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

                BufferedImage newImage = Scalr.resize(image, Scalr.Method.BALANCED, Scalr.Mode.AUTOMATIC,
                        image.getWidth() / 2, image.getHeight() / 2, Scalr.OP_ANTIALIAS);

                newImage = Scalr.resize(newImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC,
                        newWidth, newHeight, Scalr.OP_ANTIALIAS);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);

            }
            System.out.println(Thread.currentThread().getName() + " was finished");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
