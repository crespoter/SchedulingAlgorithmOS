import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class AssignmentOSIntegrated{
	JFrame f;
	AssignmentOSIntegrated(program[] p,int counter){
		if(counter==0)
		{
			f=new JFrame();  
		    f.setTitle("Inputs");
		    String[][] data=new String[100][];
		    for(int i=0;i<100;i++){
		    	data[i]=new String[4];
		    }
		    String column[]={"P.ID","Arrival Time","Burst Time","Priority"};  
		    for(int i=0;i<10;i++){
		    	data[i][0]=Integer.toString(p[i].pid);
		    	data[i][1]=Integer.toString(p[i].arrivalTime);
		    	data[i][2]=Integer.toString(p[i].burstTime);
		    	data[i][3]=Integer.toString(p[i].priority);
		    }
		    
		      
		    JTable jt=new JTable(data,column);  
		    jt.setBounds(30,40,200,300);  
		      
		    JScrollPane sp=new JScrollPane(jt);  
		    f.add(sp);  
		      
		    f.setSize(300,400);  
		//  f.setLayout(null);  
		    f.setVisible(true);  
			
		}
		else{
			
			f=new JFrame();  
			if(counter==1)
			{
				f.setTitle("Preemptive Burst");
			}
			else if(counter==2)
			{
				f.setTitle("Round Robin");
			}
			else if(counter==3)
			{
				f.setTitle("Non Preemptive Burst");
			}
			
			else if(counter==4)
			{
				f.setTitle("First Come First Serve");
			}
			else if(counter==5)
			{
				f.setTitle("Non Preemptive Priority");
			}
			else
			{
				f.setTitle("Preemtive Priority");
			}  
		    String[][] data=new String[100][];
		    for(int i=0;i<100;i++){
		    	data[i]=new String[2];
		    }
		    String column[]={"P.ID","Waiting Time"};  
		    for(int i=0;i<10;i++){
		    	data[i][0]=Integer.toString(p[i].pid);
		    	data[i][1]=Integer.toString(p[i].waitingTime);
		    }
		    
		      
		    JTable jt=new JTable(data,column);  
		    jt.setBounds(30,40,200,300);  
		      
		    JScrollPane sp=new JScrollPane(jt);  
		    f.add(sp);  
		      
		    f.setSize(300,400);  
		//  f.setLayout(null);  
		    f.setVisible(true);  
	
		}
		
	}
	

		static class program{
			public int burstTime;
			public int pid;
			public boolean executing;
			public boolean executed;
			public boolean robin;
			public int arrivalTime;
			public int priority;
			public int waitingTime;
			public int setBurstTime(){
				burstTime=(int)(Math.random()*10+1);
				return burstTime;
			}
			public int setPriority(){
				priority=(int)(Math.random()*10);
				return priority;
			}
			public int setArrivalTime(int x){
				arrivalTime=(int)(x+1+Math.random()*5);
				//Crespoters code
				return arrivalTime;
			}
			public void print(){
				System.out.println(pid+"\t"+arrivalTime+"\t\t"+burstTime+"\t\t"+priority);
			}
			program(int x){
				
				waitingTime=0;
				executing=false;
				priority=0;
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
			
			//PREEMTIVE BURST TIME 
			
			
			
			
			int burstTimeSaver[]=new int[10];
			int t=0;
			program[] programs=new program[10];
			for(int i=0;i<10;i++){
				programs[i]=new program(i);
			}
			System.out.println("\n*******Preemptive on Burst Time**********\n\n\nGenerating 10 programs\n");
			System.out.println("P.id\tArrivalTime\tBurstTime");
			for(int i=0;i<10;i++)
			{
				programs[i].setBurstTime();
				burstTimeSaver[i]=programs[i].burstTime;
				//Crespoters code
				t=programs[i].setArrivalTime(t);
				programs[i].setPriority();
				programs[i].print();
			}
			 new AssignmentOSIntegrated(programs,0); 
			System.out.println("Time\tExecutingPid");
			int executing=0;
			for(int time=0;!allFinished(programs);){
				int minBurstIndex=-1;
				for(int i=0;i<10&&programs[i].arrivalTime<=time;i++){
					if(!programs[i].executed){
						if(minBurstIndex==-1){
							minBurstIndex=i;
						}
						else if(programs[minBurstIndex].burstTime>programs[i].burstTime){
							minBurstIndex=i;
						}
					}
				}
				executing=minBurstIndex;
				if(executing==-1){
					System.out.println(time+"\t\t"+"-");
					time++;
					continue;
				}
				if(programs[executing].burstTime==0){
					programs[executing].executed=true;
					continue;
				}
				System.out.println(time+"\t\t"+executing);
				programs[executing].burstTime--;
				updateWaitingTime(programs,time);
				time++;
			}
			System.out.println("Waiting Time\nPid\tWaiting Time");
			for(int i=0;i<10;i++){
				System.out.println(i+"\t\t"+programs[i].waitingTime);
			}
			
			
			new AssignmentOSIntegrated(programs,1);
			//ROUND ROBIN
			
			
			
			
			
			
			
			
			System.out.println("\n\n*********Round Robin***********\n\n");
			
			for(int i=0;i<10;i++){
				programs[i].burstTime=burstTimeSaver[i];
				programs[i].waitingTime=0;
				programs[i].executing=false;
				programs[i].executed=false;
				
			}
			int quantumTime=2;//Change this to change the quantum time
			System.out.println("Generating 10 programs with Quantum time of 2 seconds\n");
			System.out.println("P.id\tArrivalTime\tBurstTime");
			Queue robins=new LinkedList();
			for(int i=0;i<10;i++){
				programs[i].print();
			}
			System.out.println("Time\tExecutingPid");
			executing=0;
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
			
			
			new AssignmentOSIntegrated(programs,2);
			
			
			//NON PREEMTIVE BURST TIME
			
			
			
			
			
			System.out.println("\n\n*********NonPreemtive Burst Time***********\n\n");
			
			for(int i=0;i<10;i++){
				programs[i].burstTime=burstTimeSaver[i];
				programs[i].waitingTime=0;
				programs[i].executing=false;
				programs[i].executed=false;
				
			}
			System.out.println("Generating 10 programs\n");
			System.out.println("P.id\tArrivalTime\tBurstTime");
			for(int i=0;i<10;i++){
				programs[i].print();
			}
			System.out.println("Time\tExecutingPid");
			executing=0;
			for(int time=0;!allFinished(programs);){
				int minBurstIndex=-1;
				for(int i=0;i<10&&programs[i].arrivalTime<=time;i++){
					if(!programs[i].executed){
						if(minBurstIndex==-1){
							minBurstIndex=i;
						}
						else if(programs[minBurstIndex].burstTime>programs[i].burstTime){
							minBurstIndex=i;
						}
					}
				}
				executing=minBurstIndex;
				if(executing==-1){
					System.out.println(time+"\t\t"+"-");
					time++;
					continue;
				}
				for(int i=0;i<programs[executing].burstTime;i++){
					programs[executing].executing=true;
					updateWaitingTime(programs,time);
					System.out.println(time+"\t\t"+executing);
					time++;
				}
				programs[executing].executing=false;
				programs[executing].executed=true;
				
			}
			System.out.println("P.Id\tWaitingTime");
			for(int i=0;i<10;i++){
				System.out.println(i+"\t\t"+programs[i].waitingTime);
			}
		
			
			
			new AssignmentOSIntegrated(programs,3);
			
			
			
		//FIRST COME FIRST SERVE
			
			
			
			
			
			
			
		System.out.println("\n\n*********First Come First Serve***********\n\n");
			
		for(int i=0;i<10;i++){
			programs[i].burstTime=burstTimeSaver[i];
			programs[i].waitingTime=0;
			programs[i].executing=false;
			programs[i].executed=false;
			
		}
		System.out.println("Generating 10 programs\n");
		System.out.println("P.id\tArrivalTime\tBurstTime");
		for(int i=0;i<10;i++){
			programs[i].print();
		}
		System.out.println("Time\tExecutingPid");
		executing=0;
		for(int time=0;!allFinished(programs);)
		{
			while(time<programs[executing].arrivalTime){
				System.out.println(time+"\t\t"+"-");
				//Crespoters code
				updateWaitingTime(programs,time);
				time++;
			}
			for(int i=0;i<programs[executing].burstTime;i++){
				System.out.println((time+i)+"\t\t"+executing);
				programs[executing].executing=true;
				updateWaitingTime(programs,time);
				time++;
			}
			programs[executing].executing=false;
			programs[executing].executed=true;
			executing++;
			
		}
		System.out.println("Pid\tWaiting Time");
		for(int i=0;i<10;i++){
			System.out.println(i+"\t\t"+programs[i].waitingTime);
		}
		
		
		
		
		new AssignmentOSIntegrated(programs,4);
		
		//NON PREEMPTIVE PRIORITY
		
		
		
		
		
		
		System.out.println("\n\n*********Non Preemptive Priority***********\n\n");
			
		for(int i=0;i<10;i++){
			programs[i].burstTime=burstTimeSaver[i];
			programs[i].waitingTime=0;
			programs[i].executing=false;
			programs[i].executed=false;
			
		}
		System.out.println("Generating 10 programs\n");
		System.out.println("P.id\tArrivalTime\tBurstTime\tPriority");
		for(int i=0;i<10;i++)
		{
			//Crespoters code
			programs[i].print();
		}
		System.out.println("Time\tExecutingPid");
		executing=0;
		for(int time=0;!allFinished(programs);){
			int minPriorityIndex=-1;
			for(int i=0;i<10&&programs[i].arrivalTime<=time;i++){
				if(!programs[i].executed){
					if(minPriorityIndex==-1){
						minPriorityIndex=i;
					}
					else if(programs[minPriorityIndex].priority>programs[i].priority){
						minPriorityIndex=i;
					}
				}
			}
			executing=minPriorityIndex;
			if(executing==-1){
				System.out.println(time+"\t\t"+"-");
				time++;
				continue;
			}
			for(int i=0;i<programs[executing].burstTime;i++){
				programs[executing].executing=true;
				updateWaitingTime(programs,time);
				System.out.println(time+"\t\t"+executing);
				time++;
			}
			programs[executing].executing=false;
			programs[executing].executed=true;
		}
		System.out.println("P.Id\tWaitingTime");
		for(int i=0;i<10;i++){
			System.out.println(i+"\t\t"+programs[i].waitingTime);
		}
		
		
		
		new AssignmentOSIntegrated(programs,5);
		
		//PREEMTIVE PRIORITY
		
		
		
		
		
		
		System.out.println("\n\n*********Preemptive Priority***********\n\n");
		
		System.out.println("Generating 10 programs\n");
		System.out.println("P.id\tArrivalTime\tBurstTime\tPriority");
		for(int i=0;i<10;i++)
		{
			//Crespoters code
			programs[i].print();
		}
		System.out.println("Time\tExecutingPid");
		executing=0;

		for(int time=0;!allFinished(programs);){
			int minPriorityIndex=-1;
			for(int i=0;i<10&&programs[i].arrivalTime<=time;i++){
				if(!programs[i].executed){
					if(minPriorityIndex==-1){
						minPriorityIndex=i;
					}
					else if(programs[minPriorityIndex].priority>programs[i].priority){
						minPriorityIndex=i;
					}
				}
			}
			executing=minPriorityIndex;
			if(executing==-1){
				System.out.println(time+"\t\t"+"-");
				time++;
				continue;
			}
			if(programs[executing].burstTime==0){
				programs[executing].executed=true;
				continue;
			}
			updateWaitingTime(programs,time);
			System.out.println(time+"\t\t"+executing);
			programs[executing].burstTime--;
			time++;
		}
		System.out.println("P.Id\tWaitingTime");
		for(int i=0;i<10;i++){
			System.out.println(i+"\t\t"+programs[i].waitingTime);
		}

		new AssignmentOSIntegrated(programs,6);
		
			
	}
}



