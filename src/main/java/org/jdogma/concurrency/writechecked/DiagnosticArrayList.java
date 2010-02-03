package org.jdogma.concurrency.writechecked;

import org.jdogma.concurrency.ThreadAccessController;

import java.util.*;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticArrayList<T> extends ArrayList<T> {
    private final ArrayList<T> innerList;
    private final ThreadAccessController threadAccessController;


    public DiagnosticArrayList(int initialCapacity) {
        super();
        innerList = new ArrayList<T>(initialCapacity);
        threadAccessController = new ThreadAccessController();
    }

    public DiagnosticArrayList() {
        innerList = new ArrayList<T>();
        threadAccessController = new ThreadAccessController();
    }

    public DiagnosticArrayList(Collection<? extends T> c) {
        innerList = new ArrayList<T>(c);
        threadAccessController = new ThreadAccessController();
    }


    private DiagnosticArrayList( DiagnosticArrayList<T> diagnosticArrayList){
        this.innerList = new ArrayList<T>(diagnosticArrayList.innerList);
        threadAccessController = new ThreadAccessController();
    }


    public void trimToSize() {
        threadAccessController.checkThreadAccess();
        innerList.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        threadAccessController.checkThreadAccess();
        innerList.ensureCapacity(minCapacity);
    }

    public int size() {
        return innerList.size();
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public boolean contains(Object o) {
        return innerList.contains(o);
    }

    public int indexOf(Object o) {
        return innerList.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return innerList.lastIndexOf(o);
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    public Object clone() {
        return new DiagnosticArrayList<T>(this);
    }

    public Object[] toArray() {
        return innerList.toArray();
    }

    @SuppressWarnings({"SuspiciousToArrayCall"})
    public <T> T[] toArray(T[] a) {
        return innerList.toArray(a);
    }

    public T get(int index) {
        return innerList.get(index);
    }

    public T set(int index, T element) {
        threadAccessController.checkThreadAccess();
        return innerList.set(index, element);
    }

    public boolean add(T t) {
        threadAccessController.checkThreadAccess();
        return innerList.add(t);
    }

    public void add(int index, T element) {
        threadAccessController.checkThreadAccess();
        innerList.add(index, element);
    }

    public T remove(int index) {
        threadAccessController.checkThreadAccess();
        return innerList.remove(index);
    }

    public boolean remove(Object o) {
        threadAccessController.checkThreadAccess();
        return innerList.remove(o);
    }

    public void clear() {
        threadAccessController.checkThreadAccess();
        innerList.clear();
    }

    public boolean addAll(Collection<? extends T> c) {
        threadAccessController.checkThreadAccess();
        return innerList.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        threadAccessController.checkThreadAccess();
        return innerList.addAll(index, c);
    }

    public Iterator<T> iterator() {
        return innerList.iterator();
    }

    public ListIterator<T> listIterator() {
        return innerList.listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return innerList.listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return innerList.subList(fromIndex, toIndex);
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object o) {
        return innerList.equals(o);
    }

    public int hashCode() {
        return innerList.hashCode();
    }

    public boolean containsAll(Collection<?> c) {
        return innerList.containsAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        threadAccessController.checkThreadAccess();
        return innerList.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        threadAccessController.checkThreadAccess();
        return innerList.retainAll(c);
    }

    public String toString() {
        return innerList.toString();
    }
}