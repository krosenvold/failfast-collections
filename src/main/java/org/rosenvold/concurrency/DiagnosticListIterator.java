package org.rosenvold.concurrency;

import org.rosenvold.concurrency.accesscontrollers.ThreadAccessController;

import java.util.ListIterator;

/**
 * @author Kristian Rosenvold
 */
public class DiagnosticListIterator<T> implements ListIterator<T>
{
    private final ThreadAccessController threadAccessController;
    private final ListIterator<T> underlying;
    private final boolean listSemantics;

    public DiagnosticListIterator( ThreadAccessController threadAccessController, ListIterator<T> underlying,
                                   boolean listSemantics )
    {
        this.threadAccessController = threadAccessController;
        this.underlying = underlying;
        this.listSemantics = listSemantics;
    }

    public boolean hasNext()
    {
        return underlying.hasNext();
    }

    public T next()
    {
        return underlying.next();
    }

    public boolean hasPrevious()
    {
        return underlying.hasPrevious();
    }

    public T previous()
    {
        return underlying.previous();
    }

    public int nextIndex()
    {
        return underlying.nextIndex();
    }

    public int previousIndex()
    {
        return underlying.previousIndex();
    }

    public void remove()
    {
        threadAccessController.checkWriteAccess();
        underlying.remove();
    }

    public void set( T t )
    {
        if (!listSemantics) threadAccessController.checkWriteAccess();
        underlying.set( t );
    }

    public void add( T t )
    {
        threadAccessController.checkWriteAccess();
        underlying.add( t );
    }
}
