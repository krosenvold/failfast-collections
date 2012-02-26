package org.rosenvold.concurrency.knownproblem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Just used to defer a real problme until later ;)
 *
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticHashMap<T, V>
    extends HashMap<T, V>
{
    private final Object lock = new Object();


    public DiagnosticHashMap( int initialCapacity, float loadFactor )
    {
        super( initialCapacity, loadFactor );
    }

    public DiagnosticHashMap( int initialCapacity )
    {
        super( initialCapacity );
    }

    public DiagnosticHashMap()
    {
    }

    public DiagnosticHashMap( Map<? extends T, ? extends V> m )
    {
        super( m );
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
    public V get( Object key )
    {
        synchronized ( lock )
        {
            return super.get( key );
        }
    }

    @Override
    public boolean containsKey( Object key )
    {
        synchronized ( lock )
        {
            return super.containsKey( key );
        }
    }

    @Override
    public V put( T key, V value )
    {
        synchronized ( lock )
        {
            return super.put( key, value );
        }
    }

    @Override
    public void putAll( Map<? extends T, ? extends V> m )
    {
        synchronized ( lock )
        {
            super.putAll( m );
        }
    }

    @Override
    public V remove( Object key )
    {
        synchronized ( lock )
        {
            return super.remove( key );
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
    public boolean containsValue( Object value )
    {
        synchronized ( lock )
        {
            return super.containsValue( value );
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
    public Set<T> keySet()
    {
        synchronized ( lock )
        {
            return super.keySet();
        }
    }

    @Override
    public Collection<V> values()
    {
        synchronized ( lock )
        {
            return super.values();
        }
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet()
    {
        synchronized ( lock )
        {
            return super.entrySet();
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
    public String toString()
    {
        synchronized ( lock )
        {
            return super.toString();
        }
    }
}