package designpattern.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyList<E> implements Iterable<E> {

    private int size;
    private Object[] data;

    public MyList(Object[] data) {
        this.size = data.length;
        this.data = data;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }


    private class MyIterator<T> implements Iterator<T> {
        private int cursor;

        public MyIterator() {
            cursor = 0;
        }

        public boolean hasNext() {
            return cursor < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object value = data[cursor];
            cursor++;
            return (T) value;
        }
    }
}
