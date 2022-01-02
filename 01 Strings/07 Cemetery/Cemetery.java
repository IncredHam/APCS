//Zoe Winston
//September 13, 2020
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need

public class Cemetery
{
   public static void main (String [] args)
   {
      DecimalFormat df = new DecimalFormat("0.0###");
      File file = new File("cemetery_short.txt");
      //File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      //see what you have
      for (int i = 0; i < cemetery.length; i++) 
         System.out.println(cemetery[i].toString());
         
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + df.format(cemetery[min].getAge()));    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge()); 
      //you may create other testing cases here
      //comment them out when you submit your file to Grade-It
      
          
   }
   
   /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      try{
         Scanner sc = new Scanner(f);
         int k = 0;
         while(sc.hasNextLine())
         {
            sc.nextLine();
            k++;
         }
         return k;
      }
      catch(Exception c){
         return 0;
      }
   }

   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      try{
         Scanner sc = new Scanner(f);
         Person[] array = new Person[num];
         for(int k = 0; k < num; k ++)
            array[k] = makeObjects(sc.nextLine());
         return array;
      }catch(Exception e){
         return null;
      }
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String entry)
   {
      String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      int len = 0;
      for(int k = 0; k < entry.length(); k++)
      {
         if(letters.contains(entry.substring(k, k + 1)) && letters.contains(entry.substring(k + 1, k + 2)))
         {
            while(letters.contains(entry.substring(k, k + 1)))
               k++;
            len = k;
            break;
         }
      }
      String name = entry.substring(0, len).trim();
      entry = entry.substring(len).trim();
      String[] array = entry.split(" ");
      String burialDate = array[0] + " " + array[1] + " " + array[2];
      String age = array[3];
      return new Person(name, burialDate, age);
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      double min = arr[0].getAge();
      int index = 0;
      for(int k = 0; k < arr.length; k++)
      {
         if(arr[k].getAge() < min)
         {
            min = arr[k].getAge();
            index = k;
         }
      }
      return index;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      double max = arr[0].getAge();
      int index = 0;
      for(int k = 0; k < arr.length; k++)
      {
         if(arr[k].getAge() > max)
         {
            max = arr[k].getAge();
            index = k;
         }
      }
      return index;
   }        
} 

class Person
{
   //constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0###");
   /* private fields */
   String name = "";
   String burialDate = "";
   double age = 0;
      
   /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String n, String b, String a)
   {
      name = n;
      burialDate = b.trim();
      age = calculateAge(a);
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */
   public double getAge()
   {
      return age;
   }
   public String getName()
   {
      return name;
   }
   public String getBurialDate()
   {
      return burialDate;
   }
   public String toString()
   {
      return name + ", " + burialDate + ", " + df.format(age);
   }
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
   {
      int last = a.length() - 1;
      double foo = 0;
      if(a.substring(last).equals("w"))
         foo = Double.parseDouble(a.substring(0, last)) / 52.143;
      else if(a.substring(last).equals("d"))
         foo = Double.parseDouble(a.substring(0, last)) / 365.242;
      else
         foo = Double.parseDouble(a);
      return foo;
   }
}

/******************************************

 John William ALLARDYCE, 17 Mar 1844, 2.9
 Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 Philip AMIS, 03 Aug 1848, 1.0
 Thomas ANDERSON, 06 Jul 1845, 27.0
 Edward ANGEL, 20 Nov 1842, 22.0
 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 Thomas William COLLEY, 08 Aug 1833, 0.011
 Joseph COLLIER, 03 Apr 1831, 58.0
 
 In the St. Mary Magdelene Old Fish Cemetery --> 
 Name of youngest person: Thomas William COLLEY
 Age of youngest person: 0.011
 Name of oldest person: Joseph COLLIER
 Age of oldest person: 58.0
 
 **************************************/