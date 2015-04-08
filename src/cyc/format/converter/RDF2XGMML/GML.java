package cyc.format.converter.RDF2XGMML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class Node {
	private int id;
	private String label;
	private String name;
	
	Node(int id, String l){
		this.id=id;
		label=String.valueOf(id);
		name=l;
	}
	
	public String print(){
		String result = new String();
		result += "node [\n id " + id + "\n label \""+
				label + "\"\n name \""+ name + "\"\n]";
		return result;
	}
	
	public int getId(){
		return id;
	}
}

class Edge {
	private int source;
	private int target;
	private String label;
	
	Edge(int s, int t, String l){
		source = s;
		target = t;
		label = l;
	}
	
	public String print(){
		String result = new String();
		result += "edge [\n source " + source + "\n target " +
		target + "\n label \"" + label + "\"\n]";
		return result;
	}
}

public class GML {
	private HashMap<String, Node> nodeList;
	private ArrayList<Edge> edgeList;
	private int nodeCount;
	
	GML(){
		nodeList = new HashMap<String,Node>();
		edgeList = new ArrayList<Edge>();
		nodeCount = 0;
	}
	
	public void addNode(String l, boolean isAnno){
		if(nodeList.get(l) == null){
			String label;
			if (isAnno){
				label = "[]";
			}else{
				label = l;
			}
			Node n = new Node(nodeCount,label);
			nodeList.put(l, n);
			nodeCount++;
		}
	}
	
	public void addEdge(String s, String t, String l){
		int sId = nodeList.get(s).getId();
		int tId = nodeList.get(t).getId();
		Edge e = new Edge(sId, tId, l);
		edgeList.add(e);
	}
	
	public String print(){
		String result = new String();
		result += "graph [\n";
		for (Entry<String, Node> item:nodeList.entrySet()){
			result += item.getValue().print() + "\n";
		}
		for (Edge item:edgeList){
			result += item.print() + "\n";
		}
		result += "]";
		return result;
	}
}
