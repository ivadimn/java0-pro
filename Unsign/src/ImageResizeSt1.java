import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizeSt1 {
    private int newWidth;
    private File[] files;
    private String dstFolder;

    public ImageResizeSt1(int newWidth, File[] files, String dstFolder) {
        this.newWidth = newWidth;
        this.files = files;
        this.dstFolder = dstFolder;
    }

    public void resize() {

        try
        {
            int i = 0;
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );


                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = newImage.createGraphics();

                AffineTransform at = AffineTransform.getScaleInstance(newWidth / (double) image.getWidth(),
                        newHeight / (double) image.getHeight());
                g2.drawImage(image, at, null);
                g2.dispose();

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
