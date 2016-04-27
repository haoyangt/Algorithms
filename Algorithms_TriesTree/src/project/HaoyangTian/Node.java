package project.HaoyangTian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

/**
 * @author Haoyang Tian G47995931
 * 2015-9-19 15:56:42
 */
public class Node {
	protected Map<String, Node> children;
	protected List<Integer> index;
	public Node() {
		children=new CaseInsensitiveMap<String, Node>();
		index=new ArrayList<Integer>();
	}
}
