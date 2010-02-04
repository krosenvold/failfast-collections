package org.jdogma.concurrency;

import org.jdogma.concurrency.accesscontrollers.ThreadAccessController;

import java.util.Iterator;

/**
 * @author Kristian Rosenvold
 */
public class DiagnosticIterator<T> implements Iterator<T>
{
    private final ThreadAccessController threadAccessController;
    private final Iterator<T> underlying;

    public DiagnosticIterator( ThreadAccessController threadAccessController, Iterator<T> underlying )
    {
        this.threadAccessController = threadAccessController;
        this.underlying = underlying;
    }

    public boolean hasNext()
    {
        return underlying.hasNext();
    }

    public T next()
    {
        return underlying.next();
    }

    public void remove()
    {
        threadAccessController.checkWriteAccess();
        underlying.remove();
    }
}
