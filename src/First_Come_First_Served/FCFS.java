package First_Come_First_Served;

import java.util.Scanner;

public class FCFS {

    static void CalcWT(int process[], int a, int WaitingTime[]){

        WaitingTime[0]=0;

        for(int i=1;i<a;i++){
            WaitingTime[i]=process[i-1]+WaitingTime[i-1];
        }


    }

    static void calcTurnAroundTime(int processArr[],int a, int waitingTime[],int turnAroundTime[]){

        for(int i=0;i<a;i++){
            turnAroundTime[i]=processArr[i]+waitingTime[i];
        }

    }

    static void calcAvgTime(int processArr[],int a){

        int waitingTime[]=new int[a], turnAroundTime[]=new int [a];
        int totalWaitingTime=0, totalTurnAroundTime=0;

        CalcWT(processArr,a,waitingTime);

        calcTurnAroundTime(processArr,a,waitingTime,turnAroundTime);

        System.out.println("\nProcesses  "+  "Burst Time  "+ "Waiting Time  "+ "Turn Around Time");

        for(int i=0;i<a;i++){

            totalWaitingTime=totalWaitingTime+waitingTime[i];

            totalTurnAroundTime=totalTurnAroundTime+turnAroundTime[i];

            System.out.println(""+(i+1)+"\t\t\t"+processArr[i]+"\t\t\t"+waitingTime[i]+"\t\t\t"+turnAroundTime[i]);


        }

        System.out.println("\nAverage Waiting Time="+ (float)totalWaitingTime/(float)a);

        System.out.println("\nAverage Turn Around Time="+(float)totalTurnAroundTime/(float)a);
    }

    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int a=sc.nextInt();

        int processArr[]=new int[a];

        for(int i=0;i<a;i++){
            System.out.print("Enter burst time for process "+(i+1)+": ");
            processArr[i]=sc.nextInt();
        }

        calcAvgTime(processArr,a);

    }
}

