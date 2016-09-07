import java.util.*;
public class roundRobin {
	static class program{
		public int burstTime;
		public int pid;
		public boolean executing;
		boolean robin;
		public boolean executed;
		public int arrivalTime;
		int waitingTime;
		public int setBurstTime(){
			burstTime=(int)(Math.random()*10+1);
			return burstTime;
		}
		public int setArrivalTime(int x){
			arrivalTime=(int)(x+1+Math.random()*10);
			//Crespoters code
			return arrivalTime;
		}
		public void print(){
			System.out.println(pid+"\t"+arrivalTime+"\t\t"+burstTime);
		}
		program(int x){
			robin=false;
			waitingTime=0;
			executing=false;
			executed=false;
			pid=x;
		}
	}
static void updateWaitingTime(program[] programs,int endTime){
		
		for(int i=0;i<10;i++)
		{
			//Crespoters code
			if(programs[i].arrivalTime>endTime){
				break;
			}
			if(!(programs[i].executed||programs[i].executing))
			{

				programs[i].waitingTime++;
			}
		}
		
	}
	static boolean allFinished(program[] programs){
		for(int i=0;i<10;i++){
			if(programs[i].executed==false){
				return false;
				//Crespoters code
			}
		}
		return true;
	}
	public static void main(String args[]){
		Queue robins=new LinkedList();
		int t=0;
		int quantumTime=2;//Change this to change the quantum time
		program[] programs=new program[10];
		for(int i=0;i<10;i++){
			programs[i]=new program(i);
		}
		System.out.println("Generating 10 programs with Quantum time of 2 seconds\n");
		System.out.println("P.id\tArrivalTime\tBurstTime");
		for(int i=0;i<10;i++)
		{
			programs[i].setBurstTime();
			//Crespoters code
			t=programs[i].setArrivalTime(t);
			programs[i].print();
		}
		System.out.println("Time\tExecutingPid");
		int executing=0;
		for(int time=0;!allFinished(programs);)
		{
			for(int i=0;i<10;i++){
				if(programs[i].arrivalTime<=time && !programs[i].robin){
					robins.add(i);
					programs[i].robin=true;
				}
			}
			if(robins.isEmpty()){
				System.out.println(time+"\t\t"+"-");
				time++;
				continue;
			}
			executing=(int)robins.element();
			robins.remove();
			for(int i=0;i<quantumTime;i++){
				if(programs[executing].burstTime!=0)
				{
					System.out.println(time+"\t\t"+executing);
					programs[executing].burstTime--;
					
				}
				else
				{
					System.out.println(time+"\t\t"+"blank");
				}
				updateWaitingTime(programs,time);
				time++;
			}
			if(programs[executing].burstTime==0){
				programs[executing].executed=true;
			}
			else
			{
				robins.add(executing);
			}
				
		}
		System.out.println("P.Id\tWaitingTime");
		for(int i=0;i<10;i++){
			System.out.println(i+"\t\t"+programs[i].waitingTime);
		}
	}
}
