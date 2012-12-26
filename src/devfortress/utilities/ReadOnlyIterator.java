/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Team Poseidon
 */
class ReadOnlyIterator<E> implements Iterator<E>, Serializable {

    private Iterator<E> itr;

    public ReadOnlyIterator(Iterator<E> itr) {
        this.itr = itr;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Restricted Operation");
    }

    @Override
    public E next() {
        return itr.next();
    }

    @Override
    public boolean hasNext() {
        return itr.hasNext();
    }
}
