package Round_Robin_Priority;

import java.util.*;

public class RRPriority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] remainingTime = new int[n];

        System.out.println("\nEnter burst time and priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Burst time of process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            System.out.print("Priority of process " + (i + 1) + ": ");
            priority[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        System.out.print("\nEnter the time quantum: ");
        int timeQuantum = sc.nextInt();

        int currentTime = 0;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        boolean[] completed = new boolean[n];
        int totalCompleted = 0;

        while (totalCompleted < n) {
            int highestPriority = -1;
            int selectedProcess = -1;

            for (int i = 0; i < n; i++) {
                if (!completed[i] && priority[i] > highestPriority && remainingTime[i] > 0 && currentTime >= waitingTime[i]) {
                    highestPriority = priority[i];
                    selectedProcess = i;
                }
            }

            if (selectedProcess == -1) {
                currentTime++;
                continue;
            }

            if (remainingTime[selectedProcess] <= timeQuantum) {
                currentTime += remainingTime[selectedProcess];
                remainingTime[selectedProcess] = 0;
                completed[selectedProcess] = true;
                totalCompleted++;

                turnaroundTime[selectedProcess] = currentTime;
                waitingTime[selectedProcess] = currentTime - burstTime[selectedProcess];
            } else {
                currentTime += timeQuantum;
                remainingTime[selectedProcess] -= timeQuantum;

                for (int i = 0; i < n; i++) {
                    if (!completed[i] && priority[i] > priority[selectedProcess] && currentTime >= waitingTime[i]) {
                        waitingTime[i] += timeQuantum;
                    }
                }
            }
        }

        float totalWaitingTime = 0;
        float totalTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        float averageWaitingTime = totalWaitingTime / n;
        float averageTurnaroundTime = totalTurnaroundTime / n;

        System.out.println("\nProcess\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t\t" + burstTime[i] + "\t\t" + priority[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
        }

        System.out.println("\nAverage waiting time: " + averageWaitingTime);
        System.out.println("Average turnaround time: " + averageTurnaroundTime);

        sc.close();
    }
}


