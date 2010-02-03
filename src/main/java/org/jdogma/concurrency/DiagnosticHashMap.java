package org.jdogma.concurrency;

import java.util.*;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticHashMap<T,V> extends HashMap<T,V> {
    private final HashMap<T,V> innerMap;
    private final ThreadAccessController threadAccessController;


    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap(int initialCapacity, float loadFactor) {
        innerMap = new HashMap<T,V>(initialCapacity, loadFactor);
        threadAccessController = new ThreadAccessController();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap(int initialCapacity) {
        innerMap = new HashMap<T,V>( initialCapacity);
        threadAccessController = new ThreadAccessController();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap() {
        innerMap = new HashMap<T,V>( );
        threadAccessController = new ThreadAccessController();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap(Map<? extends T, ? extends V> m) {
        innerMap = new HashMap<T,V>( m);
        threadAccessController = new ThreadAccessController();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private DiagnosticHashMap(DiagnosticHashMap<T,V> other) {
        this.innerMap = new HashMap<T,V>( other.innerMap);
        threadAccessController = new ThreadAccessController();
    }

    public int size() {
        threadAccessController.checkThreadAccess();
        return innerMap.size();
    }

    public boolean isEmpty() {
        threadAccessController.checkThreadAccess();
        return innerMap.isEmpty();
    }

    public V get(Object key) {
        threadAccessController.checkThreadAccess();
        return innerMap.get(key);
    }

    public boolean containsKey(Object key) {
        threadAccessController.checkThreadAccess();
        return innerMap.containsKey(key);
    }

    public V put(T key, V value) {
        threadAccessController.checkThreadAccess();
        return innerMap.put(key, value);
    }

    public void putAll(Map<? extends T, ? extends V> m) {
        threadAccessController.checkThreadAccess();
        innerMap.putAll(m);
    }

    public V remove(Object key) {
        threadAccessController.checkThreadAccess();
        return innerMap.remove(key);
    }

    public void clear() {
        threadAccessController.checkThreadAccess();
        innerMap.clear();
    }

    public boolean containsValue(Object value) {
        threadAccessController.checkThreadAccess();
        return innerMap.containsValue(value);
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    public Object clone() {
        threadAccessController.checkThreadAccess();
        return new DiagnosticHashMap<T,V>( this);
    }

    public Set<T> keySet() {
        threadAccessController.checkThreadAccess();
        return innerMap.keySet();
    }

    public Collection<V> values() {
        threadAccessController.checkThreadAccess();
        return innerMap.values();
    }

    public Set<Map.Entry<T, V>> entrySet() {
        threadAccessController.checkThreadAccess();
        return innerMap.entrySet();
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object o) {
        threadAccessController.checkThreadAccess();
        return innerMap.equals(o);
    }

    public int hashCode() {
        threadAccessController.checkThreadAccess();
        return innerMap.hashCode();
    }

    public String toString() {
        threadAccessController.checkThreadAccess();
        return innerMap.toString();
    }
}