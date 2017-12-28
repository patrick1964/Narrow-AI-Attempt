package game;
import java.util.Scanner;
import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
public class Ai implements Readable {
	
	Game g = new Game(this);
	Scanner s = new Scanner((Readable) System.out);
	DataSet data;
	int dimention;
	static double alpha = 0.8;
	static double gamma = 0.5;
	int[] state;
    int[] newstate;
    int action;
    double reward;
	private boolean actionTaken=false;
	
	public Ai(int d){
		dimention= d;
		data=new DataSet(dimention);
		System.out.println("AI initialized");
	}
	public void input(){
		g.play(dimention);
		while(s.hasNextLine()){
			react(s.nextLine());
		}
		s.close();
	}
	
	public void react(String input){
		double this_Q;
	    double max_Q;
	    double new_Q;
		action = selectAction( state );
		newstate = add(action);
	    reward = getReward(newstate);

	    this_Q = getQValue( state, action );
	    max_Q = data.getMaxQValue( newstate );

	    // Calculate new Value for Q
	    new_Q = this_Q + alpha * ( reward + gamma * max_Q - this_Q );
	    data.setQValue( state, action, new_Q );

	    // Set state to the new state.
	    state = newstate;
	}
	private double getQValue(int[] state2, int action2) {
		int[] arr= g.generateArr(action2);
		for(int i=0;i<data.get(currentGame).size();i++){
			if(i%2==1){
				if(data.get(currentGame).get(i-1).equals(g.generateArr(action2))){
					qvalue.
				}
			}
		}
		return 0;
	}
	private double getReward(int[] arr) {
		return arr[0]*100+arr[1]*10+arr[2]*0;
	}
	private int[] add(int input) {
		action=input;
		data.get(currentGame).add(g.generateArr(action));
		actionTaken=true;
		int[] arr ={0,0,0};
		data.get(currentGame).add(new int[3]);
		String temp = s.nextLine().substring("Fermi: ".length());
		data.get(currentGame).get(1)[0]=(int)temp.charAt(0);
		arr[0]=(int)temp.charAt(0);
		temp= temp.substring(" Pico: ".length()+1);
		data.get(currentGame).get(1)[1]=(int)temp.charAt(0);
		arr[1]=(int)temp.charAt(0);
		temp= temp.substring(" Bagel: ".length()+1);
		data.get(currentGame).get(1)[2]=(int)temp.charAt(0);
		arr[2]=(int)temp.charAt(0);
		return arr;
	}
	private int selectAction( int[] state ) {

		double[] qValues = getQValuesAt( state );
		int selectedAction = -1;
		int action;
	    double prob[] = new double[ qValues.length ];
	    double sumProb = 0;
	    
	    for( action = 0 ; action < qValues.length ; action++ ) {
		prob[action] = Math.exp( qValues[action] / temp );
		sumProb += prob[action];
	    }
	    for( action = 0 ; action < qValues.length ; action++ )
		prob[action] = prob[action] / sumProb;
	    
	    double rndValue;
	    double offset;
		
		rndValue = Math.random();
		offset = 0;
		
		for( action = 0 ; action < qValues.length ; action++ ) {
		    if( rndValue > offset && rndValue < offset + prob[action] )
			selectedAction = action;
		    offset += prob[action];
		    // System.out.println( "Action " + action + " chosen with " + prob[action] );
		}
	    

	}
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(actionTaken){
			cb.append((char)action);
			actionTaken=false;
		}
		return 0;
	}
}
