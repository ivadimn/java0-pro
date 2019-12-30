import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;

public class ImageResizerSt {
    private int newWidth;
    private File[] files;
    private String dstFolder;

    public ImageResizerSt(int newWidth, File[] files, String dstFolder) {
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


                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = newImage.createGraphics();

                AffineTransform at = AffineTransform.getScaleInstance(newWidth / (double) image.getWidth(),
                        newHeight / (double) image.getHeight());

                BufferedImageOp biop = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
                newImage = biop.createCompatibleDestImage(image, image.getColorModel());
                biop.filter(image, newImage);
                g2.drawImage(image, biop, newWidth, newHeight);
                ///g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, biop);
                g2.drawImage(image, at, null);
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
