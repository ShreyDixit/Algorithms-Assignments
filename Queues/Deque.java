import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first; // top of stack
	private Node<Item> last; // bottom of stack
	private int n; // size of the stack

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
	}

	public Deque() // construct an empty deque
	{
		first = null;
		n = 0;
		last = null;
	}

	public boolean isEmpty() // is the deque empty?
	{
		return n == 0;
	}

	public int size() // return the number of items on the deque
	{
		return n;
	}

	public void addFirst(Item item) // add the item to the front
	{
		if (item == null)
			throw new java.lang.IllegalArgumentException("Can't add null");
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		first.previous = null;
		if(n!=0) oldfirst.previous=first;
		if (n == 0)
			last = first;
		n++;
	}

	public void addLast(Item item) // add the item to the end
	{
		if (item == null)
			throw new java.lang.IllegalArgumentException("Can't add null");
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		last.previous = oldLast;
		if(n!=0) oldLast.next=last;
		if (n == 0)
			first = last;
		n++;
	}

	public Item removeFirst() // remove and return the item from the front
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		Item item = first.item; // save item to return
		if (n == 1) {
			first = null;
			last = null;
		} else {
			first = first.next; // delete first node
			first.previous = null;
		}
		n--;
		return item; // return the saved item
	}

	public Item removeLast() // remove and return the item from the end
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		Item item = last.item;
		if (n == 1) {
			first = null;
			last = null;
		} else {
			last = last.previous;
			last.next = null;
		}
		n--;
		return item;
	}

	public Iterator<Item> iterator() // return an iterator over items in order from front to end
	{
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		         System.out.println(deque.isEmpty()) ;
		         System.out.println(deque.size());
		         deque.addLast(2);
		         System.out.println(deque.size());
		         System.out.println(deque.isEmpty());
		         deque.addFirst(5);
		         System.out.println(deque.size());
		         System.out.println(deque.isEmpty());
		         deque.removeLast();
	}
}
