// mlbillington@fcps.edu,  10-20-2019

import javax.swing.JFrame;
import java.util.*;
import java.io.*;
public class NQueensDriver
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("N-Queens");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Scanner sc = new Scanner(System.in);
      System.out.print("Size of board: ");
      NQueens nqueens = new NQueens(sc.nextInt());
      frame.setContentPane(nqueens);
      frame.setVisible(true);
                  
      if(nqueens.solve())
         System.out.println("Solved");
      else
         System.out.println("Solution not found");
   }
}