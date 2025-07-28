public class RecursiveSum {
    public int sum(final int num) {
        if(num == 0)
            return 0;
        return num + sum(num-1);
    }
}
