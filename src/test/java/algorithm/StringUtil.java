package algorithm;

import java.util.Random;

public class StringUtil {

    private static final Random random = new Random();

    public static String randomNumStr() {
        String base = "0123456789";
        StringBuilder sb = new StringBuilder();
        char sign = random.nextDouble() < 0.5 ? '+' : '-';
        sb.append(sign);
        int MAX_LEN = 8;
        int len = random.nextInt(MAX_LEN) + 1;
        for (int i = 0; i < len; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }
}
