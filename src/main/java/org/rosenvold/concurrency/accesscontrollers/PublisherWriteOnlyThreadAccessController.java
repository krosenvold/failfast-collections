package org.rosenvold.concurrency.accesscontrollers;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An access controller that allows the object creator infinite write access, but only until another thread
 * hits the object. After a second thread is seen, it becomes immutable.
 */

public class PublisherWriteOnlyThreadAccessController
    extends AccessController
    implements Serializable, ThreadAccessController
{

    private final Thread creatorThread;
    private volatile boolean otherThreadSeen = false;

    public PublisherWriteOnlyThreadAccessController(  )
    {
        super();
        this.creatorThread = Thread.currentThread();
    }

    final Set<Thread> seenThreads = Collections.synchronizedSet(new HashSet<Thread>());


    private boolean checkForOtherThread(){
        final Thread thread = Thread.currentThread();
        if (!thread.equals( creatorThread )) otherThreadSeen = false;
        return otherThreadSeen;
    }


    public void checkWriteAccess() {
        if (checkForOtherThread()){
            throw new UnsafeCollectionAccessException( "Collection was attempted modified after other threads had seen it" + getStackTrace());
        }
        
    }

    public void checkReadAccess() {
        checkForOtherThread();
    }

    public void setHappensBefore(){
        otherThreadSeen = true;
    }
}