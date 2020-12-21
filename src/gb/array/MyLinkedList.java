package gb.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    int size = 0;

    LinkedNode<T> first;
    LinkedNode<T> last;

    protected LinkedNode<T> createNode(T value) {
        return new LinkedNode<T>(value);
    }

    public Iterator<T> iterator() {
        return new Iter();
    }

    public int indexOf(T value) {
        return index(value);
    }

    public void insertFirst(T value) {
        LinkedNode<T> newNode = createNode(value);
        newNode.next = first;
        if (isEmpty()) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    public void insertLast(T value) {
        LinkedNode<T> newNode = createNode(value);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }


    public void insert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            insertFirst(value);
            return;
        }
        if (index == size) {
            insertLast(value);
            return;
        }

        LinkedNode current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        LinkedNode newNode = new LinkedNode(value);
        newNode.next = current.next;
        newNode.prev = current;
        current.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        }
        if (first.value.equals(value)) {
            removeFirst();
            return true;
        }
        LinkedNode current = first;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == last) {
            removeLast();
            return true;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return true;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        LinkedNode<T> node = first;
        first = first.next;
        size--;
        return node.value;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        LinkedNode<T> oldLast = last;
        if (last.prev != null) {
            last.prev.next = null;
        } else {
            first = null;
        }
        last = last.prev;
        size--;
        return oldLast.value;
    }

    private int index(T value) {
        LinkedNode current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public T getFirst() {
        return (T) first.value;
    }

    public boolean contains(T value) {
        return index(value) > -1;
    }

    public LinkedNode<T> getFirstNode() {
        return first;
    }

    public T getLast() {
        return (T) last.value;
    }

    public boolean isEmpty() {
        return getFirstNode() == null;
    }

    public int size() {
        return size;
    }

    private class Iter implements Iterator<T> {
        LinkedNode current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return (T) current.value;
        }
    }

    public String toString() {

        if (this.isEmpty()) {
            return "[]";
        }

        ArrayList<String> strs = new ArrayList<>();
        LinkedNode<T> current = getFirstNode();
        do {
            strs.add(current.value.toString());
            current = current.getNext();


        } while (current != null);

        return "[ " + String.join(", ", strs) + " ] ";
    }

}