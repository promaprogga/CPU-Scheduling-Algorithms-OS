package Round_Robin;

import java.util.Scanner;

public class RoundRobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];

        System.out.println("Enter the arrival time and burst time of each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Arrival time of process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Burst time of process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        System.out.print("Enter the time quantum: ");
        int timeQuantum = sc.nextInt();

        int t = 0;
        int completed = 0;
        while (completed < n) {
            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0 && arrivalTime[i] <= t) {
                    if (remainingTime[i] <= timeQuantum) {
                        t += remainingTime[i];
                        completionTime[i] = t;
                        turnaroundTime[i] = completionTime[i] - arrivalTime[i];
                        waitingTime[i] = turnaroundTime[i] - burstTime[i];
                        remainingTime[i] = 0;
                        completed++;
                    } else {
                        t += timeQuantum;
                        remainingTime[i] -= timeQuantum;
                    }
                }
            }
        }

        double totalTurnaroundTime = 0.0;
        double totalWaitingTime = 0.0;
        for (int i = 0; i < n; i++) {
            totalTurnaroundTime += turnaroundTime[i];
            totalWaitingTime += waitingTime[i];
        }

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));
        System.out.println("Average Waiting Time: " + (totalWaitingTime / n));

        sc.close();
    }

}
