package gb.array;

public class LinkedNode<T> {

    T value;
    LinkedNode next;
    LinkedNode prev;

    public LinkedNode(T value) {
        this.value = value;
    }

    public void setPrev(LinkedNode<T> node) {
        this.prev = node;
    }

    public LinkedNode<T> getPrev() {
        return this.prev;
    }

    public void setNext(LinkedNode<T> node) {
        this.next = node;
    }

    public LinkedNode<T> getNext() {
        return this.next;
    }
}
