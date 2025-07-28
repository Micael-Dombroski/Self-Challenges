public class Fibonacci {
    public int findNthElement(final int n1, final int n2, final int nth) {
        int next = n1 + n2;
        if(nth == 0)
            return 0;
        if(nth == 1)
            return next;
        return findNthElement(n2, next, nth-1);
    }
}
