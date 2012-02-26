package org.rosenvold.concurrency.knownproblem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Kristian Rosenvold
 */
public class DiagnosticHashSet<T>
    extends HashSet<T>
{
    private final Object lock = new Object();

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashSet()
    {
        super();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashSet( Collection<? extends T> c )
    {
        super( c );
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashSet( int initialCapacity, float loadFactor )
    {
        super( initialCapacity, loadFactor );
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashSet( int initialCapacity )
    {
        super( initialCapacity );
    }

    @Override
    public Iterator<T> iterator()
    {
        synchronized ( lock )
        {
            return super.iterator();
        }
    }

    @Override
    public int size()
    {
        synchronized ( lock )
        {
            return super.size();
        }
    }

    @Override
    public boolean isEmpty()
    {
        synchronized ( lock )
        {
            return super.isEmpty();
        }
    }

    @Override
    public boolean contains( Object o )
    {
        synchronized ( lock )
        {
            return super.contains( o );
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
    public Object clone()
    {
        synchronized ( lock )
        {
            return super.clone();
        }
    }

    @Override
    public boolean equals( Object o )
    {
        synchronized ( lock )
        {
            return super.equals( o );
        }
    }

    @Override
    public int hashCode()
    {
        synchronized ( lock )
        {
            return super.hashCode();
        }
    }

    @Override
    public boolean removeAll( Collection<?> c )
    {
        synchronized ( lock )
        {
            return super.removeAll( c );
        }
    }

    @Override
    public Object[] toArray()
    {
        synchronized ( lock )
        {
            return super.toArray();
        }
    }

    @Override
    public <T> T[] toArray( T[] a )
    {
        synchronized ( lock )
        {
            return super.toArray( a );
        }
    }

    @Override
    public boolean containsAll( Collection<?> c )
    {
        synchronized ( lock )
        {
            return super.containsAll( c );
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
    public boolean retainAll( Collection<?> c )
    {
        synchronized ( lock )
        {
            return super.retainAll( c );
        }
    }

    @Override
    public String toString()
    {
        synchronized ( lock )
        {
            return super.toString();
        }
    }
}