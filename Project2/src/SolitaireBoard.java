import java.util.ArrayList;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:

      <put rep. invar. comment here>

   */
   
   // <add instance variables here>
   private int [] cardPiles= new int[CARD_TOTAL];
   private int numPiles;
  
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	  cardPiles = new int[CARD_TOTAL]; 
      numPiles=piles.size();
      for(int i=0; i<piles.size(); i++)
      {
         cardPiles[i] = piles.get(i);
      }


      assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   int sum = 0;
	   int numCards=0;
	   cardPiles = new int[CARD_TOTAL+1];
	   Random r = new Random();
	   
	   while(CARD_TOTAL-sum>0) {
		   numCards = r.nextInt(CARD_TOTAL-sum)+1;
		   cardPiles[numPiles++] = numCards;
		   sum+=numCards;
		   
	   }
	   assert isValidSolitaireBoard();
	   
   }
   
   public void playRound() {
	   int newPile = 0;
	   int i=0;
	   while(cardPiles[i]!=0) {
		   cardPiles[i]--;
		   newPile++;
		   i++;
	   }
	   int newCardPiles[]= new int[CARD_TOTAL];
	   int index = 0;
	   for(int j=0; j<newPile; j++) {
		   if(cardPiles[j]!=0) {
			   newCardPiles[index] = cardPiles[j];
			   index++;
		   }
	   }
	   newCardPiles[index] = newPile;
	   cardPiles=newCardPiles;
	   assert isValidSolitaireBoard();
	   
   }
   
   public boolean isDone() {
	   boolean[] checkAllPiles = new boolean[CARD_TOTAL+1];
	   boolean flag =true;
	   for(int i=0; i<cardPiles.length;i++)
	   {
		   if(cardPiles[i]!=0) {
			   checkAllPiles[cardPiles[i]]=true;
		   }
	   }
	   for(int i=1; i<=NUM_FINAL_PILES; i++)
	   {
		   if(checkAllPiles[i]==false) {
			   flag = false;
		   }
			   
	   }
	   assert isValidSolitaireBoard();
	   return flag;
	      
	     
	       // dummy code to get stub to compile

	      
	   }

   public String configString() {
	   String s = "";
		for(int i : cardPiles)
		{
			if(i != 0)
			{
				s += i + " ";
			}
		}
		assert isValidSolitaireBoard();
		return s.substring(0, s.length()-1);  

	        // dummy code to get stub to compile
	   }
   
   private boolean isValidSolitaireBoard() {
	   int sumTotalCards = 0;
	   for(int i=0; i<numPiles; i++) {
		   if(cardPiles[i]<=0 || cardPiles[i]>=CARD_TOTAL) {
			   return false;
		   }
		   sumTotalCards+=cardPiles[i];
	   }
	   return sumTotalCards == CARD_TOTAL;
	      
	        // dummy code to get stub to compile

	   }
   
}
	  
   
      
      
//Name:Jun Yu
//USC NetID:jyu377
//CSCI455 PA2
//Spring 2019


/**
<add main program comment here>
*/




    
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  