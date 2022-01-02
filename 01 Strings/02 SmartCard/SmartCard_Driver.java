//Zoe Winston
//September 4, 2020
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburbia", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.exit(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.exit(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.exit(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.exit(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.exit(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   double money = 0;
   boolean board = false;
   Station place = null;
   SmartCard()
   {
      money = 0.00;
   }
   SmartCard(double d)
   {
      money = d;
   }
   void addMoney(double d)
   {
      money = money + d;
   }
   String getBalance()
   {
      return df.format(money);
   }
   boolean isBoarded()
   {
      return board;
   }
   void board(Station s)
   {
      if(board)
         System.out.println("Error: already boarded?!");
      else if(money < .50)
         System.out.println("Insufficient funds to board. Please add more money.");
      else
      {
         place = s;
         board = true;
         System.out.println("Boarded at " + s.getName() + ".\tSmartCard has " + df.format(money));
      }
   }
   double cost(Station s)
   {
      if(s.getZone() == place.getZone())
         return .5;
      int diff = Math.abs(s.getZone() - place.getZone());
      return .5 + diff * .75;
   }
   void exit(Station s)
   {
      if(!board)
         System.out.println("Error: Did not board?!");
      else if(cost(s) > money)
         System.out.println("Insufficient funds to exit. Please add more money.");
      else
      {
         money -= cost(s);
         board = false;
         System.out.println("From "+place.getName()+" to "+s.getName()+" costs "+df.format(cost(s))+".\tSmartCard has "+df.format(money));
         place = null;
      }
   }
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   double getMoneyRemaining()
   {
      return money;
   }
   
   Station getBoardedAt()
   {
      return place;
   }
  
   boolean getIsOnBoard()
   {
      return board;
   }
}
   
//Note Station is not a public class.  Why?
class Station
{
   String name;
   int zone;
   Station()
   {
      name = "Union";
      zone = 1;
   }
   Station(String n, int z)
   {
      name = n;
      zone = z;
   }
   String getName()
   {
      return name;
   }
   int getZone()
   {
      return zone;
   }
   void setName(String n)
   {
      name = n;
   }
   void setZone(int n)
   {
      zone = n;
   }
   public String toString()
   {
      return "Station " + name + " is in Zone " + zone;
   }
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/