package org.rosenvold.concurrency.strictpublication;

import org.rosenvold.concurrency.DiagnosticIterator;
import org.rosenvold.concurrency.DiagnosticListIterator;
import org.rosenvold.concurrency.accesscontrollers.PublisherWriteOnlyThreadAccessController;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An access controller that allows the object creator infinite write access, but only until another thread
 * hits the object. After a second thread is seen, it enforces immutability .
 *
 * Please note there's no method to signal the start of the happens-before relationship, so it's still
 * somewhat of an approximation. (Threads could be started and original thread could do writes after starting
 * the threads but before new threads hit this instance)
 * 
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticArrayList<T> extends ArrayList<T> {
    private final PublisherWriteOnlyThreadAccessController threadAccessController;

    private static final List<SoftReference<DiagnosticArrayList>> softReferences = Collections.synchronizedList(new ArrayList<SoftReference<DiagnosticArrayList>>());
    public static volatile boolean before = true;

    public DiagnosticArrayList(int initialCapacity) {
        super();
        threadAccessController = new PublisherWriteOnlyThreadAccessController();
        addSoftReference();
    }

    private void addSoftReference()
    {
        if (before) softReferences.add( new SoftReference<DiagnosticArrayList>(this)); // Oooh. A nice escape of partially constructed object
    }

    public DiagnosticArrayList() {
        super();
        threadAccessController = new PublisherWriteOnlyThreadAccessController();
        addSoftReference();
    }

    public DiagnosticArrayList( Collection<? extends T> c)
    {
        super( c );
        this.threadAccessController = new PublisherWriteOnlyThreadAccessController();
        addSoftReference();
    }

    public static void setHappensBefore(){
        if (!before) throw new IllegalStateException( "setHappensBefore can only be called once");
        before = false;
        for ( SoftReference<DiagnosticArrayList> softReference : softReferences )
        {
            final DiagnosticArrayList arrayList = softReference.get();
            if (arrayList != null){
                arrayList.threadAccessController.setHappensBefore();
            }
        }
        softReferences.clear();
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
        super.ensureCapacity( minCapacity );
    }

    @Override
    public boolean add( T t )
    {
        if (threadAccessController != null)  threadAccessController.checkWriteAccess();
        return super.add( t );
    }

    @Override
    public void add( int index, T element )
    {
        if (threadAccessController != null) threadAccessController.checkWriteAccess();
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
        threadAccessController.checkReadAccess();
        return new DiagnosticIterator<T>( threadAccessController, super.iterator());
    }

    @Override
    public ListIterator<T> listIterator()
    {
        threadAccessController.checkReadAccess();
        return new DiagnosticListIterator<T>( threadAccessController, super.listIterator( ), true);
    }

    @Override
    public ListIterator<T> listIterator( int index )
    {
        threadAccessController.checkReadAccess();
        return new DiagnosticListIterator<T>( threadAccessController, super.listIterator(  index), true);
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
    public int size()
    {
        threadAccessController.checkReadAccess();
        return super.size();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEmpty()
    {
        threadAccessController.checkReadAccess();
        return super.isEmpty();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains( Object o )
    {
        threadAccessController.checkReadAccess();
        return super.contains( o );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int indexOf( Object o )
    {
        threadAccessController.checkReadAccess();
        return super.indexOf( o );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int lastIndexOf( Object o )
    {
        threadAccessController.checkReadAccess();
        return super.lastIndexOf( o );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Object clone()
    {
        threadAccessController.checkReadAccess();
        return super.clone();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Object[] toArray()
    {
        return super.toArray();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public <T> T[] toArray( T[] a )
    {
        threadAccessController.checkReadAccess();
        return super.toArray( a );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public T get( int index )
    {
        threadAccessController.checkReadAccess();
        return super.get( index );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public T set( int index, T element )
    {
        threadAccessController.checkReadAccess();
        return super.set( index,
                          element );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<T> subList( int fromIndex, int toIndex )
    {
        threadAccessController.checkReadAccess();
        return super.subList( fromIndex,
                              toIndex );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals( Object o )
    {
        threadAccessController.checkReadAccess();
        return super.equals( o );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int hashCode()
    {
        threadAccessController.checkReadAccess();
        return super.hashCode();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean containsAll( Collection<?> c )
    {
        threadAccessController.checkReadAccess();
        return super.containsAll( c );    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString()
    {
        threadAccessController.checkReadAccess();
        return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
    }
}