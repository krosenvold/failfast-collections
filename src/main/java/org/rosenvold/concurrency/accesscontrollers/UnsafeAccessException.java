package org.rosenvold.concurrency.accesscontrollers;

public class UnsafeAccessException  extends RuntimeException {
    private final long threadId;
    private final String threadName;
    public UnsafeAccessException(String s) {
        super(s);
        this.threadId = Thread.currentThread().getId();
        this.threadName = Thread.currentThread().getName();
    }

    @Override
    public String toString() {
        return "UnsafeAccessException by " +
                "threadId=" + threadId +
                ", threadName='" + threadName + '\'';
    }
}
