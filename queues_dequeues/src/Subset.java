public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (String s: StdIn.readAllStrings()) {
            rq.enqueue(s);
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(rq.dequeue());
        }
    }
}