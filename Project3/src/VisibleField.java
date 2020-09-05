// Name:
// USC NetID:
// CS 455 PA3
// Spring 2019
import java.util.Arrays;

/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.c4
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private MineField mineFieldStatus;
   private int[][] status;

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      mineFieldStatus = mineField;
      status = new int[mineField.numRows()][mineField.numCols()];
      for(int i = 0; i < mineField.numRows(); i++) {
    	  for(int j = 0; j<mineField.numCols(); j++) {
    		  status[i][j] = COVERED;
    	  }
      }
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
	   for(int i = 0; i < status.length; i++) {
		   for(int j =0; j < status[0].length; j++) {
			   status[i][j]= COVERED;
		   }
	   }
      
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineFieldStatus;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
	   assert getMineField().inRange(row, col);
       return status[row][col];       // DUMMY CODE so skeleton compiles
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
	   int guessMine = 0;
	   for(int i = 0; i < status.length; i++) {
		   for(int j = 0; j < status[0].length; j++) {
			   if(status[i][j] == MINE_GUESS) {
				   guessMine++;
			   }
		   }
	   }
      return getMineField().numMines()- guessMine ;       // DUMMY CODE so skeleton compiles

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
	   assert getMineField().inRange(row, col);
	   if(getStatus(row,col) == COVERED) {
		   status[row][col] = MINE_GUESS;
	   }
	   else if(getStatus(row,col) == MINE_GUESS) {
		   status[row][col] = QUESTION;
	   }
	   else if(getStatus(row,col) == QUESTION) {
		   status[row][col] = COVERED;
	   }
      
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      assert getMineField().inRange(row, col);      // DUMMY CODE so skeleton compiles
      if(getMineField().hasMine(row, col)) {
    	  status[row][col] = EXPLODED_MINE;
    	  return false;
      }
      else {
    	  updateSquareStatus(row,col);
      }
      return true;
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game over
    */
   public boolean isGameOver() {
	   int coveredCount = 0;
	   for(int i = 0; i < getMineField().numRows(); i++) {
		   for(int j = 0; j <getMineField().numCols(); j++) {
			   if(status[i][j] == EXPLODED_MINE) {
				   for(int m = 0; m < getMineField().numRows(); m++) {
					   for(int n = 0; n < getMineField().numCols(); n++) {
						   if ((status[m][n] == MINE_GUESS ) && !getMineField().hasMine(m,n)){
							   status[m][n] = INCORRECT_GUESS;
						   }else if((status[m][n] == COVERED||status[m][n] == QUESTION) && getMineField().hasMine(m,n)){
							   status[m][n] = MINE;
						   }
					   
						}
					}
				   return true;
			   }
			   else if(status[i][j] == COVERED || status[i][j] == MINE_GUESS || status[i][j] == QUESTION) {
				   coveredCount++;
			   }
			   
		   }
	   }
	   if(coveredCount == getMineField().numMines()) {
		   for(int m = 0; m < getMineField().numRows(); m++) {
			   for(int n = 0; n <getMineField().numCols(); n++) {
				   if(status[m][n] == COVERED || status[m][n] == QUESTION) {
					   status[m][n] = MINE_GUESS;
				   }
			   }
		   }
			   
				   	   
		   return true;
	   }
	   return false;
	   
	   
   }
   
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      assert getMineField().inRange(row, col);     // DUMMY CODE so skeleton compiles
      if(getStatus(row,col) == COVERED || getStatus(row,col) == MINE_GUESS || getStatus(row,col) == QUESTION) {
    	  return false;
      }
    	  return true;
    	
   }
   
 
   /**
     This method is to explore the adjacent squares recursively  
    */
   private void updateSquareStatus(int row, int col) {
	   if(!getMineField().inRange(row, col) || isUncovered(row,col) || getMineField().hasMine(row,col) || status[row][col] == MINE_GUESS) {
		   return;
	   }
	   status[row][col] = getMineField().numAdjacentMines(row, col);
	   if(getStatus(row, col) == 0) {
		   updateSquareStatus(row, col+1);
		   updateSquareStatus(row, col-1);
		   updateSquareStatus(row+1, col);
		   updateSquareStatus(row-1, col);
		   updateSquareStatus(row+1, col+1);
		   updateSquareStatus(row+1, col-1);
		   updateSquareStatus(row-1, col+1);
		   updateSquareStatus(row-1, col-1);
	   }
	   
   }
   
   
}

 