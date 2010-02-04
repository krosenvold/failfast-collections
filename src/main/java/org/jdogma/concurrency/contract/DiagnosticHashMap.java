package org.jdogma.concurrency.contract;

import org.jdogma.concurrency.accesscontrollers.ThreadAccessControllerImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class DiagnosticHashMap<T,V> extends HashMap<T,V> {
    private final HashMap<T,V> innerMap;
    private final ThreadAccessControllerImpl threadAccessController;


    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap(int initialCapacity, float loadFactor) {
        innerMap = new HashMap<T,V>(initialCapacity, loadFactor);
        threadAccessController = new ThreadAccessControllerImpl();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap(int initialCapacity) {
        innerMap = new HashMap<T,V>( initialCapacity);
        threadAccessController = new ThreadAccessControllerImpl();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap() {
        innerMap = new HashMap<T,V>( );
        threadAccessController = new ThreadAccessControllerImpl();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public DiagnosticHashMap(Map<? extends T, ? extends V> m) {
        innerMap = new HashMap<T,V>( m);
        threadAccessController = new ThreadAccessControllerImpl();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private DiagnosticHashMap(DiagnosticHashMap<T,V> other) {
        this.innerMap = new HashMap<T,V>( other.innerMap);
        threadAccessController = new ThreadAccessControllerImpl();
    }

    public int size() {
        return innerMap.size();
    }

    public boolean isEmpty() {
        return innerMap.isEmpty();
    }

    public V get(Object key) {
        return innerMap.get(key);
    }

    public boolean containsKey(Object key) {
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
        return innerMap.containsValue(value);
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    public Object clone() {
        return new DiagnosticHashMap<T,V>( this);
    }

    public Set<T> keySet() {
        return innerMap.keySet();
    }

    public Collection<V> values() {
        return innerMap.values();
    }

    public Set<Map.Entry<T, V>> entrySet() {
        return innerMap.entrySet();
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object o) {
        return innerMap.equals(o);
    }

    public int hashCode() {
        return innerMap.hashCode();
    }

    public String toString() {
        return innerMap.toString();
    }
}