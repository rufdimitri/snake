package rd.arrays;

public class Arrays1 {
    public static void main(String[] args) {

    }

    static void arrayBasics() {
        int[] array1 = new int[5];
        array1[0] = 10;
        array1[1] = 20;
        array1[2] = 30;
        array1[3] = 40;
        array1[4] = 50;

        System.out.print("array1: ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + ", ");
        }
        System.out.println();

        // array2
        int[] array2 = { 90, 80, 70, 60 };
        System.out.print("array2: ");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + ", ");
        }
        System.out.println();
    }
}
