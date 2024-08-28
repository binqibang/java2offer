package designpattern.iterator;

import org.junit.jupiter.api.Test;

class MyListTest {
    @Test
    void iterator() {
        MyList<Integer> list = new MyList<>(new Integer[]{1, 2, 3, 4, 5});
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}