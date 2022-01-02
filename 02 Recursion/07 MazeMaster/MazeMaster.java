//Zoe Winston
//October 13, 2020

import java.util.*;
import java.io.*;

public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      //System.out.print("Enter the maze's filename (no .txt): ");
      //Maze m = new Maze(sc.next()+".txt");
      Maze m = new Maze();    //extension
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
   public Maze()
   {
      maze = new char[(int)(Math.random() * 8) + 3][(int)(Math.random() * 8) + 3];
      int sym;
      boolean st = false;
      boolean ex = false;
      
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         {
            if(st && ex)
               sym = (int)(Math.random() * 2) + 1;
            else if(st)
               sym = (int)(Math.random() * 3) + 1;
            else if(ex)
               sym = (int)(Math.random() * 3);
            else
               sym = (int)(Math.random() * 4);
            
            switch(sym){
               case 0: maze[r][c] = START;
                       startRow = r;
                       startCol = c;
                       st = true;
                       break;
               case 1: maze[r][c] = WALL;
                       break;
               case 2: maze[r][c] = DOT;
                       break;
               case 3: maze[r][c] = EXIT;
                       ex = true;
                       break;
            }
         }
      }
   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         {
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
   public Maze(String filename)    
   {
      Scanner sc = null;
      try
      {
         sc = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return;
      }
      int len = sc.nextInt();
      int wid = sc.nextInt();
      maze = new char[len][wid];
      char[] line = new char[wid];
      for(int r = 0; r < len; r++)
      {
         line = sc.next().toCharArray();
         for(int c = 0; c < wid; c++)
         {
            maze[r][c] = line[c];
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            {
               markAll(startRow, startCol);
               break;
            }
         case 2:
            {
               int count = markAllAndCountRecursions(startRow, startCol);
               System.out.println("Number of recursions = " + count);
               break;
            }
         case 3:
            {
               markTheCorrectPath(startRow, startCol);
               break;
            }
         case 4:         //use mazeNoPath.txt 
            {
               if( !markTheCorrectPath(startRow, startCol) )
                  System.out.println("No path exists."); 
               break;
            }
         case 5:
            {
               if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {
      if(r < maze.length && r >= 0 && c < maze[r].length && c >= 0 && (maze[r][c] == START || maze[r][c] == EXIT || maze[r][c] == DOT))
      {
         if(maze[r][c] == DOT)
            maze[r][c] = PATH;
         markAll(r + 1, c);
         markAll(r - 1, c);
         markAll(r, c + 1);
         markAll(r, c - 1);
      }
   }

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {
      if(r < maze.length && r >= 0 && c < maze[r].length && c >= 0  && (maze[r][c] == START || maze[r][c] == EXIT || maze[r][c] == DOT))
      {
         if(maze[r][c] == EXIT)
            return 0;
         if(maze[r][c] == START)
            return markAllAndCountRecursions(r + 1, c) + markAllAndCountRecursions(r - 1, c) +
               markAllAndCountRecursions(r, c + 1) + markAllAndCountRecursions(r, c - 1);
         if(maze[r][c] == DOT)
         {
            maze[r][c] = PATH;
            return 1 + markAllAndCountRecursions(r + 1, c) + markAllAndCountRecursions(r - 1, c)
               + markAllAndCountRecursions(r, c + 1) + markAllAndCountRecursions(r, c - 1);
         }
      }
      return 0;
   }

   /* 
	 * From handout, #3.
	 * Recur until you find E, then mark the True path.
	 */ 	
   public boolean markTheCorrectPath(int r, int c)
   {
      if(r < maze.length && r >= 0 && c < maze[r].length && c >= 0)
      {
         if(maze[r][c] == EXIT)
            return true;
         if(maze[r][c] == START && (markTheCorrectPath(r + 1, c) || markTheCorrectPath(r - 1, c) || markTheCorrectPath(r, c + 1) || 
            markTheCorrectPath(r, c - 1)))
         {
            return true;
         }
         if(maze[r][c] == DOT)
         {
            maze[r][c] = TEMP;
            if(markTheCorrectPath(r + 1, c) || markTheCorrectPath(r - 1, c) || markTheCorrectPath(r, c + 1) || markTheCorrectPath(r, c - 1))
            {
               maze[r][c] = PATH;
               return true;
            }
            else
            {
               maze[r][c] = DOT;
               return false;
            }
         }
      }
      return false;
   }
	
	
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need. */
      

   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      if(r < maze.length && r >= 0 && c < maze[r].length && c >= 0)
      {
         if(maze[r][c] == EXIT)
         {
            System.out.println("Number of steps = " + count);
            return true;
         }
         if(maze[r][c] == START && (markCorrectPathAndCountSteps(r + 1, c, count + 1) || markCorrectPathAndCountSteps(r - 1, c, count + 1) || 
            markCorrectPathAndCountSteps(r, c + 1, count + 1) || markCorrectPathAndCountSteps(r, c - 1, count + 1)))
         {
            return true;
         }
         if(maze[r][c] == DOT)
         {
            maze[r][c] = TEMP;
            if(markCorrectPathAndCountSteps(r + 1, c, count + 1) || markCorrectPathAndCountSteps(r - 1, c, count + 1) || 
               markCorrectPathAndCountSteps(r, c + 1, count + 1) || markCorrectPathAndCountSteps(r, c - 1, count + 1))
            {
               maze[r][c] = PATH;
               return true;
            }
            else
            {
               maze[r][c] = DOT;
               return false;
            }
         }
      }
      return false;
   }
}

/*****************************************
 
 ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 3
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
     
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 5
 Number of steps = 14
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  ********************************************/