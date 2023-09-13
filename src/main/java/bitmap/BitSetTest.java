package bitmap;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        int [] nums = new int [] {1, 2, 3, 22, 0, 3, 63};
        BitSet bitSet  = new BitSet(1);
        System.out.println(bitSet.size());   // 64
        bitSet  = new BitSet(65);
        System.out.println(bitSet.size());   // 128
        bitSet  = new BitSet(23);
        System.out.println(bitSet.size());   // 64

        for (int num : nums) {
            bitSet.set(num, true);
        }

        System.out.println(bitSet.get(22));
        System.out.println(bitSet.get(60));
    }
}
