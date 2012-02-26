package org.rosenvold.concurrency.explore;

import java.util.Map;

/**
 * Basically the same as "contract" since there is no difference for a hashmap.
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticHashMap<T,V> extends org.rosenvold.concurrency.contract.DiagnosticHashMap<T,V> {

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
}
 