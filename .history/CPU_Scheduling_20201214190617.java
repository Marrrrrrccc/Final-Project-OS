
import java.util.*;
 
public class CPU_Scheduling {
	static Scanner input=new Scanner(System.in);
	static public final int  N = 100;
	static int queue[] = new int[N];
	static int front = 0, rear = 0;
	static int n = 999;
	static int processId[] = new int[n]; // it takes the process ID
	static int priority[] = new int[n];
	static int arrivalTime[] = new int[n]; //  arrival time
	static int burstTime[] = new int[n]; //  burst time
	static int flag[] = new int[n];  //  it checks process is completed or not
    static int[] tempBurstTime = new int[n];// temporary burst time variable
	static int completionTime[] = new int[n]; // completion time
	static int turnAroundTime[] = new int[n];//turn around time
	static int waitingTime[] = new int[n];  //waiting time
	static float averageWaitingTime=0, averageTurnAroundTime=0;
	static String choice;
	static int quantum;
	static int overhead;
	static int remaining_time[] = new int[n];
    static int[] q;
    static int head = -1, tail = -1;
    static int time = 0;
    static boolean[] arrived = new boolean[n]; 
    static int notArrived = 0;
	static int[] startTime = new int[n];
	static int q1[]= new int[n];
	static int q1arrivalTime[] = new int[n];
	static int q1burstTime[] = new int[n];
	static int q1remainingTime[] = new int[n];
	static int q1waitingTime[] = new int[n];
	static int q1turnAroundTime[] = new int[n];
	static char q1name[] = new char[n];
	static int q2[]= new int[n];
	static int q2arrivalTime[] = new int[n];
	static int q2burstTime[] = new int[n];
	static int q2remainingTime[] = new int[n];
	static int q2waitingTime[] = new int[n];
	static int q2turnAroundTime[] = new int[n];
	static char q2name[] = new char[n];
	static int q3[]= new int[n];
	static int q3arrivalTime[] = new int[n];
	static int q3burstTime[] = new int[n];
	static int q3remainingTime[] = new int[n];
	static int q3waitingTime[] = new int[n];
	static int q3turnAroundTime[] = new int[n];
	static char q3name[] = new char[n];
	static int q3completionTime[] = new int[n];

    static boolean[] done = new boolean[n];
	static String breaker = "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
	static String breaker2 = "* * * * * * * * * * * * * * * * * * * * * * * * * * *";
	
	static void zeroVariables(){
		n=99;
		processId = new int[n];
		 arrivalTime = new int[n]; //  arrival time
		 burstTime = new int[n]; //  burst time
		 flag = new int[n];  //  it checks process is completed or not
		 tempBurstTime= new int[n];   // temporary burst time variable
		 completionTime = new int[n]; // completion time
		 turnAroundTime = new int[n];//turn around time
		 waitingTime = new int[n];  //waiting time
		 priority = new int[n];
		startTime = new int[n];
		remaining_time = new int[n];
		   done = new boolean[n];
		   arrived = new boolean[n];
		   tempBurstTime = new int[n];
		   q = new int[n];
		   queue = new int[N];
		 averageWaitingTime=0;
		averageTurnAroundTime=0;
		quantum = 0;
		overhead = 0;
		notArrived = 0;
		time = 0;
		head = -1;
		tail = -1;
		front = 0; 
		rear = 0;
	}
	
	static void thankYou() {
		System.out.println("\n\n"+ breaker2 +"\n*\t   Thank you for using our program.\t    *\n*\t   "
				+ "Made by:\t\t\t\t    *\n*\t      Marc Ricafort\t\t\t    *\n*\t      Mhargarette Basbas\t\t    "
				+ "*\n*\t      Stanley Orong\t\t\t    *\n*\t      Patricia Valenzuela\t\t    *\n*\t      "
				+ "Cyril Verdad\t\t\t    *\n" + breaker2 );
	}
	
