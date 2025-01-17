package basic;

import java.util.Scanner;

public class Lesson01 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Hello " + name + "!");
        sc.close();
    }
}
