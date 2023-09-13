package serial;

import java.io.*;

public class SerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Serialize
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.txt"));
        Employee e1 = new Employee("Tom", 2000d);
        System.out.println(e1);
        oos.writeObject(e1);

        // Deserialize
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.txt"));
        Employee e2 = (Employee) ois.readObject();
        System.out.println(e2);
    }
}
