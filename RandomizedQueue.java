import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] A = (Item[]) new Object[1];
    private int count;
    private int Front;
    private int End;
    private int deleted;

    public RandomizedQueue()
    {
        count = 0;
        Front = 0;
        End = 0;
        deleted = 0;
    }

    private void resize(int max)
    {
        Item[] NewArray = (Item[]) new Object[max];
        int item_count = distanceBetweenEnd();
        int current_index = Front;
        int i = 0;
        while (item_count > 0)
        {
            if (A[current_index] != null)
            {
                NewArray[i] = A[current_index];
            }
            item_count --;
            current_index = (current_index + 1) % A.length;
            i ++;
        }
        Front = 0;
        End = count - 1;
        if (End < 0)
        {
            End = 0;
        }
        A = NewArray;
        deleted = 0;
    }

    private int distanceBetweenEnd()
    {
        int distance1 = Front - End;
        int distance2 = Front - End;
        if (distance1 < 0)
        {
            distance1 = -1 * distance1;
        }
        if (distance2 < 0)
        {
            distance2 = -1 * distance2;
        }
        distance2 = A.length - distance2;
        if (distance1 > distance2)
        {
            return distance1;
        }
        return distance2;

    }

    public boolean isEmpty()
    {
        return (count == 0);
    }

    public int size()
    {
        return count;
    }

    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new java.lang.IllegalArgumentException("The input cannot be null.");
        }
        if (count == 0)
        {
            A[Front] = item;
            count ++;
        }
        else
        {
            End = (End + 1) % A.length;
            A[End] = item;
            count ++;
        }
        if (count == A.length)
        {
            resize(A.length * 2);
        }
        else if (deleted >= distanceBetweenEnd() || End == Front)
        {
            resize(A.length);
        }


    }

    public Item dequeue()
    {
        if (count <= 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty");
        }

        Item val;
        int maxIncrement = distanceBetweenEnd();
        int randomNum = StdRandom.uniform(0, maxIncrement+1);
        while (A[randomNum] == null)
        {
            randomNum = StdRandom.uniform(0, maxIncrement+1);
        }
        val = A[randomNum];
        count --;
        deleted ++;
        A[randomNum] = null;
        if (count > 0 && count <= A.length/4)
        {
            resize(A.length/2);
        }
        else if (deleted >= distanceBetweenEnd())
        {
            resize(A.length);
        }
        return val;

    }

    public Item sample()
    {
        if (count <= 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty");
        }

        Item val;
        int maxIncrement = distanceBetweenEnd();
        int randomNum = StdRandom.uniform(0, maxIncrement+1);
        while (A[randomNum] == null)
        {
            randomNum = StdRandom.uniform(0, maxIncrement+1);
        }
        val = A[randomNum];
        if (count > 0 && count <= A.length/4)
        {
            resize(A.length/2);
        }
        else if (deleted >= distanceBetweenEnd())
        {
            resize(A.length);
        }
        return val;

    }

    public Iterator<Item> iterator()
    {
        return new RandomizedArrayIterator();
    }

    private class RandomizedArrayIterator implements Iterator<Item>
    {
        private Item[] IterArray = (Item[]) new Object[count];
        private boolean Initialised = false;
        private int current_index = 0;

        public boolean hasNext()
        {
            if (count == 0 || current_index >= IterArray.length)
            {
                return false;
            }
            if (!Initialised)
            {
                int maxIncrement = distanceBetweenEnd();
                int A_index;
                for (int i = 0; i <= maxIncrement; i++)
                {
                    A_index = (Front + i) % count;
                    if (A[A_index] != null)
                    {
                        IterArray[i] = A[A_index];
                    }
                }
            }
            return true;
        }

        public Item next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException("The end has been reached.");
            }
            if (!Initialised)
            {
                int maxIncrement = distanceBetweenEnd();
                int A_index;
                for (int i = 0; i <= maxIncrement; i++)
                {
                    A_index = (Front + i) % count;
                    if (A[A_index] != null)
                    {
                        IterArray[i] = A[A_index];
                    }
                }
            }
            Item val = IterArray[current_index];
            current_index ++;
            return val;


        }

        public void remove()
        {
            throw new java.lang.UnsupportedOperationException("remove is not supported.");
        }
    }

    public static void main(String[] args)
    {
        RandomizedQueue a = new RandomizedQueue();
        a.enqueue(3);
        a.enqueue(4);
        a.enqueue(5);
        a.enqueue(66);
        a.enqueue(7);
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        for (int i = 0; i < a.A.length; i ++)
        {
            System.out.println(a.A[i]);
        }

    }

}
