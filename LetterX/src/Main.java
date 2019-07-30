public class Main {
    public static void main(String[] args) {
        int length = 7;
        String[] lx = generateX(length);
        for (int i = 0; i < length; i++) {
            System.out.println(lx[i]);
        }
    }

    public static String[] generateX(int length) {
        String[] sm = new String[length];
        for (int i = 0; i < length; i++) {
            char[] chs = new char[length];
            for (int j = 0; j < length; j++) {
                if (j == i || j == length - 1 - i)
                    chs[j] = 'x';
                else {
                    chs[j] = ' ';
                }
            }
            sm[i] = new String(chs);
        }
        return sm;
    }
}
