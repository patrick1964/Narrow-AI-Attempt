package game;
import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Game g=new Game();
		g.play(4);
	}
	Scanner s;
	public Game(){
		s= new Scanner(System.in);
	}
	public Game(Ai a){
		s= new Scanner(a);
	}
	public void play(int temp){
		int numD=temp;
		String guess;
		int num[]=generateNum(numD);
		int output[]= new int[3];
		int counter=0;
		
		while(output[0]!=numD){
			System.out.print("enter a guess of "+temp+" digits: ");
			guess=s.nextLine();
			if(guess.length()==numD){
				output=results(generateArr(guess),num);
				System.out.println("Fermi: "+output[0]+" Pico: "+output[1]+" Bagel: "+output[2]);
				counter++;
			}else{
				for(int i=0;i<numD;i++){
					System.out.print(num[i]);
				}
				break;
			}
			
		}
		System.out.println("you win and it took you "+counter+" tries");
		s.close();
	}
	private int[] generateArr(String guess) {
		int arr[]= new int[guess.length()];
		for(int i=0;i<guess.length();i++){
			arr[i]=guess.charAt(i)-48;
		}
		return arr;
	}
	private int[] generateNum(int  numD){
		Random r = new Random();
		int arr[]=new int[numD];
		for(int i=0;i<numD;i++){
			do{
				arr[i]=r.nextInt()%10;
			}while(arr[i]<0);
		}
		return arr;
	}
	public int[] generateArr(int num){
		int numD=(int)Math.floor(Math.log10(num))+1;
		int arr[]=new int[numD];
		for(int i=numD-1;i>=0;i--){
			int temp=(int)Math.pow(10, i);
			arr[numD-1-i]=(num-(num%temp))/temp;
			num=num-(num-(num%temp));
		}
		return arr;
	}
	public int[] results(int[] guess, int[] truenum){
		int arr[]= {0,0,0};
		for(int i=0;i<guess.length;i++){
			if(guess[i]==truenum[i]){
				arr[0]++;
			}else if(search(truenum, guess[i])){
				arr[1]++;
			}else{
				arr[2]++;
			}
		}
		return arr;
	}
	private boolean search(int[] truenum, int guess) {
		for(int i=0;i<truenum.length;i++){
			if(truenum[i]==guess){
				return true;
			}
		}
		return false;
	}
}
