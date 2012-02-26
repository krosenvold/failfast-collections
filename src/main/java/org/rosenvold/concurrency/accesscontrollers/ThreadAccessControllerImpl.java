package org.rosenvold.concurrency.accesscontrollers;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ThreadAccessControllerImpl extends AccessController
    implements Serializable, ThreadAccessController
{
    final Set<Thread> seenThreads = Collections.synchronizedSet(new HashSet<Thread>());
    Thread lastThread;


    public void checkThreadAccess() {
        final Thread thread = Thread.currentThread();
        if (seenThreads.contains(thread)) {
            if (!thread.equals(lastThread)) {
                throw new UnsafeCollectionAccessException("The thread is accessing this collection intertwined with others, constructed at:\n" + getStackTrace());
            }
        } else {
            seenThreads.add(thread);
        }
        lastThread = thread;
    }
    public void checkWriteAccess() {
        checkThreadAccess();
    }
    
    public void checkReadAccess() {
        checkThreadAccess();
    }

}