	static void ShortestRemainingTimeFirst()
	{
		int i;
		int st=0, tot=0;
		
	    while(true){
	    	int min=99,c=n;
	    	if (tot==n)
	    		break;
	    	
	    	for ( i=0;i<n;i++)
	    	{
	    		if ((arrivalTime[i]<=st) && (flag[i]==0) && (burstTime[i]<min))
	    		{	
	    			min=burstTime[i];
	    			c=i;
	    		}
	    	}
	    	
	    	if (c==n)
	    		st++;
	    	else
	    	{
	    		burstTime[c]--;
	    		st++;
	    		if (burstTime[c]==0)
	    		{
	    			completionTime[c]= st;
	    			flag[c]=1;
	    			tot++;
	    		}
	    	}
	    }
	    
	    for(i=0;i<n;i++)
	    {
	    	turnAroundTime[i] = completionTime[i] - arrivalTime[i];
	    	waitingTime[i] = turnAroundTime[i] - tempBurstTime[i];
	    	averageWaitingTime+= waitingTime[i];
	    	averageTurnAroundTime+= turnAroundTime[i];
	    }
	    
	    System.out.println(breaker + "\nProcesses | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

        for (i = 0; i < n; i++) {
           

            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + tempBurstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
        }
	    System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/n));
	  
	    
	    System.out.println("Average Turnaround time: "+ (averageTurnAroundTime/n));
	  
	}
	static void push(int process_id)
	{
		queue[rear] = process_id;
		rear = (rear+1)%N;
	}
	
	static int pop()
	{
		if(front == rear)
			return -1;

		int return_position = queue[front];
		front = (front +1)%N;
		return return_position;
	}
	
	static void RR(){
		float wait_time_total = 0.0f, tat = 0.0f;
		
		for(int i=0; i<n; i++)
		{
			processId[i] = i+1;
			remaining_time[i] = burstTime[i];
		}
		
		int caseFail1 = 0;
		
		//do while to catch exception from invalid input
		do {
			try {
				System.out.print("Enter time quantum: ");
				quantum = input.nextInt();
				caseFail1 = 1;
			} catch (Exception e) {
				System.out.println("Invalid Input. Please enter an Integer only.\n");
				input.next();
			}
		} while (caseFail1 != 1);

		int time=0; 
		int processes_left=n;   
		int position=-1; 		
		int local_time=0; 

		for(int j=0; j<n; j++)
			if(arrivalTime[j] == time)
				push(j);

		while(processes_left != 0)
		{
			if(local_time == 0) 
			{
				if(position != -1)
					push(position);

				position = pop();
			}

			for(int i=0; i<n; i++)
			{
				if(arrivalTime[i] > time)
					continue;
				if(i==position)
					continue;
				if(remaining_time[i] == 0)
					continue;

				waitingTime[i]++;
				turnAroundTime[i]++;
			}

			if(position != -1)
			{
				remaining_time[position]--;
				turnAroundTime[position]++;
				
				if(remaining_time[position] == 0)
				{
					processes_left--;
					local_time = -1;
					position = -1;
				}
			}
			else
				local_time = -1; 

			time++;
			local_time = (local_time +1)%quantum;
			for(int j=0; j<n; j++)
				if(arrivalTime[j] == time)	
					push(j);
		}
		for(int i=0; i<n; i++)
		{

			tat += turnAroundTime[i];
			wait_time_total += waitingTime[i];
		}

		tat = (float) (tat/(1.0*n));
		wait_time_total = (float) (wait_time_total/(1.0*n));

		System.out.print("\n");

		
	      System.out.println(breaker + "\nProcesses | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

	        for (int i = 0; i < n; i++) {
	            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
	        }
		    
		    System.out.println("\nAverage waiting time = " + 
		    		wait_time_total );

	          
	        System.out.println("Average turn around time = " + 
	        		tat); 
		
	}
	static void rro(){
	          
		int caseFail1 = 0;
			
		//do while to catch exception from invalid input
		do {
		    try {
				System.out.print("Input quantum time: ");  
				quantum = input.nextInt();
				caseFail1 = 1;
			} catch (Exception e) {
				System.out.println("Invalid Input. Please enter an Integer only.\n");
				input.next();
			}
		} while (caseFail1 != 1);
	    
		int caseFail2 = 0;
		//do while to catch exception from invalid input
		do {
			try {
			System.out.print("Input overhead: ");  
			overhead = input.nextInt(); 
			caseFail2 = 1;
			} catch (Exception e) {
				System.out.println("Invalid Input. Please enter an Integer only.\n");
				input.next();
			}	
		} while (caseFail2 != 1);
		q = new int[n]; 
		for (int donePs = 0; donePs < n;) {    
			if (notArrived!=9)
				checkArrived();
			if (head!=-1) {  
				int i = deq();   
				
				if (startTime[i]==0) { 
					startTime[i] = time;
				}
				for (int qtc = 0; qtc < quantum; qtc++) { 
					++time;
					--tempBurstTime[i]; 
					if (notArrived!=9)
						checkArrived(); 
					if (done[i] = tempBurstTime[i] == 0) {  
						donePs++; 
						completionTime[i] = time;   
						break;
					}   
				}  
				if (!done[i])
					enq(i);   
				
				for (int otc = 0; otc < overhead && n!=donePs; otc++) {
					++time; 
					if (notArrived!=9)
						checkArrived();
				}  
			} 
			else {
				time++;  
			} 
		}

		float averageWaitingTime = 0, averageTurnAroundTime = 0;
		for (int i = 0; i < n; i++) 
		{
			turnAroundTime[i] = completionTime[i] - arrivalTime[i];
			waitingTime[i] = turnAroundTime[i] - burstTime[i];
			averageWaitingTime += waitingTime[i];
			averageTurnAroundTime += turnAroundTime[i];
		}  

		

		  System.out.println("\n" + breaker + "\nProcesses | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

			for (int i = 0; i < n; i++) {
				System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
			}
			averageWaitingTime /= n;
		System.out.println("\nAverage waiting time: " + averageWaitingTime);  
		averageTurnAroundTime /= n;
		System.out.println("Average turn around time: " + averageTurnAroundTime); 

	   
	}
	private static void checkArrived() {
		for (int i = 0; i < n; i++) {  
			if (!arrived[i]) { 
				if (arrived[i] = arrivalTime[i] <= time)  {
					enq(i); 
					notArrived--; 
				} 
			} 
		}   
	}
	private static int deq() {
		int x = q[head];

		if (head == tail) 
			head = tail = -1; 
		else 
			head = (head+1) % n;
		return x;
	}
	private static void enq(int x) {
		if (head == -1) 
			head = 0;
		tail = (tail+1) % n;
		q[tail] = x; 
	}
	public static void pPrio(){
		int x[] = new int[n];
		int i;
		int smallest;
		int count = 0;
		int time;
		int end;
		int caseFail1 = 0;
		
		do {
			try {
				System.out.print("Input corresponding process priorities\n");
				for(i = 0; i<n;i++){
					System.out.print(" Enter Priority "+ (i+1) + ": ");
					priority[i]  = input.nextInt();
					if (i+1 >= n) {
						caseFail1 = 1;
					}
				}
			} catch (Exception e) {
				System.out.println(" Invalid Input. Please enter integers only.\n");
				input.next();
			}
		} while (caseFail1 != 1);

	    priority[9]=-1;
	    for(time=0; count!=n; time++)
	    {
	        smallest=9;
	        for(i=0; i<n; i++)
	        {
	            if(arrivalTime[i]<=time && priority[i]>priority[smallest] && burstTime[i]>0 )
	                smallest=i;
	        }
	        burstTime[smallest]--;
	        if(burstTime[smallest]==0)
	        {
	            count++;
	            end=time+1;
	            completionTime[smallest] = end;
	        }
	    }
	    for(i=0;i<n;i++)
	    {
	    	turnAroundTime[i] = completionTime[i] - arrivalTime[i];
	    	waitingTime[i] = turnAroundTime[i] - tempBurstTime[i];
	    	averageWaitingTime+= waitingTime[i];
	    	averageTurnAroundTime+= turnAroundTime[i];
	    }
	    
	   
	    
	    System.out.println("\n" + breaker + "\nProcesses | " + "Arrival Time | " + "Burst Time  " + "  |   Priority "
	    		+ "| " + "Turn Around Time | " + "Waiting Time");

        for (i = 0; i < n; i++) {
           

            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + tempBurstTime[i] +"\t\t" + priority[i] +  "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
        }
	    
	    System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/n));
	    System.out.println("Average Turnaround time: "+ (averageTurnAroundTime/n));
	    
	        
	
	}
	static void fcfs(){
		int temp;
		for(int i = 0 ; i <n; i++)
		{
			for(int  j=0;  j < n-(i+1) ; j++)
			{
				if( arrivalTime[j] > arrivalTime[j+1] )
				{
					temp = arrivalTime[j];
					arrivalTime[j] = arrivalTime[j+1];
					arrivalTime[j+1] = temp;
					temp = burstTime[j];
					burstTime[j] = burstTime[j+1];
					burstTime[j+1] = temp;
					temp = processId[j];
					processId[j] = processId[j+1];
					processId[j+1] = temp;
				}
			}
		}
		
		// finding completion times
		for(int  i = 0 ; i < n; i++)
		{
			if( i == 0)
			{	
				completionTime[i] = arrivalTime[i] + burstTime[i];
			}
			else
			{
				if( arrivalTime[i] > completionTime[i-1])
				{
					completionTime[i] = completionTime[i] + burstTime[i];
				}
				else
					completionTime[i] = completionTime[i-1] + burstTime[i];
			}
			turnAroundTime[i] = completionTime[i] - arrivalTime[i] ;          // turnaround time= completion time- arrival time
			waitingTime[i] = turnAroundTime[i] - burstTime[i] ;          // waiting time= turnaround time- burst time
			averageWaitingTime += waitingTime[i] ;               // total waiting time
			averageTurnAroundTime += turnAroundTime[i] ;               // total turnaround time
		}
		  System.out.println("Processes | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

	        for (int i = 0; i < n; i++) {
	           

	            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
	        }
		    System.out.println("Average Waiting Time: "+ (float)(averageWaitingTime/n));
		  
		    
		    System.out.println("Average Turnaround time: "+ (float)(averageTurnAroundTime/n));
		  
		}
	static void sjf(){
		boolean a = true;
		int st = 0, tot = 0;
		int f[] = new int[n];   // flag, it checks process is completed or not
        while (a) {
            int c = n, min = 999;
            if (tot == n) break; // total no of process = completed process loop will be terminated            
            
            for (int i=0; i<n; i++) {
                /*
                * If i'th process arrival time <= system time and its flag = 0 and burst < min 
                * That process will be executed first 
                */ 
                if ((arrivalTime[i] <= st) && (f[i] == 0) && (burstTime[i]<min)) {
                    min=burstTime[i];
                    c=i;
                }
            }
            
            /* If c == n means c value can not updated because no process arrival time < system time so we increase the system time */
            if (c == n) st++;
            else {
                completionTime[c] = st + burstTime[c];
                st += burstTime[c];
                turnAroundTime[c] = completionTime[c] - arrivalTime[c];
                waitingTime[c] = turnAroundTime[c] - burstTime[c];
                f[c] = 1;
                tot++;
            }
            
        }
        
        System.out.println("Processes | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

        for (int i = 0; i < n; i++) {
        	averageWaitingTime += waitingTime[i];
            averageTurnAroundTime += turnAroundTime[i];
           

            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
        }
	    System.out.println("Average Waiting Time: "+ (float)(averageWaitingTime/n));
	  
	    
	    System.out.println("Average Turnaround time: "+ (float)(averageTurnAroundTime/n));
	}
	static void sortByArrival()
	{
	int temp;
	int i,j;
	for(i=0;i<n;i++)
	    {
	        for(j=i+1;j<n;j++)
	            {
	                if(arrivalTime[i]>arrivalTime[j])
	                    {
	                          temp=q1[i];
	                          q1[i]=q1[j];
	                          q1[j]=temp;
	                    }
	            }
	    }
	}

	static void mlfq()
	{
		System.out.print("Insert number of levels: ");
		int levels = input.nextInt();
		System.out.println("Input algorithm for each levels ");
		for(int i = 0 ; i<levels;i++){
			System.out.print("Input algorithm for level "+(i+1)+":");
			int algo = input.nextInt();
		}
	     int i,j,k=0,r=0,time=0,tq1=5,tq2=8,flag=0;
	     char c;
	     char name;
	     for(i=0,c='A';i<n;i++,c++)
	     {
	    	 q1arrivalTime[i] = arrivalTime[i];
	         q1name[i]=c;
	         q1remainingTime[i]=q1burstTime[i];/*save burst time in remaining time for each process*/

	    }
	sortByArrival();
	time=q1arrivalTime[0];

	for(i=0;i<n;i++)
	{

	  if(q1remainingTime[i]<=tq1)
	  {

	       time+=q1remainingTime[i];/*from arrival time of first process to completion of this process*/
	       q1remainingTime[i]=0;
	       q1waitingTime[i]=time-q1arrivalTime[i]-q1burstTime[i];/*amount of time process has been waiting in the first queue*/
	       q1turnAroundTime[i]=time-q1arrivalTime[i];/*amount of time to execute the process*/
	       System.out.println(q1name[i]+q1burstTime[i]+q1waitingTime[i]+q1turnAroundTime[i]);
	       
	  }
	  else/*process moves to queue 2 with qt=8*/
	  {
	      q2remainingTime[k]=time;
	      time+=tq1;
	      q1remainingTime[i]-=tq1;
	      q2burstTime=q1remainingTime;
	      q2remainingTime[k]=q2burstTime[k];
	      q2name[k]=q1name[i];
	      k=k+1;
	      flag=1;
	   }
	}
	if(flag==1){

	}for(i=0;i<k;i++)
	   {
	    if(q2remainingTime[i]<=tq2)
	     {
	       time+=q2remainingTime[i];/*from arrival time of first process +BT of this process*/
	       q2remainingTime[i]=0;
	       q2waitingTime[i]=time-tq1-q2burstTime[i];/*amount of time process has been waiting in the ready queue*/
	       q2turnAroundTime[i]=time-q2arrivalTime[i];/*amount of time to execute the process*/
	       

	    }
	    else/*process moves to queue 3 with FCFS*/
	    {
	      q3arrivalTime[r]=time;
	      time+=tq2;
	      q2remainingTime[i]-=tq2;
	      q3burstTime[r]=q2remainingTime[i];
	      q3remainingTime[r]=q3burstTime[r];
	      q3name[r]=q2name[r];
	      r=r+1;
	      flag=2;
	    }
	  }

	{if(flag==2)
	
	
	for(i=0;i<r;i++)
	{
	    if(i==0)
	            q3completionTime[i]=q3burstTime[i]+time-tq1-tq2;
	        else
	        	q3completionTime[i]=q3completionTime[i-1]+q3burstTime[i];

	}

	for(i=0;i<r;i++)
	    {
	        q3turnAroundTime[i]=q3completionTime[i];
	        q3waitingTime[i]=q3turnAroundTime[i]-q3burstTime[i];
	        

	    }

	}
	}
	
	public static void main (String args[]){
		while(true){
			int caseFail1 = 0;
					
			//do while to catch exception from invalid input
			do {
				try {
					System.out.print("\n" + breaker + "\n Input number of processes[2-9]: ");
					n = input.nextInt();
					caseFail1 = 1;
					
					if ( n < 2|| n > 9) {
						System.out.println(" Invalid Input. Please enter the required number of Processes.");
					}
				} catch (Exception e) {
					System.out.println(" Invalid Input. Please enter the required number of Processes.");
					input.next();
				}
			} while (caseFail1 != 1 || n < 2 || n > 9);
			
			int caseFail3 = 0;
			//do while to catch exception from invalid input
			do {
				try {
					System.out.print("\nInput the Arrival Time:\n");
				    for (int i=0;i<n;i++)
				    {
				    	processId[i]= i+1;
				    	System.out.print(" AT" +(i+1)+": ");
				    	arrivalTime[i]= input.nextInt();	
				    	if (i+1 >= n) {
				    		caseFail3 = 1;
				    	}
				    }
				} catch (Exception e) {
					System.out.println(" Invalid Input. Please enter integers only.");
					input.next();
				}
			} while (caseFail3 != 1);
			
			int caseFail4 = 0;
			//do while to catch exception from invalid input
			do {
				try {
				    System.out.print("\nInput the Burst Time:\n");
				    for(int a = 0;a<n;a++){
				    	System.out.print(" BT" +(a+1)+": ");
				    	burstTime[a]= input.nextInt();
				    	tempBurstTime[a]= burstTime[a];
				    	flag[a]= 0;
				    	if (a+1 >= n) {
				    		caseFail4 = 1;
				    	}
				    }
				} catch (Exception e) {
					System.out.println(" Invalid Input. Please enter integers only.");
					input.next();
				}
			} while (caseFail4 != 1);
			    

		    int caseFail2 = 0;
		    
		    //do while to catch exception from invalid input
		    do {
		    	
			    System.out.println("\n" + breaker + "\nChoose An Algorithm [A-E] or exit [F]\n[A] "
			    		+ "Shortest Remaining Time First (SRTF)\n[B] Round Robin (RR)\n[C] "
			    		+ "Round Robin with Overhead (RRO)\n[D] Preemptive Priority (P-Prio)\n"
			    		+ "[E] Multi-level Feedback Queue (MLFQ)\n[F] Exit");
			    
			    System.out.print("\nEnter choice: ");
			    String choice = input.next();
			    System.out.print("\n");
			
		    	switch(choice){
			    case "A":
			    case "a":
			    	caseFail2 = 1;
			    	ShortestRemainingTimeFirst();
			    	break;
			    case "B":
			    case "b":
			    	caseFail2 = 1;
			    	RR();
			    	break;
			    case "C":
			    case "c":
			    	caseFail2 = 1;
			    	rro();
			    	break;
			    case "D":
			    case "d":
			    	caseFail2 = 1;
			    	pPrio();//Highest Number Highest Priority
					break;
					case "e"
			    case "F":
			    case "f":
			    	caseFail2 = 1;
			    	thankYou();
			    	System.exit(0);
			    	break;
			    default:
			    	System.out.println("Please enter only letters from A to F");
			    }
		    } while (caseFail2 != 1); 
		    	
		    	System.out.print("\nInput another set?[y/n]:");
				 choice = input.next();
				if(choice.equalsIgnoreCase("y")){
					zeroVariables();
					continue;

				} else {
					thankYou();
					break;
				}
			
			}  
		}
}
		
	
