public class Factorial {
    public int numFac(final int num) {
        if(num == 0) {
            return 1;
        }
        return num * (numFac(num-1));
    }
}