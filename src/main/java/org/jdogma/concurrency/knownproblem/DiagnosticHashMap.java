package org.jdogma.concurrency.knownproblem;

import org.jdogma.concurrency.ThreadAccessController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticHashMap<T,V> extends HashMap<T,V> {


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