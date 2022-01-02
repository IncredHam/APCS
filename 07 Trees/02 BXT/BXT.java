// Zoe Winston
// February 8, 2021
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      String[] st = str.split(" ");
      Stack<TreeNode> x = new Stack<TreeNode>();
      for(String s : st)
      {
         if(!isOperator(s))
            x.push(new TreeNode(s));
         else
         {
            TreeNode temp = x.pop();
            x.push(new TreeNode(s, x.pop(), temp));
         }
      }
      root = x.pop();         
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      String val = (String)t.getValue();
      if(isOperator(val))
         return computeTerm(val, evaluateNode(t.getRight()), evaluateNode(t.getLeft()));
      else
         return Double.parseDouble(val);
   }
   
   private double computeTerm(String s, double a, double b)
   {
      switch(s)
      {
         case "+": return b + a;
         case "-": return b - a;
         case "*": return b * a;
         case "/": return b / a;
      }
      return 0;
   }
   
   private boolean isOperator(String s)
   {
      return "+-*/".contains(s);
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   public String inorderTraverseWithParentheses()
   {
      return inorderTraverseWithParentheses(root);
   }

   private String inorderTraverseWithParentheses(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      String r = "n";
      if(t.getRight() != null) r = (String)t.getRight().getValue();
      String l = "n";
      if(t.getLeft() != null) l = (String)t.getLeft().getValue();
      String c = (String)t.getValue();
      
      toReturn += inorderTraverseWithParentheses(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      if("*/".contains(c) && ("+-".contains(r) || "+-".contains(l)))
         toReturn += "( ";
      toReturn += inorderTraverseWithParentheses(t.getRight());  //recurse right
      if("*/".contains(c) && ("+-".contains(r) || "+-".contains(l)))
         toReturn += ") ";
      return toReturn;
   }
}