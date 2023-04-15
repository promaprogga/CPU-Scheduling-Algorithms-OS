package Shortest_Job_First;
import java.util.*;

class Process {
    int processId;
    int burstTime;

    public Process(int processId, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
    }
}

public class SJF {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        System.out.print("Enter the number of processes: ");
        n = input.nextInt();

        Process[] processes = new Process[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the burst time for process " + (i+1) + ": ");
            int burstTime = input.nextInt();
            processes[i] = new Process(i+1, burstTime);
        }

        Arrays.sort(processes, Comparator.comparing(p -> p.burstTime));

        waitingTime[0] = 0;
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i-1] + processes[i-1].burstTime;
        }

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + processes[i].burstTime;
        }

        double avgWaitingTime = 0;
        double avgTurnAroundTime = 0;

        for (int i = 0; i < n; i++) {
            avgWaitingTime += waitingTime[i];
            avgTurnAroundTime += turnAroundTime[i];
        }

        avgWaitingTime /= n;
        avgTurnAroundTime /= n;

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].processId + "\t\t" + processes[i].burstTime + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }

        System.out.println("\nAverage waiting time: " + avgWaitingTime);
        System.out.println("Average turnaround time: " + avgTurnAroundTime);
    }
}

