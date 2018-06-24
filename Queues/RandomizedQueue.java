import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	int size;

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			copy[i] = queue[i];
		queue = copy;
	}

	public RandomizedQueue() // construct an empty randomized queue
	{
		queue = (Item[]) new Object[2];
		size = 0;
	}

	public boolean isEmpty() // is the randomized queue empty?
	{
		return size == 0;
	}

	public int size() // return the number of items on the randomized queue
	{
		return size;
	}

	public void enqueue(Item item) // add the item
	{
		if (item == null)
			throw new java.lang.IllegalArgumentException("Can't add null");
		if (size == queue.length)
			resize(size * 2);
		queue[size++] = item;
	}

	public Item dequeue() // remove and return a random item
	{
		if (size == 0)
			throw new java.util.NoSuchElementException("Stack underflow");
		if (size < queue.length / 4)
			resize(queue.length / 2);
		int index = StdRandom.uniform(0, size);
		Item item = queue[index];
		queue[index] = queue[--size];
		queue[size] = null;
		return item;
	}

	public Item sample() // return a random item (but do not remove it)
	{
		if (size == 0)
			throw new java.util.NoSuchElementException("Stack underflow");
		return queue[StdRandom.uniform(0, size)];
	}

	public Iterator<Item> iterator() // return an independent iterator over items in random order
	{
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {
		int n = 0;

		public boolean hasNext() {
			return n<size;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return queue[n++];
		}
	}
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("H");
		queue.enqueue("j");
		queue.enqueue("t");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}

}
