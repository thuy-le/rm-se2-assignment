/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Team Poseidon
 */
public class ReadOnlySet<E> implements Set<E> , Serializable {

    private Set<E> set;

    public ReadOnlySet(Set<E> set) {
        this.set = set;

    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new ReadOnlyIterator<E>(set.iterator());
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return set.equals(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
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
