public class Power {
    public int exponentiation(final int num, final int exp) {
        if(exp == 0)
            return 1;
        return num * exponentiation(num, exp-1);
    }
}
