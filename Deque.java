import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node front;
    private Node End;
    private int count;

    public Deque()
    {
        count = 0;

    }

    private class Node
    {
        Item item;
        Node next;
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
            front = new Node();
            front.item = item;
            End = front;
            count ++;
        }
        else
        {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = front;
            front = newNode;
            count ++;
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
            front = new Node();
            front.item = item;
            End = front;
            count ++;
        }
        else
        {
            Node newNode = new Node();
            newNode.item = item;
            End.next = newNode;
            End = newNode;
        }
    }

    public Item removeFirst()
    {
        if (count <= 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty.");
        }
        if (count == 1)
        {
            Item val = front.item;
            front = new Node();
            End = null;
            front = null;
            return val;
        }
        else
        {
            Item val = front.item;
            front = front.next;
            return val;
        }
    }

    public Item removeLast()
    {
        if (count <= 0)
        {
            throw new java.util.NoSuchElementException("The queue is empty.");
        }
        if (count == 1)
        {
            Item val = front.item;
            front = new Node();
            End = null;
            front = null;
            return val;
        }
        else
        {
            Item val = End.item;

        }
    }

    public Iterator<Item> iterator()
    {

    }

    public static void main(String[] args)
    {

    }
}
