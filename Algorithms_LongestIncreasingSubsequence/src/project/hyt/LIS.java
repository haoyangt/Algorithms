package project.hyt;

/**
 * 
 * Enlightened by "http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/"
 * @author Haoyang Tian
 *
 */
public class LIS {
	
	
	public static int getResult(int input[]){
		//Initiation
		int[] result=new int[input.length];
		result[0] = input[0];
		int length=1;
				
		//======This outer loop's time complexity is n.======
		for (int i = 1; i < input.length; i++) {
			if(input[i] < result[0])
				result[0] = input[i];
			//This is the only case that **length** would increase. 
			//Only when the new number is larger than the end element of the array "result[]".
			//And by the way, the length of the array "result[]" do represents the length of LIS, but the elements in it is not exactly the same as the elements of LIS.
			else if(input[i] > result[length-1])
				result[length++] = input[i];
			else{
				//======Use binary search, make the time complexity of inner loop reduce to log(n).======
				int leftIndex=0;
				int rightIndex=length-1;
				int midIndex;
				int targetIndex;
				while(rightIndex - leftIndex > 1){
					midIndex = (leftIndex +rightIndex)/2;
					if(input[i] < result[midIndex])
						rightIndex = midIndex;
					else
						leftIndex = midIndex;
				}
				targetIndex = rightIndex;
				result[targetIndex] = input[i];
			}
		}
		return length;
	}
	
	public static int getResultWithNormalAlgorithm(int[] input){
		int[] result = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			int maximum=Integer.MIN_VALUE;
			int maximumIndex=0;
			for (int j = 0; j < i; j++) {
				if(input[j] < input[i])
					if(result[j]>maximum){
						maximum=result[j];
						maximumIndex=j;
					}
			}
			result[i]=result[maximumIndex]+1;
		}
		int maximum=Integer.MIN_VALUE;
		for (int i = 0; i < result.length; i++) {
			if(result[i] >maximum)
				maximum=result[i];
		}
		return maximum;
	}
	
}
