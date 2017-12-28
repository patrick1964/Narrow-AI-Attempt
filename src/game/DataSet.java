package game;

import java.util.ArrayList;

public class DataSet {
	public static ArrayList<ArrayList<double[][][]>> data= new ArrayList<ArrayList<double[][][]>>();
	public static int currentGame=0;
	int actions;
	int states;
	int dimention;
	int[] stateValConversion;
	int location=0;
	public DataSet(int d){
		actions=10*d;
		states=getStates(d);
		dimention=d;
		stateValConversion= statesVals(states);
		data.add(initQvalues(d));
		
	}
	private int[] statesVals(int s) {
		int[] temp = new int[s];
		int multiplier=10;
		int counter=0;
		int count=0;
		for(int i=dimention;i>0;i--){
			for(int j=0;j<=i;j++){
				temp[count]=j*multiplier+counter;
				count++;
			}
			counter=(dimention+1-i)*100;
			if(i==1)
				temp[count]=counter;
		}
		return temp;
	}
	private int getStates(int d) {
		int temp=0;
		for(int i=1;i<=d+1;i++){
			temp+=i;
		}
		return temp;
	}
	private ArrayList<double[][][]> initQvalues(int d) {
		ArrayList<double[][][]> arr= new ArrayList<double[][][]>();
		
		arr.add(new double[states][d][10]);
		for(int i=0;i<states;i++){
			for(int j=0;j<actions;j++){
				arr.get(0)[i][j/10][j%10]=0;
			}
		}
		return arr;
	}
	public void setQValue(int[] state, int actionLoc,int actionNum, double new_Q) {
		
		data.get(currentGame).get(location)[convertState(state)][actionLoc][actionNum]=new_Q;
	}
	public double getMaxQValue(int[] state) {
		double max=0;
		for(double j[]:data.get(currentGame).get(location)[convertState(state)]){
			for(double i:j){
				if(i>max)
					max=i;
			}
		}
		return max;
	}
	public int convertState(int[] state){
		int temp= state[0]*100+state[1]*10+0;
		return binarySearch(stateValConversion,temp);
	}
	public int binarySearch(int[] arr, int val){
		int first=0;
		int last=arr.length;
		boolean found=false;
		while(!found){
			if(arr[(last+first)/2]<val)
				first=(last+first)/2;
			if(arr[(last+first)/2]>val)
				last=(last+first)/2;
			if(arr[(last+first)/2]==val)
				return (last+first)/2;
			if(first==last)
				found=true;
		}
		return -1;
		
	}
}