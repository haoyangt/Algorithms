package project.HaoyangTian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Haoyang Tian G47995931
 * 2015-9-19 15:56:42
 */
public class Tree {
	protected Node rootF;
	protected Node rootL;
	protected Node rootP;
	protected Node rootC;
	protected List<Data> data;     //store all data from .csv 
	private int defaultIndex=1;    //just a default value used for "search" function
	private int resultCount=0;     //the total number of result which match the keyword
	public Tree() {
		rootF=new Node();
		rootL=new Node();
		rootP=new Node();
		rootC=new Node();
		data=new ArrayList<Data>();  
	}
	
	//Simplified "insert" Function, in order to reduce the number of parameters.
	protected void insert(String keyword, Node root){
		insert2(keyword, defaultIndex, root);
	}
	//Core function of inserting.
	private void insert2(String keyword, int index, Node root){
		if(index > keyword.length()){
			root.index.add(data.size()-1);
			return;
		}
		String subString=keyword.substring(0, index);
		Node node;
		if((node=root.children.get(subString))==null){
			node=new Node();
			root.children.put(subString, node);
		}
		insert2(keyword, ++index, node);
	}
	
	//Simplified "search" Function, in order to reduce the number of parameters.
	protected void search(String keyword, Node root, String displayType){
		search2(keyword, defaultIndex, root, displayType);
	}
	//Core function of searching.
	private void search2(String keyword, int index, Node root, String displayType){
		if(index > keyword.length()){
			if(displayType.equalsIgnoreCase("Y"))
				getAllSubtree(root);
			else
				getAllSubtreeOneDisplay(root);
			return;
		}
		String subString=keyword.substring(0, index);
		Node node=root.children.get(subString);
		if(node != null)
			search2(keyword, ++index, node,displayType);
		else
			return;
	}
	
	//Print result message.
	protected void printTimeMsg(long timeConsumed){
		System.out.println(resultCount+" Total matches, consumed "+timeConsumed+"ms");
		resultCount=0; //reset
	}
	
	//Traversal of subtree when the node match the keyword. 
	//And print out all data.
	private void getAllSubtree(Node root){
		for (Integer index : root.index) {
			resultCount++;
			System.out.println("RESULT:"+data.get(index).toString()+"\n----------------------------------------------");
		}
		for (Map.Entry<String, Node> entry: root.children.entrySet()) {
			getAllSubtree(entry.getValue());
		}
		if(root.children.size() == 0) 
			return;
	}
	//Traversal of subtree when the node match the keyword. 
	//And print out just one data, in order to deduct the time consumed on "System.out"
	private void getAllSubtreeOneDisplay(Node root){
		for (int i=0;i< root.index.size();i++) {
			resultCount++;
			if(resultCount == 1)
				System.out.println("RESULT:"+data.get(root.index.get(0)).toString()+"\n----------------------------------------------");
		}
		for (Map.Entry<String, Node> entry: root.children.entrySet()) {
			getAllSubtreeOneDisplay(entry.getValue());
		}
		if(root.children.size() == 0) 
			return;
	}
	
	
}
