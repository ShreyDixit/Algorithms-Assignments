<body>

<h2>Programming Assignment 2: Deques and Randomized Queues</h2>

<b>Dequeue.</b>
A <em>double-ended queue</em> or <em>deque</em> (pronounced “deck”)
is a generalization of a 
stack and a queue that supports adding and removing items 
from either the front or the back of the data structure.
<code>Deque</code> Implements the following API:

<blockquote>
<pre>
<b>public class Deque&lt;Item&gt; implements Iterable&lt;Item&gt; {</b>
<b>   public Deque()                           </b><font color = gray>// construct an empty deque</font>
<b>   public boolean isEmpty()                 </b><font color = gray>// is the deque empty?</font>
<b>   public int size()                        </b><font color = gray>// return the number of items on the deque</font>
<b>   public void addFirst(Item item)          </b><font color = gray>// add the item to the front</font>
<b>   public void addLast(Item item)           </b><font color = gray>// add the item to the end</font>
<b>   public Item removeFirst()                </b><font color = gray>// remove and return the item from the front</font>
<b>   public Item removeLast()                 </b><font color = gray>// remove and return the item from the end</font>
<b>   public Iterator&lt;Item&gt; iterator()         </b><font color = gray>// return an iterator over items in order from front to end</font>
<b>   public static void main(String[] args)   </b><font color = gray>// unit testing (optional)</font>
<b>}</b>
</pre>
</blockquote>

<p><em>Corner cases.&nbsp;</em>
Throws the specified exception for the following corner cases:
<ul>
<li>Throws a <code>java.lang.IllegalArgumentException</code>
if the client calls either <code>addFirst()</code> or <code>addLast()</code>
with a <code>null</code> argument.
<li>
Throws a <code>java.util.NoSuchElementException</code>
if the client calls either <code>removeFirst()</code> or <code>removeLast</code>
when the deque is empty.
<li>
Throws a <code>java.util.NoSuchElementException</code> if the client calls the <code>next()</code>
method in the iterator when there are no more items to return.
<li>
Throws a <code>java.lang.UnsupportedOperationException</code> if the client calls
the <code>remove()</code> method in the iterator.
</ul>

<p><em>Performance Spesifications.&nbsp;</em>
My deque implementation supports each deque operation (including construction)
in <em>constant worst-case time</em>.
A deque containing <em>n</em> items uses at most 48<em>n</em> + 192 bytes of memory
and use space proportional to the number of items <em>currently</em> in 
the deque.
Additionally, my iterator implementation supports
each operation (including construction) in <em>constant worst-case time</em>.

<p><b>Randomized queue.</b>
A <em>randomized queue</em> is similar to a stack or queue, except that
the item removed is chosen uniformly at random from items in
the data structure.
<code>RandomizedQueue</code> implements the following API:

<blockquote>
<pre>
<b>public class RandomizedQueue&lt;Item&gt; implements Iterable&lt;Item&gt; {</b>
<b>   public RandomizedQueue()                 </b><font color = gray>// construct an empty randomized queue</font>
<b>   public boolean isEmpty()                 </b><font color = gray>// is the randomized queue empty?</font>
<b>   public int size()                        </b><font color = gray>// return the number of items on the randomized queue</font>
<b>   public void enqueue(Item item)           </b><font color = gray>// add the item</font>
<b>   public Item dequeue()                    </b><font color = gray>// remove and return a random item</font>
<b>   public Item sample()                     </b><font color = gray>// return a random item (but do not remove it)</font>
<b>   public Iterator&lt;Item&gt; iterator()         </b><font color = gray>// return an independent iterator over items in random order</font>
<b>   public static void main(String[] args)   </b><font color = gray>// unit testing (optional)</font>
<b>}</b>
</pre>
</blockquote>

<p><em>Iterator</em>.&nbsp;
Each iterator returns the items in uniformly random order.
The order of two or more iterators to the same randomized queue is
<em>mutually independent</em>; each iterator maintains its own random order.

<p><em>Corner cases.&nbsp;</em>
Throws the specified exception for the following corner cases:
<ul>
<li>
Throws a <code>java.lang.IllegalArgumentException</code> if the client calls
<code>enqueue()</code> with a <code>null</code> argument.
<li>
Throws a <code>java.util.NoSuchElementException</code>
if the client calls either <code>sample()</code> or 
<code>dequeue()</code> when the randomized queue is empty.
<li>
Throws a <code>java.util.NoSuchElementException</code> if the client calls the <code>next()</code>
method in the iterator when there are no more items to return.
<li>
Throws a <code>java.lang.UnsupportedOperationException</code> if the client calls
the <code>remove()</code> method in the iterator.
</ul>

<p><em>Performance Specifications.&nbsp;</em>
My randomized queue implementation supports each randomized queue operation   
(besides creating an iterator) in <em>constant amortized time</em>.
That is, any sequence of <em>m</em> randomized queue operations (starting from
an empty queue) must take at most <em>cm</em> steps in the worst case,
for some constant <em>c</em>.
Randomized queue containing <em>n</em> items uses at most
48<em>n</em> + 192 bytes of memory.
Additionally, my iterator implementation supports
operations <code>next()</code> and <code>hasNext()</code> in <em>constant worst-case time</em>;
and construction in <em>linear time</em>


<p>
<b>Client.</b>
I have also written a client program <code>Permutation.java</code> that takes an integer <em>k</em> as
a command-line argument; reads in a sequence of strings from standard input
using <code>StdIn.readString()</code>; and prints exactly <em>k</em> of them,
uniformly at random.

<blockquote>
<pre>
% <b>more <a href = "../testing/queues/distinct.txt">distinct.txt</a></b>                        % <b>more <a href = "../testing/queues/duplicates.txt">duplicates.txt</a></b>
A B C D E F G H I                          AA BB BB BB BB BB CC CC

% <b>java-algs4 Permutation 3 < distinct.txt</b>   % <b>java-algs4 Permutation 8 < duplicates.txt</b>
C                                               BB
G                                               AA
A                                               BB
                                                CC
% <b>java-algs4 Permutation 3 < distinct.txt</b>       BB
E                                               BB
F                                               CC
G                                               BB
</pre>
</blockquote>


<ADDRESS><SMALL>
This assignment was developed by Kevin Wayne.
<br>Copyright &copy; 2005.
</SMALL>
</ADDRESS>

</BODY>
</HTML>
