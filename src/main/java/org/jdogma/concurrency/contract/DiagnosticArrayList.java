package org.jdogma.concurrency.contract;

import org.jdogma.concurrency.DiagnosticIterator;
import org.jdogma.concurrency.DiagnosticListIterator;
import org.jdogma.concurrency.ThreadAccessController;

import java.util.*;

/**
 * An array list that enforces the concurrency contract of ArrayList
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticArrayList<T> extends ArrayList<T> {
    private final ThreadAccessController threadAccessController;

    public DiagnosticArrayList(int initialCapacity) {
        super();
        threadAccessController = new ThreadAccessController();
    }

    public DiagnosticArrayList() {
        threadAccessController = new ThreadAccessController();
    }

    public DiagnosticArrayList(Collection<? extends T> c) {
        threadAccessController = new ThreadAccessController();
    }


    private DiagnosticArrayList( DiagnosticArrayList<T> diagnosticArrayList){
        threadAccessController = new ThreadAccessController();
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
        super.ensureCapacity(
            minCapacity );    
    }

    @Override
    public int size()
    {
        return super.size();    
    }

    @Override
    public boolean isEmpty()
    {
        return super.isEmpty();    
    }

    @Override
    public boolean contains( Object o )
    {
        return super.contains( o );    
    }

    @Override
    public int indexOf( Object o )
    {
        return super.indexOf( o );    
    }

    @Override
    public int lastIndexOf( Object o )
    {
        return super.lastIndexOf( o );    
    }

    @Override
    public Object clone()
    {
        return super.clone();    
    }

    @Override
    public Object[] toArray()
    {
        return super.toArray();    
    }

    @Override
    public <T> T[] toArray( T[] a )
    {
        return super.toArray( a );    
    }

    @Override
    public T get( int index )
    {
        return super.get( index );    
    }

    @Override
    public T set( int index, T element )
    {
        return super.set( index,
                          element );    
    }

    @Override
    public boolean add( T t )
    {
        threadAccessController.checkWriteAccess();
        return super.add( t );
    }

    @Override
    public void add( int index, T element )
    {
        threadAccessController.checkWriteAccess();
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
    public List<T> subList( int fromIndex, int toIndex )
    {
        return super.subList( fromIndex,
                              toIndex );    
    }

    @Override
    public boolean equals( Object o )
    {
        return super.equals( o );    
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();    
    }

    @Override
    public boolean containsAll( Collection<?> c )
    {
        return super.containsAll( c );    
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

    @Override
    public String toString()
    {
        return super.toString();    
    }
}