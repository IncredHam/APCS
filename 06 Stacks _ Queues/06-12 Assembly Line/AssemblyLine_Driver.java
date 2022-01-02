//Zoe Winston
//January 19, 2021
   import java.util.*;

    public class AssemblyLine_Driver
   {
      static int NDISKS = 50;
      static int MAXRADIUS = 100;
       public static void main(String[] args)
      {
         AssemblyLine a = new AssemblyLine(NDISKS, MAXRADIUS);
         a.showInput();
         a.process();
         a.showOutput();
      }
   }
   
    class AssemblyLine
   {
      private Queue<Disk> assemblyLineIn;
      private Queue<Pyramid> assemblyLineOut;
      private Pyramid robotArm;
   	/**
   		* initializes this object so the assemblyLineIn contains 
   		* nDisks with random radii;  assemblyLineOut is initialized to * an empty Queue; robotArm is initialized to an empty Pyramid.
   		**/
   	//Part A
       public AssemblyLine( int nDisks, int maxRadius )
      {	
         assemblyLineOut = new LinkedList<Pyramid>();
         robotArm = new Pyramid();
         assemblyLineIn = new LinkedList<Disk>();
         for(int i = 0; i < nDisks; i++)
            assemblyLineIn.add(new Disk((int)(Math.random() * maxRadius) + 1));
      }
   
   	/**
   		* "flips over" the pyramid in the robotArm and adds it to the
   		* assemblyLineOut queue.
   		* Precondition:  robotArm is not empty and holds an inverted 
   		*				pyramid of disks
   		**/
   	// Part B
       private void unloadRobot()
      {
         Pyramid p = new Pyramid();
         while(!robotArm.isEmpty())
            p.push(robotArm.pop());
         assemblyLineOut.add(p);
      }
   
   	/**
   		* processes all disks from assemblyLineIn; a disk is processed
   		* as follows:  if robotArm is not empty and the next disk does
   		* not fit on top of robotArm (which must be an inverted 
   		* pyramid) then robotArm is unloaded first; the disk from
   		* assemblyLineIn is added to robotArm; when all the disks
   		* have been retrieved from assemblyLineIn, robotArm is unloaded.
   		*  Precondition:  robotArm is empty;
   		*				assemblyLineOut is empty
   		**/
   	//Part C
       public void process()
      {
         while(!assemblyLineIn.isEmpty())
         {
            if(!robotArm.isEmpty() && robotArm.peek().compareTo(assemblyLineIn.peek()) > 0)
               unloadRobot();
            /*if(!robotArm.isEmpty())
            {
               Disk d = robotArm.pop();
               if(robotArm.isEmpty())
                  robotArm.push(d);
               else if(robotArm.peek().compareTo(d) > 0 && d.compareTo(assemblyLineIn.peek()) < 0 || robotArm.peek().compareTo(d) < 0
                && d.compareTo(assemblyLineIn.peek()) > 0)
               {
                  robotArm.push(d);
                  unloadRobot();
               }
               else
                  robotArm.push(d);
            }*/
            robotArm.push(assemblyLineIn.remove());
         }
         unloadRobot();
      }
      
       public void showInput()
      {
         System.out.println(assemblyLineIn);
      }
       public void showOutput()
      {
         System.out.println(assemblyLineOut);
      }
   }
   //Disk is standard and straightforward.
    class Disk implements Comparable<Disk>
   {
      private int mySize;
      public Disk(int size)
      {
         mySize = size;
      }
      public int getSize()
      {
         return mySize;
      }
      public void setSize(int s)
      {
         mySize = s;
      }
      public int compareTo(Disk d)
      {
         if(d.getSize() <= this.getSize())
            return 1;
         return -1;
      }
      public String toString()
      {
         return "" + mySize;
      }
   }
   //Pyramid is sly.
    class Pyramid extends Stack<Disk>
   {
      
   }