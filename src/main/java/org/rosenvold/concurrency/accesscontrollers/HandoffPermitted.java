package org.rosenvold.concurrency.accesscontrollers;

import sun.awt.windows.ThemeReader;

/**
 * Enforces a single thread but allows thread-handoff by another thread calling setter
 */
public class HandoffPermitted<T> {

    private volatile T item;
    private long settingThread;

    public HandoffPermitted(T item) {
        this.item = item;
    }

    public HandoffPermitted() {
    }

    public T getItem() {
        if (!(Thread.currentThread().getId() == settingThread)){
           throw new UnsafeAccessException("Reading by thread that didnt write value");
        }
        return item;
    }

    public void setItem(T item) {
        this.item = item;
        this.settingThread = Thread.currentThread().getId();
    }
}
