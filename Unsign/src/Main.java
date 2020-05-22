import java.io.File;

public class Main {

    public static final String srcFolder = "/home/vadim/images";
    public static final String dstFolder = "/home/vadim/newimages";
    public static final  int newWidth = 300;

    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        ImageResizer3 im = new ImageResizer3(newWidth, files, dstFolder);
        im.doResize();

        System.out.println("Общее время работы = " + (System.currentTimeMillis() - startTime) + " ms");


    }

}
