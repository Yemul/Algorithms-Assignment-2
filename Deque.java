import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private Item[] A = (Item[]) new Object[1];
    private int count;
    private int Front;
    private int End;

    public Deque()
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

    public void addFirst(Item item)
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
            if (Front - 1 == -1)
            {
                Front = A.length - 1;
            }
            else
            {
                Front --;
            }
            A[Front] = item;
            count ++;
        }
        if (count == A.length)
        {
            resize(A.length * 2);
        }


    }

    public void addLast(Item item)
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

    public Item removeFirst()
    {
        Item val;
        if (count == 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty");
        }
        if (count == 1)
        {
            val = A[Front];
            Front = 0;
            End = 0;
            count --;
        }
        else
        {
            val = A[Front];
            A[Front] = null;
            Front = (Front + 1) % count;
            count --;
        }
        if (count > 0 && count <= A.length/4)
        {
            resize(A.length/2);
        }
        return val;

    }

    public Item removeLast()
    {
        Item val;
        if (count == 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty");
        }
        if (count == 1)
        {
            val = A[Front];
            Front = 0;
            End = 0;
            count --;
        }
        else
        {
            val = A[End];
            A[End] = null;
            if (End - 1 == -1)
            {
                End = A.length - 1;
            }
            else
            {
                End --;
            }
            count --;
        }
        if (count > 0 && count <= A.length/4)
        {
            resize(A.length/2);
        }
        return val;
    }

    public Iterator<Item> iterator()
    {
        return new ArrayIterator();

    }

    private class ArrayIterator implements Iterator<Item>
    {
        private int current = Front;
        private int ItemRemained = count;

        public boolean hasNext()
        {
            return (ItemRemained == 0);
        }

        public void remove()
        {
            throw new java.lang.UnsupportedOperationException("This has not allowed.");
        }

        public Item next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException("No more elements in the queue");
            }
            Item val;
            val = A[current];
            current = (current + 1) % A.length;
            return val;
        }
    }

    public static void main(String[] args)
    {


    }
}
