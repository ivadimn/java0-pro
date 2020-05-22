import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String srcFolder = "/home/vadim/images";
    public static final String dstFolder = "/home/vadim/newimages";
    public static final  int newWidth = 300;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        int numProcessors = Runtime.getRuntime().availableProcessors();
        long startTime = System.currentTimeMillis();
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
            ImageResizerSt t = new ImageResizerSt(newWidth, f, dstFolder);
            threadList.add(t);
            t.start();

        }
        int ostatok = files.length % numProcessors;
        if (ostatok != 0) {
            File[] f = new File[ostatok];
            System.arraycopy(files, startPos, f, 0, ostatok);
            ImageResizerSt t = new ImageResizerSt(newWidth, f, dstFolder);
            threadList.add(t);
            t.start();

        }

        for (Thread t : threadList) {
            try {
                t.join();
            }
            catch(InterruptedException e) {
            }
        }

        System.out.println("Общее время работы = " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
