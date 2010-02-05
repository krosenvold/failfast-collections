package org.jdogma.concurrency.accesscontrollers;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An access controller that enforces that only 1 thread can access the data.
 *
 * It is mutable, in the sense that a client can call "resetOwningThread" and the next
 * thread to use the instance becomes the owner.
 * @author <a href="mailto:kristian@zenior.no">Kristian Rosenvold</a>
 */
public class SingleThreadAccessController implements ThreadAccessController {
    private final Callable<String> stringCallable;
    private final AtomicReference<Thread> owningThread = new AtomicReference<Thread>();


    public SingleThreadAccessController() {
        this(null);
    }

    public SingleThreadAccessController(Callable<String> stringCallable) {
        this.stringCallable = stringCallable;
    }

    public void lockToThisThread() {
        this.owningThread.set( Thread.currentThread());
    }

    /**
     * Please understand that this makes no guarantees about visibility. In other words, if you have to call
     * this method you have a problem.
     */
    public void unlockFromThisThread() {
        this.owningThread.set( null);
    }

    public void checkWriteAccess() {
        final Thread owner = owningThread.get();
        if (owner == null) return;

        if (!Thread.currentThread().equals(owner)){
            String result = "";
            if (stringCallable != null){
                try {
                    result = stringCallable.call();
                } catch (Exception e) {
                    throw new ThreadAccessViolationException("Error while invoking callable, to signal illegal access.", e);
                }
            }
            throw new ThreadAccessViolationException("The current thread is not allowed to access this data."
                    + (result != null ? "It is owned by " + result : ""));
        }
    }

    public void checkReadAccess() {
        checkWriteAccess();
    }
}
