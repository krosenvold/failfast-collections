package org.jdogma.concurrency.explore;

import org.jdogma.concurrency.ThreadAccessController;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Kristian Rosenvold
 */
public class DiagnosticHashSet<T> extends org.jdogma.concurrency.contract.DiagnosticHashSet<T>
{
    public DiagnosticHashSet()
    {
    }

    public DiagnosticHashSet( Collection<? extends T> c )
    {
        super( c );
    }

    public DiagnosticHashSet( int initialCapacity, float loadFactor )
    {
        super( initialCapacity, loadFactor );
    }

    public DiagnosticHashSet( int initialCapacity )
    {
        super( initialCapacity );
    }
}
