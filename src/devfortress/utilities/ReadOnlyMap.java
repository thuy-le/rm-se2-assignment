/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Team Poseidon
 */
public class ReadOnlyMap<K, V> implements Map<K, V>, Serializable {

    private Map<K, V> map;
    private ReadOnlySet<K> keySet;
    private ReadOnlyCollection<V> values;

    public ReadOnlyMap(Map m_map) {
        this.map = m_map;
        this.keySet = new ReadOnlySet<K>(map.keySet());
        this.values = new ReadOnlyCollection<V>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Collection<V> values() {
        return values;

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new ReadOnlySet<Entry<K, V>>(map.entrySet());
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Restricted Operation");
    }
}

class ReadOnlyCollection<E> implements Collection<E>, Serializable {

    private Collection<E> collection;

    public ReadOnlyCollection(Collection<E> collection) {
        this.collection = collection;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new ReadOnlyIterator<E>(collection.iterator());
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Restricted Operation");
    }
}
