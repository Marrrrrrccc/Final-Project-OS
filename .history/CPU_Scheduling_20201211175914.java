
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
	static int temp[]= new int[n];   // temporary burst time variable
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
    static int[] tempBurstTime = new int[n];
    static boolean[] done = new boolean[n];
	// static int ct[][] = new int [2][n];
	static String breaker = "* * * * * * * * * * * * * * * * * * * * * * * * * * *";
	public static void ShortestRemainingTimeFirst()
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
	    	waitingTime[i] = turnAroundTime[i] - temp[i];
	    	averageWaitingTime+= waitingTime[i];
	    	averageTurnAroundTime+= turnAroundTime[i];
	    }
	    
	    System.out.println("Processes | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

        for (i = 0; i < n; i++) {
           

            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
        }
	    System.out.println("Average Waiting Time: "+ (float)(averageWaitingTime/n));
	  
	    
	    System.out.println("Average Turnaround time: "+ (float)(averageTurnAroundTime/n));
	  
	}

//	public static void RoundRobin(){
//		System.out.print("Enter Quantum Time:");
//		 quantum = input.nextInt();
//		
//		
//	       int rem_time[] = new int[n];
//	       int wt_time[] = new int[n];
//	        for(int i=0;i<wt_time.length;i++){
//	            rem_time[i]= burstTime[i];
//	        }
//	        int t=0;
//	        int arrival=0;
//	        // processing until the value of element of rem_time array is 0
//	        while(true){
//	            boolean done = true;
//	            for(int i=0;i<n;i++){
//	                if(rem_time[i]>0){
//	                     done =false;
//	                    if(rem_time[i]>quantum && arrivalTime[i]<=arrival){
//	                        t +=quantum;
//	                        rem_time[i]-=quantum;
//	                        arrival++;
//	                    }
//	                    else{
//	                    if(arrivalTime[i]<=arrival){
//	                        arrival++;
//	                        t+=rem_time[i];
//	                        rem_time[i]=0;
//	                        completionTime[i]=t; }
//	                    }
//	                 }
//	              }
//	             
//	            if(done==true)    
//	            { 
//	                break;
//	                }
//	        } 
//	        for(int i=0;i<n;i++){
//	            turnAroundTime[i]= completionTime[i]-arrivalTime[i];
//	            wt_time[i] = turnAroundTime[i]-burstTime[i];
//	             
//	             
//	        }
//	        
//	        int total_wt = 0;
//	        int total_tat = 0;
//	        for (int i=0; i<n; i++) 
//	        { 
//	            total_wt = total_wt + wt_time[i]; 
//	            total_tat = total_tat + turnAroundTime[i]; 
//	            
//	        } 
//	        System.out.println("Waiting Time");
//		    for(int i=0;i<n;i++)
//		    {
//		    	System.out.print("P" + processId[i] +": " +  wt_time[i] +"\n");
//		    }
//		    System.out.println("Average waiting time = " + 
//                    (float)total_wt / (float)n); 
//		    System.out.println();
//		    System.out.println("Turnaround Time");
//		    for(int i=0;i<n;i++)
//		    {
//		    	System.out.print("P" + processId[i] +": " + turnAroundTime[i] +"\n");
//		    }
//		    
//		    
//	        
//	       
//	        System.out.println("Average turn around time = " + 
//	                           (float)total_tat / (float)n); 
//	      }
//	
	public static void RoundRobinWithOverhead(){
		System.out.print("Enter Quantum Time:");
		 quantum = input.nextInt();
		System.out.print("Enter Overhead Time:");
		 overhead = input.nextInt();
		
	       int rem_time[] = new int[n];
	       int wt_time[] = new int[n];
	        for(int i=0;i<wt_time.length;i++){
	            rem_time[i]= burstTime[i];
	        }
	        int t=0;
	        int arrival=0;
	        // processing until the value of element of rem_time array is 0
	        while(true){
	            boolean done = true;
	            for(int i=0;i<n;i++){
	                if(rem_time[i]>0){
	                     done =false;
	                    if(rem_time[i]>quantum && arrivalTime[i]<=arrival){
	                    	t =(t+quantum)+overhead;
	                        rem_time[i]=(rem_time[i]-quantum)+overhead;
	                        arrival++;
	                    }
	                    else{
	                    if(arrivalTime[i]<=arrival){
	                        arrival++;
	                        t=(t+rem_time[i]);
	                        rem_time[i]=0 ;
	                        completionTime[i]=t;
	                        
	                        }
	                    }
	                 }
	              }
	            
	             
	            if(done==true)    
	            { 
	                break;
	                }
	        } 
	        for(int i=0;i<n;i++){
	            turnAroundTime[i]= completionTime[i]-arrivalTime[i] ;
	            wt_time[i] = turnAroundTime[i]-burstTime[i];
	             
	             
	        }
	        
	        int total_wt = 0;
	        int total_tat = 0;
	        for (int i=0; i<n; i++) 
	        { 
	            total_wt = total_wt + wt_time[i] ; 
	            total_tat = total_tat + turnAroundTime[i]; 
	            
	        } 
	        System.out.println("Waiting Time");
		    for(int i=0;i<n;i++)
		    {
		    	System.out.print("P" + processId[i] +": " +  wt_time[i] +"\n");
		    }
		    System.out.println("Average waiting time = " + 
                    (float)total_wt / (float)n); 
		    System.out.println();
		    System.out.println("Turnaround Time");
		    for(int i=0;i<n;i++)
		    {
		    	System.out.print("P" + processId[i] +": " + turnAroundTime[i] +"\n");
		    }
		    
		    
	        
	       
	        System.out.println("Average turn around time = " + 
	                           (float)total_tat / (float)n); 
		
		    
		}

	
