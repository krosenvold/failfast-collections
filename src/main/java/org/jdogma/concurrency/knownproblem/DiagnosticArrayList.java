package org.jdogma.concurrency.knownproblem;

import org.jdogma.concurrency.ThreadAccessController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticArrayList<T> extends ArrayList<T> {

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