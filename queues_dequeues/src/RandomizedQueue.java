import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int next;
    private Object[] rq;

    public RandomizedQueue() {
        this.rq = new Object[10];
    };

    public boolean isEmpty() {
        return this.next == 0;
    }

    public int size() {
        return this.next;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (this.next == this.rq.length) {
            this.increaseSize();
        }
        if (this.next > 0) {
            int i = StdRandom.uniform(0, this.next);
            Item t = (Item) this.rq[i];
            this.rq[i] = (Object) item;
            this.rq[this.next] = t;
        } else {
            this.rq[this.next] = (Object) item;
        }
        this.next++;
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item ret = (Item) this.rq[--this.next];
        this.rq[this.next] = null;
        if (this.next <= this.rq.length/4) {
            this.decreaseSize();
        }
        return ret;
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int i = StdRandom.uniform(0, this.next);
        return (Item) this.rq[i];
    }

    public Iterator<Item> iterator() {
        return new RAIterator();
    }

    private class RAIterator implements Iterator<Item> {
        private int current = next;

        public boolean hasNext() {
            return this.current > 0;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (Item) rq[--this.current];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private void increaseSize() {
        Object[] trq = new Object[this.rq.length*2];
        for (int i = 0; i < this.rq.length; i++) {
            trq[i] = this.rq[i];
        }
        this.rq = trq;
    }

    private void decreaseSize() {
        Object[] trq = new Object[this.rq.length/2 + 1];
        for (int i = 0; i < trq.length; i++) {
            trq[i] = this.rq[i];
        }
        this.rq = trq;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("1");
        System.out.println(rq.dequeue());
        rq.enqueue("2");
        System.out.println(rq.dequeue());
        rq.enqueue("3");
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        rq.enqueue("5");
        System.out.println(rq.dequeue());
    }
}