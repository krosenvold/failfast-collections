package org.jdogma.concurrency.usagemonitors;

import org.jdogma.concurrency.accesscontrollers.ThreadAccessController;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:kristian@zenior.no">Kristian Rosenvold</a>
 */
public class UsageMonitor implements ThreadAccessController {
    Set<Thread> allThreads = Collections.synchronizedSet(new LinkedHashSet<Thread>());
    Set<Thread> writers = Collections.synchronizedSet(new HashSet<Thread>());

    public void checkWriteAccess() {
        final Thread e = Thread.currentThread();
        allThreads.add(e);
        writers.add( e);
    }

    public void checkReadAccess() {
        allThreads.add(Thread.currentThread());
    }

    public String describe(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Thread allThread : allThreads) {
            stringBuilder.append( allThread.getName());
            if (writers.contains( allThread))  stringBuilder.append( " (write)");
            stringBuilder.append( "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Usage of instance");
        System.out.println(describe());
        super.finalize();
    }
}
