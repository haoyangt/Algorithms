package project.hyt;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("How many numbers you want to create?");
		String input=sc.nextLine();
		System.out.println("What is the maximum in these numbers?");
		String input2=sc.nextLine();
		int count=Integer.parseInt(input);
		int maximum=Integer.parseInt(input2);
		int test[]=new int[count];
		sc.close();
		System.out.print("Sequence: ");
		for (int i = 0; i < test.length; i++) {
			test[i]=(int)(Math.random()*(maximum+1));
			System.out.print(test[i]);
			if(i != test.length-1)
				System.out.print(",");
		}
		System.out.println("\n========= Finished creating random numbers =========");
		long before=System.currentTimeMillis();
		int result=LIS.getResult(test);
		long after=System.currentTimeMillis();
		long before2=System.currentTimeMillis();
		int result2=LIS.getResultWithNormalAlgorithm(test);
		long after2=System.currentTimeMillis();
		System.out.println("Length of Longest Increasing Subsequence(nlogn) is "+result);
		System.out.println("Length of Longest Increasing Subsequence(n^2) is "+result2);
		System.out.println("Time consumed on computation(nlogn): "+(after-before)+" ms");
		System.out.println("Time consumed on computation(n^2): "+(after2-before2)+" ms");
	}
}
