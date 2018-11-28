import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] A = (Item[]) new Object[1];
    private int count;
    private int Front;
    private int End;

    public RandomizedQueue()
    {
        count = 0;
        Front = 0;
        End = 0;
    }

    private void resize(int max)
    {
        Item[] NewArray = (Item[]) new Object[max];
        int item_count = count;
        int current_index = Front;
        int i = 0;
        while (item_count > 0)
        {
            NewArray[i] = A[current_index];
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

    }


    public Item dequeue()
    {
        if (count <= 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty");
        }
        Item val;
        if (count == 1)
        {
            val = A[Front];
            Front = 0;
            End = 0;
            count = 0;
        }
        else
        {
            int maxIncrement = count;
            int randomNum = StdRandom.uniform(0, maxIncrement);
            val = A[(Front + randomNum) % A.length];
            Item temp = A[(Front + count - 1) % A.length];
            A[(Front + randomNum) % A.length] = temp;
            A[(Front + count - 1) % A.length] = null;
            count --;
        }
        if (count > 0 && count <= A.length/4)
        {
            resize(A.length/2);
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
        if (count == 1)
        {
            val = A[Front];
        }
        else
        {
            int maxIncrement = count;
            int randomNum = StdRandom.uniform(0, maxIncrement);
            val = A[(Front + randomNum) % A.length];
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
                int maxIncrement = count;
                int A_index;
                for (int i = 0; i < maxIncrement; i++)
                {
                    A_index = (Front + i) % count;
                    if (A[A_index] != null)
                    {
                        IterArray[i] = A[A_index];
                    }
                }
                Initialised = true;
                StdRandom.shuffle(IterArray);
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
                int maxIncrement = count;
                int A_index;
                for (int i = 0; i < maxIncrement; i++)
                {
                    A_index = (Front + i) % count;
                    if (A[A_index] != null)
                    {
                        IterArray[i] = A[A_index];
                    }
                }
                Initialised = true;
                StdRandom.shuffle(IterArray);
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


    }

}
