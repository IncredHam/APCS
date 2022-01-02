//Zoe Winston
//November 22, 2020

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode current = head;
      for(int i = 0; i < index; i++)
         current = current.getNext();
      DLNode next = new DLNode(obj, current, current.getNext());
      current.getNext().setPrev(next);
      current.setNext(next);
      size++;
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode current = head;
      for(int i = 0; i <= index; i++)
         current = current.getNext();
      return current.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode current = head;
      for(int i = 0; i <= index; i++)
         current = current.getNext();
      Object old = current.getValue();
      current.setValue(obj);
      return old;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode current = head;
      for(int i = 0; i <= index; i++)
         current = current.getNext();
      Object old = current.getValue();
      current.getNext().setPrev(current.getPrev());
      current.getPrev().setNext(current.getNext());
      size--;
      return old;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      DLNode d = new DLNode(obj, head, head.getNext());
      head.getNext().setPrev(d);
      head.setNext(d);
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode n = new DLNode(obj, head.getPrev(), head);
      head.setPrev(n);
      n.getPrev().setNext(n);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      DLNode d = head.getNext().getNext();
      Object removed = head.getNext().getValue();
      d.setPrev(head);
      head.setNext(d);
      size--;
      return removed;
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      DLNode current = head.getPrev().getPrev();
      Object obj = head.getPrev().getValue();
      head.setPrev(current);
      current.setNext(head);
      size--;
      return obj;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String s = "[";
      DLNode current = head.getNext();
      for(int i = 1; i < size; i++)
      {
         s += current.getValue() + ", ";
         current = current.getNext();
      }
      return s + current.getValue() + "]";
   }
}