package basic;

public class Lesson02 {
  public static void main(String[] args) {
//    int[] numbers = {1, 2, 3, 4, 5};
//    for (int number : numbers) {
//      System.out.println(number);
//    }

    Adder adder = Integer::sum;
    System.out.println(adder.add(1, 2));
  }
}