//	public static void PPriority()
//	{
//		int i;
//		int st=0, tot=0;
//		int is_completed[] = new int[n];
//		
//		int burst_remianing[] = new int[n];
//		int start_time[] = new int[n];
//		int total_idle_time = 0;
//		int total_turnaround_time = 0;
//		int total_waiting_time = 0;
//	    float avg_turnaround_time = 0;
//	    float avg_waiting_time = 0;
//	    Arrays.fill(is_completed,-1);
//		for(i = 0; i<n;i++){
//			processId[i] = i + 1;
//			System.out.print("Enter Priority "+ (i+1) + ": ");
//			priority[i]  = input.nextInt();
//			burst_remianing[i]= burstTime[i];
//		}
//		
//		
//		int current_time = 0;
//	    int completed = 0;
//	    int prev = 0;
//	    while(true){
//	    	int min=99,c=n;
//	    	if (tot==n)
//	    		break;
//	    	
//	    	for ( i=0;i<n;i++)
//	    	{
//	    		if ((arrivalTime[i]<=st) && (flag[i]==0) && (priority[i]<min)){
//	    			min=burstTime[i];
//	    			c=i;
//	    			
//	    		
//	    				
//	    			
//	    		}
//	    		{	
//	    			
//	    		}
//	    	}
//	    	
//	    	if (c==n)
//	    		st++;
//	    	else
//	    	{
//	    		burstTime[c]--;
//	    		st++;
//	    		if (burstTime[c]==0)
//	    		{
//	    			completionTime[c]= st;
//	    			flag[c]=1;
//	    			tot++;
//	    		}
//	    	}
//	    }
//	    
//	    for(i=0;i<n;i++)
//	    {
//	    	turnAroundTime[i] = completionTime[i] - arrivalTime[i];
//	    	waitingTime[i] = turnAroundTime[i] - temp[i];
//	    	averageWaitingTime+= waitingTime[i];
//	    	averageTurnAroundTime+= turnAroundTime[i];
//	    }
//	    
//	    System.out.println("Waiting Time");
//	    for(i=0;i<n;i++)
//	    {
//	    	System.out.print("P" + processId[i] +": " +  waitingTime[i] +"\n");
//	    }
//	    System.out.println("Average Waiting Time: "+ (float)(averageWaitingTime/n));
//	    System.out.println();
//	    System.out.println("Turnaround Time");
//	    for(i=0;i<n;i++)
//	    {
//	    	System.out.print("P" + processId[i] +": " + turnAroundTime[i] +"\n");
//	    }
//	    
//	    System.out.println("Average Turnaround time: "+ (float)(averageTurnAroundTime/n));
//	   for(i=0;i<n;i++){
//		   System.out.println(completionTime[i]);
//	   }
//	  
//	}
//	public static void Test()
//	{
//		int i;
//		int st=0, tot=0;
//		int priority[] = new int[n];
//		
//		for(i = 0; i<n;i++){
//			System.out.print("Enter Priority "+ (i+1) + ": ");
//			priority[i]  = input.nextInt();
//		}
//		
//	    while(true){
//	    	int min=0,c=n;
//	    	if (tot==n)
//	    		break;
//	    	
//	    	for ( i=0;i<n;i++)
//	    	{
//	    		if ((arrivalTime[i]<=st) && (flag[i]==0) && (priority[i]<min))
//	    		{	
//	    			min=priority[i];
//	    			c=i;
//	    		}
//	    	}
//	    	
//	    	if (c==n)
//	    		st++;
//	    	else
//	    	{
//	    		burstTime[c]--;
//	    		st++;
//	    		if (priority[c]==0)
//	    		{
//	    			completionTime[c]= st;
//	    			flag[c]=1;
//	    			tot++;
//	    		}
//	    	}
//	    }
//	    
//	    for(i=0;i<n;i++)
//	    {
//	    	turnAroundTime[i] = completionTime[i] - arrivalTime[i];
//	    	waitingTime[i] = turnAroundTime[i] - temp[i];
//	    	averageWaitingTime+= waitingTime[i];
//	    	averageTurnAroundTime+= turnAroundTime[i];
//	    }
//	    
//	    System.out.println("Waiting Time");
//	    for(i=0;i<n;i++)
//	    {
//	    	System.out.print("P" + processId[i] +": " +  waitingTime[i] +"\n");
//	    }
//	    System.out.println("Average Waiting Time: "+ (float)(averageWaitingTime/n));
//	    System.out.println();
//	    System.out.println("Turnaround Time");
//	    for(i=0;i<n;i++)
//	    {
//	    	System.out.print("P" + processId[i] +": " + turnAroundTime[i] +"\n");
//	    }
//	    
//	    System.out.println("Average Turnaround time: "+ (float)(averageTurnAroundTime/n));
//	  
//	}
	
	public static void pPrio(){
		int x[] = new int[n];
		int i;
		int smallest;
		int count = 0;
		int time;
		int end;
		System.out.print("Input corresponding process priorities\n");
		for(i = 0; i<n;i++){
			System.out.print(" Enter Priority "+ (i+1) + ": ");
			priority[i]  = input.nextInt();
		}
		for(i=0; i<n; i++)
	        x[i]=burstTime[i];

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
	            waitingTime[smallest] = end - arrivalTime[smallest] - x[smallest];
	            turnAroundTime[smallest] = end - arrivalTime[smallest];
	        }
	    }
	    for(i=0;i<n;i++)
	    {
	    	turnAroundTime[i] = completionTime[i] - arrivalTime[i];
	    	waitingTime[i] = turnAroundTime[i] - temp[i];
	    	averageWaitingTime+= waitingTime[i];
	    	averageTurnAroundTime+= turnAroundTime[i];
	    }
	    System.out.println("\nWaiting Time");
	   
	    
	    System.out.println("Processes | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

        for (i = 0; i < n; i++) {
           

            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
        }
	    
	    
	    System.out.println("Average Turnaround time: "+ (float)(averageTurnAroundTime/n));
	    System.out.println("Average Waiting Time: "+ (float)(averageWaitingTime/n));
	        
	
	}
