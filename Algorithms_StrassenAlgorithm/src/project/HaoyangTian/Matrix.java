package project.HaoyangTian;

public class Matrix {
	private int row;
	private int col;
	private int data[][];
	private Matrix sections[];
	private int n;
	
	/*public Matrix(){
		sections=new Matrix[4];
		sections[0]=new Matrix();
		sections[1]=new Matrix();
		sections[2]=new Matrix();
		sections[3]=new Matrix();
		this.n=0;
	}*/
	
	public Matrix(int n) {
		double logValue=Math.log((double)n)/Math.log(2);
		col=row=(int)Math.pow(2, Math.ceil(logValue));
		data=new int[row][col];
		sections=new Matrix[4];		//sections[0] for 11, sections[1] for 12, sections[2] for 21, sections[3] for 22
		this.n=n;
		dataInitialization(n);
		sectionsInitialization();
	}
	
	public Matrix(int data[][]){
		col=row=data.length;
		this.data=new int[data.length][data.length];
		sections=new Matrix[4];
		this.n=0;
		dataInitialization(data);
		sectionsInitialization();
	}
	
	private Matrix plus(Matrix matrix){
		if(this.row != matrix.row)
			return null;
		int data[][]=new int[this.row][this.col];
		for(int i=0; i<this.data.length; i++){
			for(int j=0; j<this.data.length; j++){
				data[i][j]=this.data[i][j]+matrix.data[i][j];
			}
		}
		Matrix returnMatrix=new Matrix(data);
		return returnMatrix;
	};
	
	private Matrix minus(Matrix matrix){
		if(this.row != matrix.row)
			return null;
		int data[][]=new int[this.row][this.col];
		for(int i=0; i<this.data.length; i++){
			for(int j=0; j<this.data.length; j++){
				data[i][j]=this.data[i][j]-matrix.data[i][j];
			}
		}
		Matrix returnMatrix=new Matrix(data);
		return returnMatrix;
	};
	
	private Matrix merge(Matrix c11, Matrix c12, Matrix c21, Matrix c22){
		if(c11.row != c12.row || c12.row != c21.row || c21.row != c22.row)
			return null;
		int data[][]=new int[c11.row*2][c11.row*2];
		for(int i=0; i<c11.data.length; i++){
			for(int j=0; j<c11.data.length; j++){
				data[i][j]=c11.data[i][j];
				data[i][c11.data.length+j]=c12.data[i][j];
				data[c11.data.length+i][j]=c21.data[i][j];
				data[c11.data.length+i][c11.data.length+j]=c22.data[i][j];
			}
		}
		Matrix returnMatrix=new Matrix(data);
		return returnMatrix;
	};
	
	public Matrix multiplyByStrassenAlgorithm(Matrix matrix){
		if(this.row != matrix.row)
			return null;
		if(this.row == 1){
			int data[][]=new int[this.row][this.row];
			data[0][0]=this.data[0][0]*matrix.data[0][0];
			Matrix returnMatrix=new Matrix(data);
			return returnMatrix;
		}
		Matrix m1=this.sections[0].plus(this.sections[3]).multiplyByStrassenAlgorithm(matrix.sections[0].plus(matrix.sections[3]));
		Matrix m2=this.sections[2].plus(this.sections[3]).multiplyByStrassenAlgorithm(matrix.sections[0]);
		Matrix m3=this.sections[0].multiplyByStrassenAlgorithm(matrix.sections[1].minus(matrix.sections[3]));
		Matrix m4=this.sections[3].multiplyByStrassenAlgorithm(matrix.sections[2].minus(matrix.sections[0]));
		Matrix m5=this.sections[0].plus(this.sections[1]).multiplyByStrassenAlgorithm(matrix.sections[3]);
		Matrix m6=this.sections[2].minus(this.sections[0]).multiplyByStrassenAlgorithm(matrix.sections[0].plus(matrix.sections[1]));
		Matrix m7=this.sections[1].minus(this.sections[3]).multiplyByStrassenAlgorithm(matrix.sections[2].plus(matrix.sections[3]));
		Matrix resultMetrix= merge(m1.plus(m4).minus(m5).plus(m7), m3.plus(m5), m2.plus(m4), m1.minus(m2).plus(m3).plus(m6));
		resultMetrix.n=this.n;
		return resultMetrix;
	}
	
	public Matrix multiplyCommonly(Matrix matrix){
		if(this.row != matrix.row)
			return null;
		int data[][]=new int[this.row][this.col];
		for(int i=0; i<data.length; i++){
			for(int j=0; j<data.length; j++){
				int temp=0;
				for(int k=0; k<data.length; k++){
					temp+=this.data[i][k]*matrix.data[k][j];
				}
				data[i][j]=temp;
			}
		}
		Matrix resultMetrix=new Matrix(data);
		resultMetrix.n=this.n;
		return resultMetrix;
	}
	
	private void dataInitialization(int trueSize){
		for(int i=0; i<data.length; i++){
			for(int j=0; j<data.length; j++){
				if(i < trueSize && j < trueSize)
					this.data[i][j]=(int)(Math.random()*100);
				else
					this.data[i][j]=0;
			}
		}
	}
	private void dataInitialization(int[][] data){
		for(int i=0; i<data.length; i++){
			for(int j=0; j<data.length; j++){
				this.data[i][j]=data[i][j];
			}
		}
	}

	private void sectionsInitialization(){
		if(this.row == 1)
			return;
		int data[][][]=new int[4][this.data.length/2][this.data.length/2];
		for(int i=0; i<this.data.length; i++){
			for(int j=0; j<this.data.length; j++){
				if(i < this.data.length/2 && j < this.data.length/2)
					data[0][i][j]=this.data[i][j];
				if(i < this.data.length/2 && j >= this.data.length/2)
					data[1][i][j-this.data.length/2]=this.data[i][j];
				if(i >= this.data.length/2 && j < this.data.length/2)
					data[2][i-this.data.length/2][j]=this.data[i][j];
				if(i >= this.data.length/2 && j >= this.data.length/2)
					data[3][i-this.data.length/2][j-this.data.length/2]=this.data[i][j];
			}
		}
		this.sections[0]=new Matrix(data[0]);
		this.sections[1]=new Matrix(data[1]);
		this.sections[2]=new Matrix(data[2]);
		this.sections[3]=new Matrix(data[3]);
	}
	
	public long getTime(){
		return System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		String output="";
		for(int i=0; i<this.n; i++){
			for(int j=0; j<this.n; j++){
				if(data[i][j] > 9)
					output+=data[i][j];
				else
					output+="0"+data[i][j];
				if(j !=data.length-1)
					output+="  ";
			}
			if(i != this.n-1)
				output+="\n";
		}
		return output;
	}
}
