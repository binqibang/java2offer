package concurrency.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockList<E> {
    private ReadWriteLock lock;
    private List<E> list;

    public ReadWriteLockList() {
        this.lock = new ReentrantReadWriteLock();
        this.list = new ArrayList<>();
    }

    public E get(int idx) {
        lock.readLock().lock();
        try {
            return list.get(idx);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void add(E elem) {
        lock.writeLock().lock();
        try {
            list.add(elem);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
