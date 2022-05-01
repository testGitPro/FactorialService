import java.math.BigInteger;
import java.util.concurrent.Callable;

public class FactorialCalc implements Callable<BigInteger> {

    private long number;

    public FactorialCalc(long number) {
        this.number = number;
    }


    @Override
    public BigInteger call() throws Exception {

        return getFactorial(number);
    }

    public static BigInteger getFactorial(long number) {

        if (number <= 1) {
            return BigInteger.valueOf(1);
        } else {
            return BigInteger.valueOf(number).multiply(getFactorial(number - 1));
        }
    }
}
