//Zoe Winston
//October 7, 2020

public class MatrixRecreate
{
   public static void main(String[] args)
   {
      int[][] matrix = TheMatrix.create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      TheMatrix.count(matrix, rowcount, colcount);
      TheMatrix.re_create(rowcount, colcount);
      int[][] new_matrix = TheMatrix.getRecreatedMatrix();
      if( new_matrix == null )
         System.out.println("Did not find a match.");
      else
         TheMatrix.display( new_matrix, rowcount, colcount );
   }
}
class TheMatrix
{
	//do not instantiate recreatedMatrix yet. Only instantiate and set that in recur.
   private static int[][] recreatedMatrix = null;
   public static int[][] getRecreatedMatrix()
   {
      return recreatedMatrix;
   }
   public static int[][] create()
   {
      int[][] thing = new int[(int)(Math.random() * 5) + 2][(int)(Math.random() * 5) + 2];
      for(int k = 0; k < thing.length; k++)
      {
         for(int j = 0; j < thing[k].length; j++)
            thing[k][j] = (int)(Math.random() * 2);
      }
      return thing;
   }
   public static void count(int[][] matrix, int[] rowcount, int[] colcount)
   {
      for(int k = 0; k < matrix.length; k++)
      {
         for(int j = 0; j < matrix[k].length; j++)
         {
            if(matrix[k][j] == 1)
            {
               rowcount[k] += 1;
               colcount[j] += 1;
            }
         }
      }
   }
   public static void display(int[][] matrix, int[] rowcount, int[] colcount)
   {
      System.out.print("    ");
      for(int k = 0; k < colcount.length; k++)
         System.out.print(colcount[k] + " ");
      System.out.println();
      System.out.print("    ");
      for(int k = 0; k < (colcount.length * 2 - 1); k++)
         System.out.print("-");
      System.out.println();
      for(int k = 0; k < rowcount.length; k++)
      {
         System.out.print(rowcount[k] + " | ");
         for(int j = 0; j < matrix[k].length; j++)
            System.out.print(matrix[k][j] + " ");
         System.out.println();
      }
   }
   public static void re_create(int[] orig_rowcount, int[] orig_colcount)
   {
      int[][] new_matrix = new int[orig_rowcount.length][orig_colcount.length];
      for(int k = 0; k < new_matrix.length; k++)
      {
         for(int j = 0; j < new_matrix[0].length; j++)
         {
            new_matrix[k][j] = 0;
         }
      }
      recur(new_matrix, orig_rowcount, orig_colcount, 0, 0);
   }
   private static void recur(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount, int row, int col)
   {
      if(compare(new_matrix, orig_rowcount, orig_colcount))    //base case: if new_matrix works, then copy over to recreatedMatrix
      {
      	//copy over from new_matrix to recreatedMatrix (not just references)
         recreatedMatrix = new int[new_matrix.length][];
         for(int i = 0; i < new_matrix.length; i++)
         {
            recreatedMatrix[i] = new int[new_matrix[i].length];
            for (int j = 0; j < new_matrix[i].length; j++)
            {
               recreatedMatrix[i][j] = new_matrix[i][j];
            }
         }           //we've found it!
      }
   	//ENTER YOUR PERMUTATION CODE HERE
      else
      {
         if(col < new_matrix[0].length)
         {
            if(row < new_matrix.length)
            {
               new_matrix[row][col] = 1;
               recur(new_matrix, orig_rowcount, orig_colcount, row + 1, col);
               new_matrix[row][col] = 0;
               recur(new_matrix, orig_rowcount, orig_colcount, row + 1, col);
            }
            else
               recur(new_matrix, orig_rowcount, orig_colcount, 0, col + 1);
         }
      }
   }
   private static boolean compare(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount)
   {
      int[] rowcount = new int[orig_rowcount.length];
      int[] colcount = new int[orig_colcount.length];
      for(int k = 0; k < new_matrix.length; k++)
      {
         for(int j = 0; j < new_matrix[k].length; j++)
         {
            if(new_matrix[k][j] == 1)
            {
               rowcount[k] += 1;
               colcount[j] += 1;
            }
         }
      }
      boolean thing1 = true;
      for(int k = 0; k < rowcount.length; k++)
      {
         if(rowcount[k] != orig_rowcount[k])
            thing1 = false;
      }
      if(thing1 == true)
      {
         for(int k = 0; k < colcount.length; k++)
         {
            if(colcount[k] != orig_colcount[k])
               thing1 = false;
         }
      }
      return thing1;
   }
}
