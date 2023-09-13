package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<Driver> s = ServiceLoader.load(Driver.class);
        String url = "localhost:3306:mysql";
        Iterator<Driver> iterator = s.iterator();
        while (iterator.hasNext()) {
            Driver driver =  iterator.next();
            driver.connect(url);
        }
    }
}
