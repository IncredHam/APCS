//Zoe Winston
//December 28, 2020

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("1.3 2.7 + -6 6 * +");
      postfixExp.add("33 -43 + -55 65 + *");
      postfixExp.add("3 4 * 5 2 / + 5 -");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("3 4 5 * 6 + *");
      postfixExp.add("3 4 5 - 6 2 * - +");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      postfixExp.add("2 -2 ^");
      postfixExp.add("2 7 3 % +");
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.trim().split(" ")));
      /*  enter your code here  */
      Stack<Double> s = new Stack<Double>();
      for(String st : postfixParts)
      {
         if(!isOperator(st))
            s.push(Double.parseDouble(st));
         else if(st.equals("!"))
            s.push(factorial(s.pop()));
         else
            s.push(eval(s.pop(), s.pop(), st));
      }
      return s.pop();
   }
   
   public static double eval(double a, double b, String ch)
   {
      if(ch.equals("+"))
         return b + a;
      if(ch.equals("-"))
         return b - a;
      if(ch.equals("*"))
         return b * a;
      if(ch.equals("/"))
         return b / a;
      if(ch.equals("%"))
         return b % a;
      if(ch.equals("^"))
         return Math.pow(b, a);
      return 0;
   }
   
   public static boolean isOperator(String op)
   {
      if(operators.contains(op))
         return true;
      return false;
   }
   public static double factorial(double a)
   {
      double d = 1;
      for(int i = 1; i <= a; i++)
         d = d * i;
      return d;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 *************************************/