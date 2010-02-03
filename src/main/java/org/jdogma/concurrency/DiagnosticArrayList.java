package org.jdogma.concurrency;

import java.util.*;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
@Deprecated
public class DiagnosticArrayList<T> extends org.jdogma.concurrency.explore.DiagnosticArrayList<T> {
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
}
