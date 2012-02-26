package org.rosenvold.concurrency;

import java.util.*;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
 @Deprecated
public class DiagnosticHashMap<T,V> extends org.rosenvold.concurrency.explore.DiagnosticHashMap<T,V> {

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