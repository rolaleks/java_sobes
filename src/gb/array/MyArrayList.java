package gb.array;

import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    private T[] array;
    private int capacity;
    private int size;

    public MyArrayList() {
        this.capacity = 100;
        this.size = 0;
        this.array = (T[]) new Object[capacity];
    }

    public void add(T value) {
        if (size >= capacity) {
            increaseCapacity();
        }
        this.array[size] = value;
        size++;
    }

    /**
     * @param value
     * @return индекс первого попавшегося элемента если не найден то -1
     */
    public int findElement(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[index];
    }

    public void removeByValue(T value) {
        int index = this.findElement(value);
        if (index >= 0) {
            remove(index);
        }
    }

    public void removeByIndex(int index) {
        if (index >= 0 && index < size) {
            remove(index);
        }
    }

    public void remove(int index) {
        for (int i = index; i < (this.size - 1); i++) {

            this.array[i] = this.array[i + 1];
        }
        size--;
    }

    public void clear() {
        this.size = 0;
    }

    private void increaseCapacity() {
        this.capacity *= 2;
        T[] arr = (T[]) new Object[capacity];
        for (int i = 0; i < this.array.length; i++) {
            arr[i] = this.array[i];
        }
        this.array = arr;
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new MyArrayList.Iter();
    }

    private class Iter implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            index++;
            return get(index);
        }
    }
}
