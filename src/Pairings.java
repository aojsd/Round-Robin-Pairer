import java.util.ArrayList;
import java.util.Collections;


public class Pairings {

	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 1; i <= 6; i++){
			names.add(Integer.toString(i));
		}

		printCycles(makePairs(names));
	}

	static ArrayList<ArrayList<Pair>> makePairs(ArrayList<String> names){
		// Result pairs
		ArrayList<ArrayList<Pair>> list = new ArrayList<ArrayList<Pair>>();
		
		// Table for making pairs and rotating
		int row = names.size()/2;
		int[][] rotate = new int[2][row];
		
		// Initialize table with numbers for each person
		int count = 0;
	    for(int i = 0; i < row; i++){
	        rotate[0][i] = count++;
	    }
	    for(int i = row - 1; i >=0; i--){
	        rotate[1][i] = count++;
	    }
	    
	    // make pairs
	    for(int i = 0; i < names.size()-1; i++) {
	    	ArrayList<Pair> cycle = new ArrayList<Pair>(row);
	    	
	    	// produce pairs for current cycle
	    	for(int j = 0; j < row; j++){
	    		Pair p = new Pair(names.get(rotate[0][j]), names.get(rotate[1][j]));
	    		cycle.add(p);
	    	}
	    	
	    	// rotate table
	    	int temp;
	    	for(int j = 2; j < row; j++){	// first row
	            temp = rotate[0][j];
	            rotate[0][j] = rotate[0][1];
	            rotate[0][1] = temp;
	        }
	        for(int j = row - 1; j >= 0; j--){	// second row
	            temp = rotate[1][j];
	            rotate[1][j] = rotate[0][1];
	            rotate[0][1] = temp;
	        }
	        
	        // shows pairs in random order
	        Collections.shuffle(cycle);
	        list.add(cycle);
	    }
		
	    // shows cycle in random order
	    Collections.shuffle(list);
		return list;
	}
	
	static void printCycles(ArrayList<ArrayList<Pair>> list){
		for(int i = 0; i < list.size(); i++){
			System.out.println("Week " + (i+1) + ":");
			
			for(Pair p: list.get(i)){
				System.out.println(p.toString());
			}
			
			System.out.println();
		}
	}
}
