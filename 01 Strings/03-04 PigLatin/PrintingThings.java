//Zoe Winston
//September 6, 2020
import java.util.Scanner;
import java.io.*;
public class PrintingThings
{
   public static void main(String[] args) throws IOException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter your name:\t");
      String name = sc.nextLine();
      System.out.println("You entered " + name);
      System.out.print("Enter an integer:\t");
      double x = sc.nextDouble();
      System.out.print("Enter a decimal number:\t");
      double y = sc.nextDouble();
      System.out.println("The sum is " + (x + y));
   }
}