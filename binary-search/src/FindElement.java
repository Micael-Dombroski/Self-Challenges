import java.util.Scanner;

public class FindElement {
    //using Binary Search to find an element
    public static boolean findElement(int lo, int hi, final int element,final int[] elements) {
        int mid = lo + (hi - lo) / 2;
        if(elements[mid] == element) return true;
        if (hi - lo <= 1) return false;
        if(elements[mid] < element) lo = mid + 1;
        else if(elements[mid] > element) hi = mid - 1;
        return findElement(lo, hi, element, elements);
    }
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int[] elements = {-13,-4,0,1,2,3,5,6,7,8,9,10,23,45};
        System.out.println("Write a number: ");
        int num = Integer.valueOf(scan.nextLine());
        if(findElement(0, elements.length-1, num, elements))
            System.out.println("The number " + num + " is in the List");
        else
            System.out.println("The number " + num + "isn't in the List");
    }
}