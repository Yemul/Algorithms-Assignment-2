import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Permutation {
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> aQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
        {
            aQueue.enqueue(StdIn.readString());
        }
        while (n > 0)
        {
            StdOut.println(aQueue.dequeue());
            n --;
        }

    }

}
