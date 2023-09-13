package designpattern.iterator;

public class MyListTest {
    public static void main(String[] args) {
        String[] ss = new String[] {"my", "name", "is", "david"};
        MyList<String> list = new MyList<>(ss);
        for (String s : list) {
            System.out.printf("%s\t", s);
        }
    }
}
