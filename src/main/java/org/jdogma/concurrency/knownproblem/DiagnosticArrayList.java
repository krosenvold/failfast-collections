package org.jdogma.concurrency.knownproblem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Just used to defer a real proble, until later ;)
 *
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticArrayList<T>
    extends ArrayList<T>
{
    private final Object lock = new Object();

    public DiagnosticArrayList( int initialCapacity )
    {
        super( initialCapacity );
    }

    public DiagnosticArrayList()
    {
    }

    public DiagnosticArrayList( Collection<? extends T> c )
    {
        super( c );
    }

    @Override
    public void trimToSize()
    {
        synchronized ( lock )
        {
            super.trimToSize();
        }
    }

    @Override
    public void ensureCapacity( int minCapacity )
    {
        synchronized ( lock )
        {
            super.ensureCapacity( minCapacity );
        }
    }

    @Override
    public boolean add( T t )
    {
        synchronized ( lock )
        {

            return super.add( t );
        }
    }

    @Override
    public void add( int index, T element )
    {
        synchronized ( lock )
        {

            super.add( index, element );
        }
    }

    @Override
    public T remove( int index )
    {
        synchronized ( lock )
        {
            return super.remove( index );
        }
    }

    @Override
    public boolean remove( Object o )
    {
        synchronized ( lock )
        {

            return super.remove( o );
        }
    }

    @Override
    public void clear()
    {
        synchronized ( lock )
        {

            super.clear();
        }
    }

    @Override
    public boolean addAll( Collection<? extends T> c )
    {
        synchronized ( lock )
        {

            return super.addAll( c );
        }
    }

    @Override
    public boolean addAll( int index, Collection<? extends T> c )
    {
        synchronized ( lock )
        {

            return super.addAll( index, c );
        }
    }

    @Override
    protected void removeRange( int fromIndex, int toIndex )
    {
        synchronized ( lock )
        {

            super.removeRange( fromIndex, toIndex );
        }
    }
}