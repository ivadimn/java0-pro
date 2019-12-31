import java.io.File;
import java.math.BigInteger;

public class Main {

    public static final String srcFolder = "/home/vadim/images";
    public static final String dstFolder = "/home/vadim/newimages";
    public static final  int newWidth = 300;

    public static void main(String[] args) {

        int dec = 0x8fea80f1;
        long ldec = (long) dec & 0xffffffff;
        BigInteger bi = new BigInteger("8fea80f1", 16);
        System.out.println(dec);
        System.out.println(ldec);
        System.out.println(bi);
        long startTime = System.currentTimeMillis();

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        ImageResizeSt1 im = new ImageResizeSt1(newWidth, files, dstFolder);
        im.resize();

        System.out.println("Общее время работы = " + (System.currentTimeMillis() - startTime) + " ms");


    }

}
