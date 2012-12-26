/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Team Poseidon
 */
public class ReadOnlyList<E> implements List<E>, Serializable {

    private List<E> list;

    public ReadOnlyList(List<E> list) {
        this.list = list;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ReadOnlyListIterator<E>(list.listIterator(index));
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ReadOnlyListIterator<E>(list.listIterator());
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new ReadOnlyIterator<E>(list.iterator());
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean add(E e) {
        list.add(e);
        return true;
    }

    @Override
    public E set(int index, E element) {
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
    public E remove(int index) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Restricted Operation");
    }
}

class ReadOnlyListIterator<E> implements ListIterator<E> {

    private ListIterator<E> itr;

    public ReadOnlyListIterator(ListIterator<E> itr) {
        this.itr = itr;
    }

    @Override
    public int previousIndex() {
        return itr.previousIndex();
    }

    @Override
    public E previous() {
        return itr.previous();
    }

    @Override
    public int nextIndex() {
        return itr.nextIndex();
    }

    @Override
    public E next() {
        return itr.next();
    }

    @Override
    public boolean hasPrevious() {
        return itr.hasPrevious();
    }

    @Override
    public boolean hasNext() {
        return itr.hasNext();
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public void set(E e) {
        throw new UnsupportedOperationException("Restricted Operation");
    }
}
