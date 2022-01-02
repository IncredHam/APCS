//Zoe Winston
//February 18, 2021

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
**********************/
public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      size++;
      root = add(root, s);
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null)
         return new TreeNode(s);
      if(s.compareTo((String)t.getValue()) <= 0)
         t.setLeft(add(t.getLeft(), s));
      else
         t.setRight(add(t.getRight(), s));
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains(String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      if(x.compareTo((String)t.getValue()) < 0)
         return contains(t.getLeft(), x);
      if(x.compareTo((String)t.getValue()) > 0)
         return contains(t.getRight(), x);
      return true;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return null;
      while(t.getLeft() != null)
         t = t.getLeft();
      return (String)t.getValue();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return (String)t.getValue();
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      toReturn += toString(t.getRight());  //recurse right
      return toReturn;
   }
   
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      if(current == null)
         return null;
      int x = ((String)current.getValue()).compareTo(target);
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
}