package Shortest_Remaining_Time_First;

import java.util.Scanner;

class Process {
    int processId;
    int burstTime;
    int arrivalTime;

    public Process(int processId, int burstTime, int arrivalTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class SRTF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        Process[] processArr = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for process " + (i + 1));
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();
            processArr[i] = new Process(i + 1, bt, at);
        }
        sc.close();

        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] remainingTime = new int[n];

        for (int i = 0; i < n; i++) {
            remainingTime[i] = processArr[i].burstTime;
        }

        int complete = 0;
        int t = 0;
        int min = Integer.MAX_VALUE;
        int shortest = 0;
        int finishTime;
        boolean check = false;

        while (complete != n) {
            for (int j = 0; j < n; j++) {
                if ((processArr[j].arrivalTime <= t) && (remainingTime[j] < min) && (remainingTime[j] > 0)) {
                    min = remainingTime[j];
                    shortest = j;
                    check = true;
                }
            }
            if (check == false) {
                t++;
                continue;
            }
            remainingTime[shortest]--;
            min = remainingTime[shortest];
            if (min == 0) {
                min = Integer.MAX_VALUE;
            }
            if (remainingTime[shortest] == 0) {
                complete++;
                finishTime = t + 1;
                waitingTime[shortest] = finishTime - processArr[shortest].burstTime - processArr[shortest].arrivalTime;
                if (waitingTime[shortest] < 0) {
                    waitingTime[shortest] = 0;
                }
            }
            t++;
        }

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = processArr[i].burstTime + waitingTime[i];
        }

        float totalWaitingTime = 0;
        float totalTurnAroundTime = 0;
        System.out.println("\nProcesses  " + "Burst Time  " + "Waiting Time  " + "Turn Around Time");
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println("  P" + processArr[i].processId + "\t\t" + processArr[i].burstTime + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }
        System.out.println("\nAverage Waiting Time = " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time = " + (totalTurnAroundTime / n));
    }
}