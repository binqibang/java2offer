package spi;

public class MysqlDriver implements Driver{

    @Override
    public void connect(String url) {
        System.out.println("MySQL " + url);
    }
}
