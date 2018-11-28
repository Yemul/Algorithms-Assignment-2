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
        for (int i = 0; i < count; i ++)
        {
            NewArray[i] = A[i];
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


    }

    public void addLast(Item item)
    {

    }

    public Item removeFirst()
    {

    }

    public Item removeLast()
    {

    }

    public Iterator<Item> iterator()
    {

    }

    public static void main(String[] args)
    {

    }
}
