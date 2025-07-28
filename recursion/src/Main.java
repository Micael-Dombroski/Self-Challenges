import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int opt = -1;
        do {
            System.out.println("[1] Factorial");
            System.out.println("[2] Recursive sum");
            System.out.println("[3] Fibonacci");
            System.out.println("[4] Palindrome");
            System.out.println("[5] Power");
            System.out.println("[0] Exit");

            do {
                System.out.println("Choose an option: ");
                opt = scan.nextInt();
            } while(opt < 0 || opt > 5);
            switch (opt) {
                case 1 -> factorial();
                case 2 -> recSum();
                case 3 -> fibonacci();
                case 4 -> palindrome();
                case 5 -> power();
                default -> {
                    System.out.println("Exiting...");
                }
            }
        } while (opt != 0);
    }
    public static void factorial() {
        System.out.println("Write a natural number: ");
        int num = scan.nextInt();
        num = Math.abs(num);
        Factorial fact = new Factorial();
        System.out.println("The factorial of " + num + " is " + fact.numFac(num));
    }
    public static void recSum() {
        System.out.println("Write an integer number: ");
        int num = scan.nextInt();
        RecursiveSum rSum = new RecursiveSum();
        int sum = rSum.sum(num);
        System.out.println("Result: " + sum);
    }
    public static void fibonacci() {
        System.out.println("Write the 1st number in fibonacci sequence: ");
        int num1 = scan.nextInt();
        System.out.println("Write the 2nd number in fibonacci sequence: ");
        int num2 = scan.nextInt();
        System.out.println("Write in which position in the Fibonacci sequence you want to find the value: ");
        int numth = scan.nextInt();
        Fibonacci fbc = new Fibonacci();
        int nthElement = fbc.findNthElement(num1, num2, numth-2);
        System.out.println("The " + numth + "th element on the fibonacci sequence started with " + num1 + " and " + num2 + " is " + nthElement);
    }
    public static void palindrome() {
        System.out.println("Write something: ");
        String word = scan.next();
        Palindrome pl = new Palindrome();
        if(pl.isPalindrome(word))
            System.out.println("The word " + word + " is a palindrome");
        else
            System.out.println("The word " + word + " isn't a palindrome");
    }
    public static void power() {
        System.out.println("Write a number: ");
        int num = scan.nextInt();
        System.out.println("Write the exponential to raise the number: ");
        int exp = scan.nextInt();
        Power pw = new Power();
        int numExp = pw.exponentiation(num, exp);
        System.out.println("The number " + num + " raised to the exponent " + exp + " results in " + numExp);
    }
}