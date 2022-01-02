//Zoe Winston
//September 8, 2020
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      //part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      int len = s.length();
      if(len == 0)
         return "";
   
      //remove and store the beginning punctuation 
      String beg = "";
      if(punct.contains(s.substring(0, 1)))
      {
         int k = 0;
         while(punct.contains(s.substring(k, k + 1)))
            k++;
         beg = s.substring(0, k);
         s = s.substring(k);
      }
      len = s.length();
           
      //remove and store the ending punctuation 
      String end = "";
      if(punct.contains(s.substring(len - 1, len)))
      {
         int k = len - 1;
         while(punct.contains(s.substring(k, k + 1)))
            k--;
         end = s.substring(k + 1);
         s = s.substring(0, k + 1);
      }
         
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      len = s.length();
      int firstVowel = -1;
      String letter = "";
      for(int k = 0; k < len; k++)
      {
         letter = s.substring(k, k + 1);
         if(vowels.contains(s.substring(k, k + 1)) || letter.equalsIgnoreCase("y"))
         {
            if(!(k == 0 && letter.equalsIgnoreCase("y")) && !(k > 0 && s.substring(k - 1, k + 1).equalsIgnoreCase("qu")))
            {
               firstVowel = k;
               break;
            }
         }
      }
      //if no vowel has been found
      if(firstVowel == -1)
         return "**** NO VOWEL ****";
      //is the first letter capitalized?
      if(firstVowel != 0 && letters.substring(0, 26).contains(s.substring(0, 1)))
      {
         s = s.substring(0, 1).toLowerCase() + s.substring(1, firstVowel) + 
            s.substring(firstVowel, firstVowel + 1).toUpperCase() + s.substring(firstVowel + 1);
      }
      
      //return the piglatinized word 
      if(firstVowel == 0)
         s = s + "way";
      else
         s = s.substring(firstVowel) + s.substring(0, firstVowel) + "ay";
      return beg + s + end;
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      String line = "";
      String[] words = null;
      while(infile.hasNextLine())
      {
         line = infile.nextLine();
         words = line.split(" ");
         for(String bleh: words)
         {
            outfile.print(pig(bleh));
            if(!bleh.equals(words[words.length - 1]))
               outfile.print(" ");
         }
         if(infile.hasNextLine())
            outfile.println();
      }
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
      int len = s.length();
      String rev = "";
      
      String beg = "";
      if(punct.contains(s.substring(0, 1)))
      {
         int k = 0;
         while(punct.contains(s.substring(k, k + 1)))
            k++;
         beg = s.substring(0, k);
         s = s.substring(k);
      }
      len = s.length();
      
      String end = "";
      if(punct.contains(s.substring(len - 1, len)))
      {
         int k = len - 1;
         while(punct.contains(s.substring(k, k + 1)))
            k--;
         end = s.substring(k + 1);
         s = s.substring(0, k + 1);
      }
      len = s.length();
      if(letters.substring(0, 26).contains(s.substring(0, 1)))
      {
         s = s.substring(0, 1).toLowerCase() + s.substring(1, len - 1) + s.substring(len - 1).toUpperCase();
      }
      for(int k = len; k > 0; k--)
      {
         rev += s.substring(k - 1, k);
      }
      return beg + rev + end;
   }
}
