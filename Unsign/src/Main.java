import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        int dec = 0x8fea80f1;
        long ldec = (long) dec & 0xffffffff;
        BigInteger bi = new BigInteger("8fea80f1", 16);
        System.out.println(dec);
        System.out.println(ldec);
        System.out.println(bi);
    }

}
