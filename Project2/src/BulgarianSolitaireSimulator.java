import java.util.ArrayList;
import java.util.Scanner;

/**
 * BulgarianSolitaireSimulator class
 *
 *  The main program that does a Bulgarian Solitaire Simulation.
 *
 *  SolitaireBoard class has a one-args constructor and a no-args constructor:
 *      one-args constructor let user set up the initial configuration
 *      no-args constructor Randomly divides cards into some number of piles of random size
 *  BulgarianSolitaireSimulator will call one of these constructors at each for Simulation
 *
 *  How to run : 1.run -ea BulgarianSolitaireSimulator without any argument
 *                  one-args constructor will be called, and cards will be Randomly divided
 *               2.run -ea BulgarianSolitaireSimulator -u
 *                  "userConfig" mode,
 *                  that is, call the one-args constructor of SolitaireBoard and let user
 *                  set up the initial configuration
 *               3.run -ea BulgarianSolitaireSimulator -s
 *                  "singleStep" mode,
 *                  that is, stops between every round of the game
 *                  the game only continues when the user hits enter
 *               4.run -ea BulgarianSolitaireSimulator -u -s
 *                  enter "userConfig" mode and "singleStep" mode
 *               Note: -ea is for assertion-checking,
 *                      a private method will test the representation invariant
 *                      for the internals of a solitaire board
 *
 *  BulgarianSolitaireSimulator class has the following instance variable:
 *          singleStep : default false, if user enter "-s" as a argument then true, and enter singleStep mode
 *          userConfig : default false, if user enter "-u" as a argument then true, and enter userConfig mode
 *          solitaireGame : the instance of SolitaireBoard class, call method in that class for simulation
 *          in : the Scanner, save it because it will be reused several times
 *
 *  BulgarianSolitaireSimulator class supports the following methods:
 *      private methods:
 *          void readAndValidateInput(in,inputPile)
 *          // read and validate the input from user in userConfig mode
 *
 *          void runSolitaireGame(solitaireGame,singleStep,in)
 *          // run the game and print out the configurations in each round, user should press return key to
 *             move on to the next round if and only if the simulator is in singleStep mode
 *
 */

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
     
        boolean singleStep = false;
        boolean userConfig = false;
        
        Scanner in = new Scanner(System.in);
        SolitaireBoard sb;

        for (int i = 0; i < args.length; i++) 
		{
        	if (args[i].equals("-u")) 
			{
				userConfig = true;
			}
			else if (args[i].equals("-s")) 
			{
				singleStep = true;
			}
		}

        if(userConfig)
        {
            ArrayList<Integer> nums = new ArrayList<>();
            System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
            System.out.println("You will be entering " + "the initial configuration of the cards " + "(i.e., how many in each pile).");
            readNums(in,nums);
            sb= new SolitaireBoard(nums);
        }
        else 
        {
        	sb = new SolitaireBoard();
        }

       play(sb,singleStep,in);
   }
    /**
     Read the input from the user, and the restrictions of input are as follows:
     1. input should be entered as a space-separated list
     2. all element in the list should be positive integers
     3. the total number of the list is equal to SolitaireBoard.CARD_TOTAL,
        a named constant defined in SolitaireBoard class
     @param in : the input Scanner
     @param inputPile : Save the initial configuration, and user input the list in userConfig mode
     */
   private static void readNums(Scanner in,ArrayList<Integer> nums){
       while(true) {
    	   int num;
           int sum = 0;
           boolean flag = true;
           System.out.println("Please enter a space-separated " + "list of positive integers followed by newline:");
           String string = in.nextLine();
           Scanner scPiles = new Scanner(string);

           while(scPiles.hasNext()) {
               if(!scPiles.hasNextInt()) {
                   flag = false;
                   break;
               }
               num = scPiles.nextInt();
               if (num <= 0) {
                   flag = false;
                   break;
               }
               nums.add(num);
               sum+= num;
           }

           if(sum != SolitaireBoard.CARD_TOTAL || !flag){
               System.out.println("ERROR: " + "Each pile must have at least one card" + " and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
               nums.clear();
           } 
           else{ 
        	   return;
        	   }
       }
   }
    /**
     Run the Solitaire game.
     the game only continues when the user hits enter when in singleStep mode.
     @param solitaireGame : the instance of SolitaireBoard class, call method in that class for simulation
     @param singleStep : true if it is in singleStep mode
     @param in : the input Scanner, which is used to scan the return key
     */
   private static void play(SolitaireBoard sb, boolean singleStep,Scanner in){
       int count = 0;
       
       System.out.println("Initial configuration: " + sb.configString());

       while (!sb.isDone()) {
           sb.playRound();
           System.out.println("[" + ++count + "] Current configuration: " + sb.configString());
           if(singleStep) {
               System.out.print("<Type return to continue>");
               in.nextLine();
           }
       }

       System.out.println("Done!");
   }

}