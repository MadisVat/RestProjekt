package ee.bcs.valiit;

public class Lesson3 {

    public static String reverseString(String a) {
        // tagasta string tagurpidi
        // Näiteks: a = "Test";
        // return tseT";

        String result = "";
        for (int i = a.length(); i > 0; i--) {
            result = result + a.substring(i - 1, i);
        }
        return result;
    }

    public static int min(int a, int b, int c) {
        // tagastab minimaalse väärtuse
        if (a <= b && a <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        }
        return c;
    }

    static int algus(int i) {
        //  kui i on paaris arv tagasta i/2
        //  kui i on paaritu arv tagasta i*3+1

        if (i % 2 == 0) {
            return i / 2;
        }
        return i * 3 + 1;
    }

    public static int sum(int x, int y) {
        //liida kokku ja tagasta x ja y väärtus

        int a = x + y;
        return a;
        //int a = Integer.sum(x, y);
    }

    public static int sum2(int[] x) {
        // liida kokku kõik numbrid massivis x

        int arrSum = 0;
        for (int i = 0; i < x.length; i++) {
            arrSum += x[i];
        }
        return arrSum;
    }

    public static int factorial(int x) {
        // tagasta x faktoriaal.
        // Näiteks: x = 5
        // return 4*3*2*1 = 24
        if (x > 1) {
            return x * factorial(x - 1);
        } else if (x == 1 || x == 0) {
            return 1;
        } else {
            throw new RuntimeException("Error! Negatiivsest arvust ei saa faktoriaali võtta");
        }
    }

    public static int[] sort(int[] a) {
        // sorteeri massiiv suuruse järgi
        // Näiteks {2, 6, 8, 1}
        // Väljund {1, 2, 6, 8}

        int temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;

                }
            }
        }
        return a;
    }
}

