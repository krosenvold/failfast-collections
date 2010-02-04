package org.jdogma.concurrency.contract;

import org.jdogma.concurrency.DiagnosticIterator;
import org.jdogma.concurrency.DiagnosticListIterator;
import org.jdogma.concurrency.accesscontrollers.ThreadAccessController;
import org.jdogma.concurrency.accesscontrollers.ThreadAccessControllerImpl;

import java.util.*;

/**
 * An array list that enforces the concurrency contract of ArrayList
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticArrayList<T> extends ArrayList<T> {
    private final ThreadAccessController threadAccessController;

    public DiagnosticArrayList(int initialCapacity) {
        super();
        threadAccessController = new ThreadAccessControllerImpl();
    }

    public DiagnosticArrayList() {
        super();
        threadAccessController = new ThreadAccessControllerImpl();
    }

    public DiagnosticArrayList( Collection<? extends T> c)
    {
        super( c );
        this.threadAccessController = new ThreadAccessControllerImpl();
    }

    @Override
    public void trimToSize()
    {
        threadAccessController.checkWriteAccess();
        super.trimToSize();    
    }

    @Override
    public void ensureCapacity( int minCapacity )
    {
        threadAccessController.checkWriteAccess();
        super.ensureCapacity( minCapacity );    
    }

    @Override
    public boolean add( T t )
    {
        if (threadAccessController != null)  threadAccessController.checkWriteAccess();
        return super.add( t );
    }

    @Override
    public void add( int index, T element )
    {
        if (threadAccessController != null) threadAccessController.checkWriteAccess();
        super.add( index, element );
    }

    @Override
    public T remove( int index )
    {
        threadAccessController.checkWriteAccess();
        return super.remove( index );
    }

    @Override
    public boolean remove( Object o )
    {
        threadAccessController.checkWriteAccess();
        return super.remove( o );
    }

    @Override
    public void clear()
    {
        threadAccessController.checkWriteAccess();
        super.clear();
    }

    @Override
    public boolean addAll( Collection<? extends T> c )
    {
        threadAccessController.checkWriteAccess();
        return super.addAll( c );
    }

    @Override
    public boolean addAll( int index, Collection<? extends T> c )
    {
        threadAccessController.checkWriteAccess();
        return super.addAll( index, c );
    }

    @Override
    protected void removeRange( int fromIndex, int toIndex )
    {
        threadAccessController.checkWriteAccess();
        super.removeRange( fromIndex,
                           toIndex );    
    }

    @Override
    public Iterator<T> iterator()
    {
        return new DiagnosticIterator<T>( threadAccessController, super.iterator());
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return new DiagnosticListIterator<T>( threadAccessController, super.listIterator( ), true);
    }

    @Override
    public ListIterator<T> listIterator( int index )
    {
        return new DiagnosticListIterator<T>( threadAccessController, super.listIterator(  index), true);
    }


    @Override
    public boolean removeAll( Collection<?> c )
    {
        threadAccessController.checkWriteAccess();
        return super.removeAll( c );
    }

    @Override
    public boolean retainAll( Collection<?> c )
    {
        threadAccessController.checkWriteAccess();
        return super.retainAll( c );    
    }
}