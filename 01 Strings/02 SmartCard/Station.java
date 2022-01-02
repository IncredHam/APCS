//Zoe Winston
//September 4, 2020
public class Station
{
   String name;
   int zone;
   public Station(String n, int z)
   {
      name = n;
      zone = z;
   }
   public String getName()
   {
      return name;
   }
   public int getZone()
   {
      return zone;
   }
   public String toString()
   {
      return "Station " + name + " is in Zone " + zone;
   }
   public void setName(String n)
   {
      name = n;
   }
   public void setZone(int n)
   {
      zone = n;
   }
}