//Updated on 12.14.2020 v2

//Zoe Winston
//January 12, 2020
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   {
      outfile.println(min + ": " + q);
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      Queue<Customer> q = new LinkedList<Customer>();
      for(int i = 0; i < TIME; i++)
      {
         if(Math.random() < CHANCE_OF_CUSTOMER)
         {
            q.add(new Customer(i));
            customers++;
         }
         if(!q.isEmpty())
         {
            if(q.peek().getOrderAndBeServed() == 0)
            {
               thisCustomersTime = i - q.peek().getTime();
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
               totalMinutes += thisCustomersTime;
               q.remove();
            }
            else
               q.peek().decrementOrderAndBeServed();
         }
         if(q.size() > longestQueue)
            longestQueue = q.size();
         displayTimeAndQueue(q, i);
      }
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
      public Customer(int time)
      {
         arrivedAt = time;
         orderAndBeServed = timeToOrderAndBeServed();
      }
      public int getOrderAndBeServed()
      {
         return orderAndBeServed;
      }
      public void setOrderAndBeServed(int t)
      {
         orderAndBeServed = t;
      }
      public void decrementOrderAndBeServed()
      {
         orderAndBeServed--;
      }
      public int getTime()
      {
         return arrivedAt;
      }
      public String toString()
      {
         return "" + arrivedAt;
      }
   }
}