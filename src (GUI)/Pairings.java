import java.util.ArrayList;
import java.util.Collections;


public class Pairings {

	private ArrayList<String> names;
	private ArrayList<ArrayList<Pair>> list;
	
	public Pairings(){
		names = new ArrayList<String>();
	}
	
	public void addName(String s) {
		names.add(s);
	}

	public void makePairs(){
		boolean odd = false;
		if(names.size() % 2 != 0){
			odd = true;
			names.add("N/A");
		}
		
		// Result pairs
		list = new ArrayList<ArrayList<Pair>>(names.size() - 1);
		
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
	    
	    // remove placeholder if odd
	    if(odd){
	    	names.remove(names.size() - 1);
	    }
	}
	
	public String printCycles(){
		StringBuffer ret = new StringBuffer("");
		for(int i = 0; i < list.size(); i++){
			ret.append("Week " + (i+1) + ":\n");
			
			for(Pair p: list.get(i)){
				ret.append(p.toString() + "\n");
			}
			
			ret.append("\n");
		}
		return ret.toString();
	}
}
