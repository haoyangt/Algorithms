package project.HaoyangTian;

public class Main {

	public static void main(String[] args) {
		Matrix matrix1=new Matrix(512);
		Matrix matrix2=new Matrix(512);
		
//		System.out.println(matrix1);
//		System.out.println();
//		System.out.println(matrix2);
//		System.out.println();
//		
		long begin=matrix1.getTime();
		Matrix result1=matrix1.multiplyByStrassenAlgorithm(matrix2);
		System.out.println(matrix1.getTime()-begin+"ms");
	

		begin=matrix1.getTime();
		Matrix result2=matrix1.multiplyCommonly(matrix2);
		System.out.println(matrix1.getTime()-begin+"ms");
	}

}
