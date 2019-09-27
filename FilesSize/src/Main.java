import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main  {

    public static void main(String[] args) throws IOException {

        DecimalFormat decimalFormat = new DecimalFormat("###.0");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к папке :");
        String path = reader.readLine();
        File folder = new File(path);
        //double size = calculateFolderSize(folder);
        //String strSize = SizeConverter((long) size);
        System.out.println(Multe.mult(10.0, 10.0));
        /*if (size >= 1_099_511_627_776L) {
            size /= 1_099_511_627_776L;
            strSize = decimalFormat.format(size) + " Tbyte";
        }
        else if (size >= 1_073_741_824) {
            size /= 1_073_741_824;
            strSize = decimalFormat.format(size) + " Gbyte";
        }
        else if (size >= 1_048_576) {
            size /= 1_048_576;
            strSize = decimalFormat.format(size) + " Mbyte";
        }
        else if (size >= 1_024) {
            size /= 1_024;
            strSize = decimalFormat.format(size) + " Kbyte";
        }
        else {
            strSize = String.format("%d", (int)size) + " byte";
        }*/

        //System.out.println("Размер папки: " + folder + " с подпапками = " + strSize);
    }

    public static double calculateFolderSize(File folder) {
        double fsize = 0;
        File [] fileList = folder.listFiles();
        for (File f : fileList) {
            if (f.isDirectory()) {
                fsize += calculateFolderSize(f);
            }
            else {
                //System.out.println(f.getPath() + " - " + f.length() + " байт");
                fsize += f.length();
            }
        }
        return fsize;
    }

    private static String SizeConverter(long size){
        if (size < 1024) return size + " Б";
        int exp = (int) (Math.log(size) / (Math.log(1024)));
        char unitsPrefix = "КМГТПЭ".charAt(exp - 1);
        return String.format("%.2f %sБ", size / Math.pow(1024, exp), unitsPrefix);
    }
}
