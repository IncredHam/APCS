//Zoe Winston
//September 4, 2020

public class warmup
{
   public static void main(String[] args)
   {
      int[] array = new int[5];
      for(int k = 1; k <= array.length;  k++)
         array[k - 1] = k;
      System.out.println(findAvg(array));
    }
   public static double findAvg(int[] array)
   {
   	int sum = 0;
   	for(int n: array)
   		sum += n;
   	int length = array.length;
   	return (double)sum / length;
   }
}