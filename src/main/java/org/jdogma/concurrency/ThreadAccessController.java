package org.jdogma.concurrency;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ThreadAccessController implements Serializable {
    final Set<Thread> seenThreads = Collections.synchronizedSet(new HashSet<Thread>());
    Thread lastThread;

    public ThreadAccessController() {
    }

    void checkThreadAccess() {
        final Thread thread = Thread.currentThread();
        System.out.println("thread = " + thread);
        if (seenThreads.contains(thread)) {
            if (!thread.equals(lastThread)) {
                throw new UnsafeCollectionAccessException("The thread is accessing this collection intertwined with others");
            }
        } else {
            seenThreads.add(thread);
        }
        lastThread = thread;
    }
}