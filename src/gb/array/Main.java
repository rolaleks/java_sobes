package gb.array;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        myLinkedList.insertFirst("test1");
        myLinkedList.insertFirst("test2");
        myLinkedList.insertLast("test3");
        myLinkedList.insert(1,"test4");
        System.out.println(myLinkedList);
        myLinkedList.removeLast();
        System.out.println(myLinkedList);


        MyArrayList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("test1");
        myArrayList.add("test2");
        myArrayList.print();
        myArrayList.remove(0);
        myArrayList.print();
    }
}
