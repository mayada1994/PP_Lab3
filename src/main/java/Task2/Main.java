package Task2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of array:");
        int size = in.nextInt();
        CustomArray arr = new CustomArray();
        arr.createArray(size);
        arr.printArray();
        System.out.println("");
        arr.sumArray();
    }

}
