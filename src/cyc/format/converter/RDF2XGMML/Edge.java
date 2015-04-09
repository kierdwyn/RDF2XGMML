package cyc.format.converter.RDF2XGMML;

class Edge {
	private int source;
	private int target;
	private String label;
	private String dispName;
	
	Edge(int s, int t, String name){
		source = s;
		target = t;
		label = s + "-&gt;" + t;
		dispName = name;
	}
	
	public String print2GML(){
		String result = new String();
		result += " edge [\n  source " + source + "\n  target " + target
				+ "\n  label \"" + label + "\"\n displayName \"" + dispName
				+ "\"\n]";
		return result;
	}
	
	public String print2XGMML(){
		String result = new String();
		result += " <edge label=\"" + label + "\" source=\"" + source + "\" target=\"" + target
				+"\">\n  <att name=\"displayName\" type=\"string\" value=\"" + dispName + "\"/>\n";
		result += " </edge>";
		return result;
	}
}
