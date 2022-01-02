//Zoe Winston
//February 28, 2021
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public E add(E element) 
   {
      size++;
      root = add(root, element);
      return element;
   }
   private TreeNode<E> add(TreeNode<E> t, E element) //recursive helper method
   {      
      if(t == null)
         return new TreeNode<E>(element);
      if(element.compareTo(t.getValue()) <= 0)
         t.setLeft(add(t.getLeft(), element));
      else
         t.setRight(add(t.getRight(), element));
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
   {
      // turn your head towards left shoulder visualize tree
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains(E element)
   {
      return contains(root, element);
   }
   private boolean contains(TreeNode<E> t, E element) //recursive helper method
   {
      if(t == null)
         return false;
      if(element.compareTo(t.getValue()) < 0)
         return contains(t.getLeft(), element);
      if(element.compareTo(t.getValue()) > 0)
         return contains(t.getRight(), element);
      return true;
   }
   
   public E min()
   {
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t == null)
         return null;
      while(t.getLeft() != null)
         t = t.getLeft();
      return t.getValue();
   }
   
   public E max()
   {
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t.getValue();
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      toReturn += toString(t.getRight());  //recurse right
      return toReturn;
   }
   
   public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      if(current == null)
         return null;
      int x = (current.getValue()).compareTo(target);
      if(x > 0)
      {
         current.setLeft(remove(current.getLeft(), target));
         return current;
      }
      else if(x < 0)
      {
         current.setRight(remove(current.getRight(), target));
         return current;
      }
      else
      {
         if(current.getRight() == null && current.getLeft() == null)
            return null;
         else if(current.getRight() != null && current.getLeft() == null)
            return current.getRight();
         else if(current.getRight() == null && current.getLeft() != null)
            return current.getLeft();
         else
         {
            current.setValue(max(current.getLeft()));
            current.setLeft(remove(current.getLeft(), max(current.getLeft())));
            return current;
         }
      }
   }
   
   /*  start the addBalanced methods */
   public E addBalanced(E value)  
   {
      size++;
      root = add(root, value);
      root = balanceTree(root);   // for an AVL tree.  You may change this line.
      return value;
   }
   private TreeNode<E> balanceTree(TreeNode<E> t)
   {
      if(t == null)
         return null;
      t.setRight(balanceTree(t.getRight()));
      t.setLeft(balanceTree(t.getLeft()));
      int b = balance(t);
      int r = balance(t.getRight());
      int l = balance(t.getLeft());
      if(b == -2 && l == -1)
      {
         t = rightRotate(t);
      }
      if(b == -2 && l == 1)
      {
         t.setLeft(leftRotate(t.getLeft()));
         t = rightRotate(t);
      }
      if(b == 2 && r == 1)
      {
         t = leftRotate(t);
      }
      if(b == 2 && r == -1)
      {
         t.setRight(rightRotate(t.getRight()));
         t = leftRotate(t);
      }
      return t;
   }
   private int balance(TreeNode<E> t)
   {
      if(t == null)
         return 0;
      return height(t.getRight()) - height(t.getLeft());
   }
   private int height(TreeNode<E> t)
   {
      if(t == null)
         return -1;
      return Math.max(height(t.getRight()), height(t.getLeft())) + 1;
   }
   private TreeNode rightRotate(TreeNode<E> t)
   {
      TreeNode<E> n = new TreeNode<E>(t.getValue(), t.getLeft().getRight(), t.getRight());
      return new TreeNode<E>(t.getLeft().getValue(), t.getLeft().getLeft(), n);
   }
   private TreeNode<E> leftRotate(TreeNode<E> t)
   {
      TreeNode<E> n = new TreeNode<E>(t.getValue(), t.getLeft(), t.getRight().getLeft());
      return new TreeNode<E>(t.getRight().getValue(), n, t.getRight().getRight());
   }
   
   public List<E> toList()
   {
      return toList(root);
   }
   private List<E> toList(TreeNode<E> t)
   {
      List<E> toReturn = new LinkedList<E>();
      if(t == null)
         return toReturn;
      List<E> a = toList(t.getLeft());   //recurse left
      for(E element : a)
         toReturn.add(element);
      toReturn.add(t.getValue());             //inorder visit
      List<E> b = toList(t.getRight());  //recurse right
      for(E element : b)
         toReturn.add(element);
      return toReturn;
   }
}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;

   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }

   public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }

   public E getValue()
   { 
      return value; 
   }

   public TreeNode<E> getLeft() 
   { 
      return left; 
   }

    public TreeNode<E> getRight() 
   { 
      return right; 
   }

    public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }

    public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }

    public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
}