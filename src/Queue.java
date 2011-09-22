// Queue är en kö med metoderna Put, Get, IsEmpty och Empty.

class ListNode<E>
{
    E element;
    ListNode<E> next = null;
}

class Queue<E>
{
    private ListNode<E> front = null, back = null;

    public void put(E element){ 
		if (isEmpty()) back = front = new ListNode<E>();
		else back = back.next = new ListNode<E>();
		
		back.element = element;
    }

    public E get() { 
		if (isEmpty()) return null;
		E element = front.element;
		front = front.next;
		return element;
    }

    public boolean isEmpty(){
    	return front == null;
    }

    public void empty(){
    	front = back = null;
    }
}
