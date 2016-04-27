package thy;

public class Heap {
	private Node[] heap;
	private int size;
	
	public Heap() {
		this.heap = new Node[256];
		this.heap[0]=null;
		this.size=0;
	}
	
	protected void insert(Node node){
		size++;
		heap[size]=node;
		int current=size;
		while(current>1){
			if(heap[current/2].getFrequency() > heap[current].getFrequency()){
				swap(current/2, current);
				current=current/2;
			}else
				break;
		}
	}
	
	protected Node minimum(){
		return heap[1];
	}
	
	protected Node extract_min(){
		Node node= heap[1];
		heap[1]=heap[size];
		heap[size]=null;
		size--;
		int current=1;
		while(current*2 <=size){
			if(heap[current].getFrequency() > heap[current*2].getFrequency()){
				swap(current, current*2);
				current=current*2;
			}else if(current*2+1 <= size && heap[current].getFrequency() > heap[current*2+1].getFrequency()){
				swap(current, current*2+1);
				current=current*2+1;
			}else
				break;
		}
		return node;
	}
		
	private void swap(int index1, int index2){
		Node temp;
		temp=heap[index1];
		heap[index1]=heap[index2];
		heap[index2]=temp;
	}
	
	@Override
	public String toString() {
		String s="";
		for (int i=1; i<=size; i++) {
			s+="heap["+i+"]: "+heap[i].getValue()+"-"+heap[i].getFrequency()+"\n";
		}
		return s;
	}
}
