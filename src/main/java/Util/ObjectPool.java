package main.java.Util;

import main.java.Intefaces.ObjectFactory;
import main.java.Intefaces.Pool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ObjectPool<T> {


    private Set<T> available = new HashSet<>();
    private List<T> inUse;

    public ObjectPool(List<T> inUse) {
        this.inUse = inUse;

    }

    protected abstract T create();


    public synchronized T checkOut() {
        if (available.isEmpty()) {
            available.add(create());
        }
        T instance = available.iterator().next();
        available.remove(instance);
        synchronized (inUse) {
            inUse.add(instance);
        }
        return instance;
    }

    public synchronized void checkIn(T instance) {
        available.add(instance);
    }

    @Override
    public synchronized String toString() {
        return String.format("Pool available=%d inUse=%d", available.size(), inUse.size());
    }
}