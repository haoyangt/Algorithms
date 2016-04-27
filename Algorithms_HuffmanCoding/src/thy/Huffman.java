package thy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Huffman {
	private Map<Character, String> map;
	public Huffman(String input) {
		Node node=createTree(input);
		map=new HashMap<Character, String>();
		traversal(node, "");
		String result=translate(input);
		System.out.println("Huffman Code:\n"+result);
	}
	
	private Node createTree(String s){
		char[] input=s.toCharArray();
		Arrays.sort(input);
		int typeCount=1;
		for (int i=0; i<input.length; i++) {
			if(i > 0 && input[i] != input[i-1])
				typeCount++;
		}
		Node[] nodes=new Node[typeCount];
		
		int index=0;
		for (int i=0; i<input.length; i++) {
			if(i >0){
				if(input[i] == input[i-1])
					nodes[index-1].selfPlus();
				else{
					nodes[index]=new Node(input[i]+"");
					index++;
				}
			}else{
				nodes[index]=new Node(input[i]+"");
				index++;
			}
		}
		
		Heap heap=new Heap();
		for (Node node : nodes) {
			heap.insert(node);
		}
		
		for(int i=0; i<nodes.length-1; i++){
			Node node1=heap.extract_min();
			Node node2=heap.extract_min();
			Node newNode=new Node(node1.getValue()+node2.getValue(), node1.getFrequency()+node2.getFrequency());
			newNode.setLeft(node1);
			newNode.setRight(node2);
			heap.insert(newNode);
		}
		return heap.minimum();
	}
	
	private void traversal(Node node, String code){
		if(!node.isLeaf()){
			if(node.getLeft() != null)
				traversal(node.getLeft(), code+"0");
			if(node.getRight() != null)
				traversal(node.getRight(), code+"1");
		}else
			map.put(node.getValue().charAt(0), code);
	}
	
	private String translate(String s){
		String result="";
		for(int i=0; i<s.length(); i++){
			result+=map.get(s.charAt(i));
		}
		return result;
	}
}