//	public static void RoundRobinNew(){
//		System.out.print("Enter Quantum Time:");
//		 quantum = input.nextInt();
//		 int flag;
//		
//		
//	       int rem_time[] = new int[n];
//	       int wt_time[] = new int[n];
//	        for(int i=0;i<wt_time.length;i++){
//	            rem_time[i]= burstTime[i];
//	        }
//	        int it=0,j=0,t =0;
//		
//			t=arrivalTime[0];
//			do{
//				j=0;
//				flag=0;
//				for(int i=0;i<n;i++){
//					if((arrivalTime[i]<=t)&&(rem_time[i]>0)){
//						flag=1;
//						t=(t+quantum);
//						if(rem_time[i]<quantum)
//							t=t-(quantum-rem_time[i]);
//						
//						rem_time[i]=(rem_time[i]-quantum);
//						completionTime[i]=t;
//					}
//				}
//				if(flag==0){
//					it=it+1;
//					t=(t+1);
//				}
//				for(int i=0;i<n;i++)
//					if(rem_time[i]>0)
//						j=1;
//			}while(j==1);
//	         
//	        for(int al=0;al<n;al++){
//	            turnAroundTime[al]= completionTime[al]-arrivalTime[al];
//	            waitingTime[al] = turnAroundTime[al]-burstTime[al];
//	             
//	             
//	        }
//	        
//	        int total_wt = 0;
//	        int total_tat = 0;
//	        for (int c=0; c<n; c++) 
//	        { 
//	            total_wt = total_wt + waitingTime[c]; 
//	            total_tat = total_tat + turnAroundTime[c]; 
//	            
//	        } 
//	        System.out.println("Waiting Time");
//		    for(int q=0;q<n;q++)
//		    {
//		    	System.out.print("P" + processId[q] +": " +  waitingTime[q] +"\n");
//		    }
//		    System.out.println("Average waiting time = " + 
//                    (float)total_wt / (float)n); 
//		    System.out.println();
//		    System.out.println("Turnaround Time");
//		    for(int count=0;count<n;count++)
//		    {
//		    	System.out.print("P" + processId[count] +": " + turnAroundTime[count] +"\n");
//		    }
//		    
//		    
//	        
//	       
//	        System.out.println("Average turn around time = " + 
//	                           (float)total_tat / (float)n); 
//	        for(int a=0;a<n;a++){
//				   System.out.println(completionTime[a]);
//			   }
//	       
//	}
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
		System.out.println("Enter time quantum: ");
		quantum = input.nextInt();

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

		
	      System.out.println("Processes | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

	        for (int i = 0; i < n; i++) {
	           

	            System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
	        }
		    
	        

		    System.out.println("Average waiting time = " + 
		    		wait_time_total );

		    
	          
	        System.out.println("Average turn around time = " + 
	        		tat); 
		
	}
	static void rro(){
	          
	    
		System.out.print("Input quantum time: ");  
		int quantum = input.nextInt();
		System.out.print("Input overhead: ");  
		int overhead = input.nextInt(); 
		
		//copy values of bt
		for (int i = 0; i < n; i++)  
			tempBurstTime[i] = burstTime[i];
		//q try
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

		

		  System.out.println("Processes | " + "Arrival Time | " + "Burst Time | "  + "Turn Around Time | " + "Waiting Time");

			for (int i = 0; i < n; i++) {
			   

				System.out.println("P" + (i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i] );
			}
			averageWaitingTime /= n;
		System.out.println("Average waiting time: " + averageWaitingTime);  
		averageTurnAroundTime /= n;
		System.out.println("Average turn around time: " + averageTurnAroundTime); 

	   
	}
	private static void checkArrived() {
		for (int i = 0; i < n; i++) {  
			if (!arrived[i]) { 
				// System.out.println("kung di pa nakapasok sa q, check"); 
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
	
	public static void main (String args[]){
		while(true){
			System.out.print("\n" + breaker + "\n Input number of processes[2-9]: ");
			n = input.nextInt();
			
			System.out.print("\nInput the Arrival Time:\n");
		    for (int i=0;i<n;i++)
		    {
		    	processId[i]= i+1;
		    	System.out.print(" AT" +(i+1)+": ");
		    	arrivalTime[i]= input.nextInt();	
		    	
		    }
		    System.out.print("\nInput the Burst Time:\n");
		    for(int a = 0;a<n;a++){
		    	System.out.print(" BT" +(a+1)+": ");
		    	burstTime[a]= input.nextInt();
		    	temp[a]= burstTime[a];
		    	flag[a]= 0;
		    }
		    System.out.println("\n" + breaker + "\nChoose An Algorithm [A-E] or exit [F]\n[A] Shortest Remaining Time First (SRTF)\n[B] Round Robin (RR)\n[C] Round Robin with Overhead (RRO)\n[D] Preemptive Priority (P-Prio)\n[E] Multi-level Feedback Queue (MLFQ)\n[F] Exit:");
		    System.out.print("\nEnter choice: ");
		    String choice = input.next();
		    System.out.println("\n" + breaker + "\n");
		
		    	switch(choice){
			    case "A":
			    case "a":
			    	ShortestRemainingTimeFirst();
			    	break;
			    case "B":
			    case "b":
			    	RR();
			    	break;
			    case "C":
			    case "c":
			    	RoundRobinWithOverhead();
			    	break;
			    case "D":
			    case "d":
			    	pPrio();//Highest Number Highest Priority
			    	break;
			    case "F":
			    case "f":
			    	System.exit(0);
			    	break;
			    }
		    	System.out.print("\nInput another set?[y/n]:");
				 choice = input.next();
				if(choice.equalsIgnoreCase("y")){
					n=99;
					processId = new int[n];
					 arrivalTime = new int[n]; //  arrival time
					 burstTime = new int[n]; //  burst time
					 flag = new int[n];  //  it checks process is completed or not
					 temp= new int[n];   // temporary burst time variable
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
					continue;

				}else{
					System.out.println("\n\n"+ breaker +"\n*\t   Thank you for using our program.\t    *\n*\t   Made by:\t\t\t\t    *\n*\t      Marc Ricafort\t\t\t    *\n*\t      Mhargarette Basbas\t\t    *\n*\t      Stanley Orong\t\t\t    *\n*\t      Patricia Valenzuela\t\t    *\n*\t      Cyril Verdad\t\t\t    *\n" + breaker );
					break;
				}
			
		}  
		}
}
		
	
