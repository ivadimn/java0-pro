import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizerAffin {
    private int newWidth;
    private File[] files;
    private String dstFolder;

    public ImageResizerAffin(int newWidth, File[] files, String dstFolder) {
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

                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                double scaleX = newWidth / (double) image.getWidth();
                double scaleY = newHeight / (double) image.getHeight();


                /*AffineTransform transform = new AffineTransform();
                transform.scale(scaleX, scaleY);

                AffineTransformOp scaleOp =
                        new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
                newImage = scaleOp.filter(image, newImage);*/

                Graphics2D graphics2D = newImage.createGraphics();
                AffineTransform xform = AffineTransform.getScaleInstance(scaleX, scaleY);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.drawImage(image, xform, null);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);

                graphics2D.dispose();


            }
            System.out.println(Thread.currentThread().getName() + " was finished");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
