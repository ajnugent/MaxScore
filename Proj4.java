import java.util.Scanner;

public class Proj4 {

    static class Pair{
        int first, second;
        int element = 0; //what element is in that array index 
        int pick = 0; //the index of array
        public String toString(){
            
            return "(" + appendZero(first) + "," + appendZero(second) + ") " + element;
        }
    }
    //----------------------------CHART-----------------------------------
    public Pair[][] findMoves(int array[]){
        
        Pair[][] moves = new Pair[array.length][array.length];
        
        for(int i=0; i < moves.length; i++){
            for(int j=0; j < moves[i].length; j++){
                moves[i][j] = new Pair();
            }
        }
        
        for(int i=0; i < array.length; i++){
            moves[i][i].first = array[i];
            
            //to track the sequence of moves
            moves[i][i].element = array[i];
            moves[i][i].pick = i;
        }
        
        for(int l = 2; l <= array.length; l++){
            for(int i=0; i <= array.length - l; i++){
                int j = i + l -1;
                if(array[i] + moves[i+1][j].second > moves[i][j-1].second + array[j]){
                    moves[i][j].first = array[i] + moves[i+1][j].second;
                    moves[i][j].second = moves[i+1][j].first;
                    moves[i][j].element = array[i];
                    moves[i][j].pick = i;
                    
                }else{
                    moves[i][j].first = array[j] + moves[i][j-1].second;
                    moves[i][j].second = moves[i][j-1].first;
                    moves[i][j].element =array[j];
                    moves[i][j].pick = j;     
                }
            }
        }
        
        return moves;
    }
    //---------------------------PRINT SEQUENCE------------------------------
    public void printSequence(int array[], Pair moves[][]){ 
        int i = 0;
        int j = array.length - 1;
        int step;
        int score1 = 0;
        int score2 = 0;
        
    	int counter = 0;
        for (int k = 0; k < array.length; k++) {
            if((counter % 2) == 0){
            	step = moves[i][j].pick;
            	score1 = score1 + array[step];
            	
            	System.out.print("Player 1 Item chosen: " + array[step] + "      SCORE: " + score1 + "\n");
            	counter++;
            	if (step <= i) {
                	i = i + 1;
            	} else {
                	j = j - 1;
            	}
            
            	}
            else{
             	step = moves[i][j].pick;
            	score2 = score2 + array[step];
            	//this is the value of element and its score
            	System.out.println("Player 2 Item chosen: " + array[step] + "      SCORE: " + score2 + "\n");
            	counter++;
            	if (step <= i) {
                i = i + 1;
            	} else {
                j = j - 1;
            	}
            
            }
           
        }
        
        System.out.println("FINAL SCORE PLAYER 1: " + score1);
        System.out.println("FINAL SCORE PLAYER 2: " + score2);
        
    }
    //-------------------BUILD + DISPLAY ARRAY INPUT-------------------------
    private static void printArray(int[] anArray) { 
      for (int i = 0; i < anArray.length; i++) {
         if (i > 0) {
            System.out.print(", ");
         }
         System.out.print(anArray[i]);
      }
   }
   //--------------------FORMAT GAMEPLAY CHART-------------------------------
   public static String appendZero(int v) { 
        return v < 10 ? "0" + v : v + "";
    }


   //--------------------------------MAIN -----------------------------------
    public static void main(String args[]){
       
       
        Proj4 nms = new Proj4();
        System.out.println("Please enter how many numbers in the array: "); //ask user for number of values to build array
        Scanner scan = new Scanner( System.in );
  	    int values = Integer.parseInt(scan.nextLine());
  	   	
  	    int arrayOfValues[] = new int[values];
		for (int i = 0; i < arrayOfValues.length; i++) {
			System.out.print("Enter a number " + (i+1) + " : ");
		        arrayOfValues[i] = scan.nextInt();
		}
		 System.out.print(" ARRAY -->           [");  
		 printArray(arrayOfValues); 
		 System.out.println("]");  
	
        
        //int array[] = {3, 1, 7, 5, 8, 4};
        //int array[] = {3,1,5,6,2,9,3};
        Pair[][] moves = nms.findMoves(arrayOfValues);
        
        System.out.print("--------------------------------GAME PLAY CHART --------------------------------\n");
        for(int i=0; i < moves.length; i++){
            for(int j=0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " || ");
            }
            System.out.print("\n");
            System.out.print("--------------------------------------------------------------------------------");
            System.out.print("\n");
        }
        
     
        System.out.println("The moves by first player and second player:");
        nms.printSequence(arrayOfValues, moves);
        
        
        
    }
}