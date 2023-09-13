package spi;

public class PgDriver implements Driver{
    @Override
    public void connect(String url) {
        System.out.println("PostgreSQL " + url);
    }
}
