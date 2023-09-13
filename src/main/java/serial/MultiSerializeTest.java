package serial;

import java.io.*;

public class MultiSerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees.txt"));
        Employee e1 = new Employee("David", 2000);
        Employee e2 = new Employee("James", 3500);
        oos.writeObject(e1);
        oos.writeObject(e2);
        oos.writeObject(e1);            // repeat serialize

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employees.txt"));
        Employee e3 = (Employee) ois.readObject();
        Employee e4 = (Employee) ois.readObject();
        Employee e5 = (Employee) ois.readObject();

        System.out.println(e3 == e5);   // true
        System.out.println(e4);
    }
}
