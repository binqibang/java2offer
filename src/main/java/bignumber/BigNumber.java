package bignumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigNumber {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("201515455455");
        BigInteger b = new BigInteger("65168546");
        BigInteger res = a.divide(b);
        System.out.println(res);

        BigDecimal c = new BigDecimal("4664984844");
        BigDecimal d = new BigDecimal("66515665");
        BigDecimal res1 = c.divide(d, 2, RoundingMode.HALF_UP);
        System.out.println(res1);
    }
}
