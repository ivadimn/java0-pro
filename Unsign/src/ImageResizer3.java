import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer3  {
    private int newWidth;
    private File[] files;
    private String dstFolder;

    public ImageResizer3(int newWidth, File[] files, String dstFolder) {
        this.newWidth = newWidth;
        this.files = files;
        this.dstFolder = dstFolder;
    }

    private BufferedImage resize(BufferedImage image, int width, int height, RenderingHints.Key key, Object interpolation) {

        BufferedImage newImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = newImage.createGraphics();

        g2.setRenderingHint(key, interpolation);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return newImage;
    }

    public void doResize() {

        try
        {
            int i = 0;
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                //int newHeight = (int) Math.round(
                //        image.getHeight() / (image.getWidth() / ((double) newWidth * 2))
                //);

                BufferedImage newImage = resize(image, image.getWidth() / 2, image.getHeight() / 2,
                        RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);


                int newHeight = (int) Math.round(
                        newImage.getHeight() / (newImage.getWidth() / (double) newWidth));

                newImage = resize(newImage, newWidth, newHeight,
                        RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

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
