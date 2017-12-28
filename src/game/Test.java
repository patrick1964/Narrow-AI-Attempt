package game;

import java.util.ArrayList;
import java.util.Random;

public class Test {
	public static void main(String[] args){
		
	}
	ArrayList<Integer> confirmedNums;
	ArrayList<Integer> possibleNums;
	ArrayList<Integer> eliminatedNums;
	ArrayList<Integer[]> tried;
	ArrayList<Integer[]> result;
	ArrayList<Integer> repeatableNums;
	Random r= new Random();
	public int[] generateGuess(int d){
		if(confirmedNums.size()==d||eliminatedNums.size()>10-d){
			return knownGuess(d);
		}
		return eliminatingGuess(d);
	}
	private int[] eliminatingGuess(int d) {
		// TODO Auto-generated method stub
		return null;
	}
	private int[] knownGuess(int d) {
		int guess[]=new int[d];
		ArrayList<Integer> pos= new ArrayList<Integer>();
		boolean started=false;
		while(!tried(guess)||!started){
			started=true;
			if(confirmedNums.size()==d){
				for(int i=0;i<d;i++){
					while(!pos.contains(r.nextInt()%confirmedNums.size())){
						guess[i]=r.nextInt()%confirmedNums.size();
						pos.add(r.nextInt()%confirmedNums.size());
					}
				}
			}else{
				guess = maxResultGuess(d);
			}
		}
		return guess;
	}
	private int[] maxResultGuess(int d) {
		int guess[]=new int[d];
		int counter=0;
		if(repeatableNums.isEmpty()){
			for(int i=0;i<confirmedNums.size();i++){
				if(i==0){
					for(int j=0;j<d-confirmedNums.size();j++){
						guess[counter]=confirmedNums.get(i);
						counter++;
					}
				}else{
					guess[counter]=confirmedNums.get(i);
					counter++;
				}
			}
			resort();
		}else{
			for(int i:repeatableNums){
				confirmedNums.add(i);
			}
			repeatableNums.clear();
			guess=knownGuess(d);
			resort();
		}
		return guess;
		
	}
	private void resort() {
		// TODO Auto-generated method stub
		
	}
	//complete block
	private boolean tried(int[] guess) {
		if(binarySearch(tried, guess)>-1)
			return true;
		return false;
	}
	private int getVal(Integer[] guess) {
		int val=0;
		for(int i=0;i<guess.length;i++){
			val+=guess[i]*Math.pow(10, guess.length-i);
		}
		return val;
	}
	private int getVal(int[] guess) {
		int val=0;
		for(int i=0;i<guess.length;i++){
			val+=guess[i]*Math.pow(10, guess.length-i);
		}
		return val;
	}
	public int binarySearch(ArrayList<Integer[]> tryed, int[] guess){
		int first=0;
		int last=tried.size();
		boolean found=false;
		int valG=getVal(guess);
		while(!found){
			if(getVal(tried.get((last+first)/2))<valG)
				first=(last+first)/2;
			if(getVal(tried.get((last+first)/2))>valG)
				last=(last+first)/2;
			if(getVal(tried.get((last+first)/2))==valG)
				return (last+first)/2;
			if(first==last)
				found=true;
		}
		return -1;
		
	}
	public int binarySearch(ArrayList<Integer[]> tryed, int guess,int pos){
		int first=0;
		int last=tried.size();
		boolean found=false;
		int valG=guess*(int)Math.pow(10, tried.get(0).length-pos);
		while(!found){
			if(getVal(tried.get((last+first)/2))<valG)
				first=(last+first)/2;
			if(getVal(tried.get((last+first)/2))>valG)
				last=(last+first)/2;
			if(getVal(tried.get((last+first)/2))==valG)
				return (last+first)/2;
			if(first==last){
				found=true;
		}
		return -1;
	}
	//end complete block
}
