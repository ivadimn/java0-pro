import java.io.File;

public class Main {

    public static final String srcFolder = "/home/vadim/images";
    public static final String dstFolder = "/home/vadim/newimages";
    public static final  int newWidth = 300;

    public static void main(String[] args) {
        int numProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Количество ядер процессора - " + numProcessors);
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        if (files.length % numProcessors != 0) {
            numProcessors -= 1;
        }
        int lenArray = files.length / numProcessors;
        int startPos = 0;
        for (int i = 0; i < numProcessors; i++) {
            File[] f = new File[lenArray];
            System.arraycopy(files, startPos, f, 0, lenArray);
            startPos += lenArray;
            new ImageResizer(newWidth, f, dstFolder).start();
        }
        int ostatok = files.length % numProcessors;
        if ( ostatok != 0) {
            File[] f = new File[ostatok];
            System.arraycopy(files, startPos, f, 0, ostatok);
            new ImageResizer(newWidth, f, dstFolder).start();
        }

    }

}
