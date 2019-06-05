public class Main {
    public static void main(String[] args) {
        int katyaAge = 19;
        int mishaAge = 40;
        int vasiaAge = 30;

        int max = -1;
        int min = -1;
        int middle = -1;

        if (katyaAge > mishaAge) {
            if (katyaAge > vasiaAge) {
                max = katyaAge;
                if (mishaAge > vasiaAge) {
                    middle = mishaAge;
                    min = vasiaAge;
                }
                else {
                    middle = vasiaAge;
                    min = mishaAge;
                }
            }
            else {
                max = vasiaAge;
                middle = katyaAge;
                min = mishaAge;
            }
        }
        else {
            if (mishaAge > vasiaAge) {
                max = mishaAge;
                if (katyaAge > vasiaAge) {
                    middle = katyaAge;
                    min = vasiaAge;
                }
                else {
                    middle = vasiaAge;
                    min = katyaAge;
                }
            }
            else {
                max = vasiaAge;
                middle = mishaAge;
                min = katyaAge;
            }
        }

        System.out.println("MAX =    " + max);
        System.out.println("MIN =    " + min);
        System.out.println("MIDDLE = " + middle);


    }
}
