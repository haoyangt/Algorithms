package hy;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//double e=2.71828182845904523536028747135266249775724709369995957496696762772407663;
		int n=1000;//applicants count;
		int testCount=100000;//test count
		System.out.println("Input several \"stopping number\" range from 1 to 1000, split them by commas, such as \"100, 200, 300, 400\" ");
		Scanner sc=new Scanner(System.in);
		String input[]=sc.nextLine().split(",");
		sc.close();
		int inputData[]=new int[input.length];
		for (int i = 0; i < input.length; i++) {
			inputData[i]=Integer.parseInt(input[i]);
		}
		for(int k=0; k<inputData.length; k++){
			int r=inputData[k];
			
			double successCount=0;
			for (int i = 0; i < testCount; i++) {
				//Initiation
				Candidate candidate[]=new Candidate[n];
				for (int j = 0; j < candidate.length; j++) {
					candidate[j]=new Candidate();
				}
				double bestValue=Double.MIN_VALUE;
				int bestIndex=-1;
				//Get the best applicant among all applicants
				for (int j = 0; j < n; j++) {
					if(candidate[j].competency>bestValue){
						bestValue=candidate[j].competency;
						bestIndex=j;
					}
				}
				//Find the best applicant among r-1 applicants
				double maximum=Double.MIN_VALUE;
				for (int j = 0; j < r; j++) {
					if(candidate[j].competency>maximum){
						maximum=candidate[j].competency;
					}
				}
				//Select the applicant using stopping rule
				double findIndex=-1;
				for (int j = r; j < candidate.length; j++) {
					if(candidate[j].competency>maximum){
						findIndex=j;
						break;
					}
				}
				if(findIndex==bestIndex){
					successCount++;
				}
			}
			System.out.println("r: "+r+"    Accuracy: "+successCount/testCount);
		}
	}
}
