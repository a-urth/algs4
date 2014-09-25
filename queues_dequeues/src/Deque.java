import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public Deque() { };

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> newHead = new Node<Item>(item);
        if (this.size == 0) {
            this.tail = newHead;
            this.head = newHead;
        } else {
            this.head.setPrevious(newHead);
            newHead.setNext(this.head);
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> newTail = new Node<Item>(item);
        if (this.size == 0) {
            this.tail = newTail;
            this.head = newTail;
        } else {
            newTail.setPrevious(this.tail);
            this.tail.setNext(newTail);
            this.tail = newTail;
        }
        this.size++;
    }

    public Item removeFirst() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        }
        this.size--;
        Node<Item> tHead = this.head;
        this.head = this.head.getNext();
        if (this.head != null) {
            this.head.setPrevious(null);
        }
        return tHead.item;
    }

    public Item removeLast() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        }
        this.size--;
        Node<Item> tTail = this.tail;
        this.tail = this.tail.getPrevious();
        if (this.tail != null) {
            this.tail.setNext(null);
        }
        return tTail.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node<Item> {
        private Node<Item> next;
        private Node<Item> previous;
        private Item item;

        public Node(Item item) {
            this.item = item;
        }

        public Node<Item> getNext() {
            return this.next;
        }

        public Node<Item> getPrevious() {
            return this.previous;
        }

        public Item getItem() {
            return this.item;
        }

        public void setNext(Node<Item> inNext) {
            this.next = inNext;
        }

        public void setPrevious(Node<Item> inPrevious) {
            this.previous = inPrevious;
        }

        public void setItem(Item inItem) {
            this.item = inItem;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = head;

        public boolean hasNext() {
            return this.current != null;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = this.current.getItem();
            this.current = this.current.getNext();
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        d.addFirst("one");
        d.addFirst("one");
        d.addFirst("one");
        // System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        d.addFirst("three");
        d.addFirst("three");
        d.addFirst("three");
        d.addFirst("three");
        d.addFirst("three");
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
    }
}
