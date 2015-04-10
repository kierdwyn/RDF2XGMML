package cyc.format.converter.RDF2XGMML;

class Edge {
	private int source;
	private int target;
	private String label;
	private String[][] att = { { "displayName", "string", "" },
			 { "sourceName", "String", "" },{"targetName","String",""}};
	
	Edge(Node s, Node t, String name){
		source = s.getId();
		target = t.getId();
		label = source + "-&gt;" + target;
		att[0][2] = name;
		att[1][2] = s.getName();
		att[2][2] = t.getName();
	}
	
	public String print2GML(){
		String result = new String();
		result += " edge [\n  source " + source + "\n  target " + target
				+ "\n  label \"" + label + "\"\n displayName \"" + att[0][2]
				+ "\"\n]";
		return result;
	}
	
	public String print2XGMML(){
		String result = new String();
		result += " <edge label=\"" + label + "\" source=\"" + source + "\" target=\"" + target +"\">\n";
		for (int i = 0; i < att.length; i++) {
			result += "  <att name=\"" + att[i][0] + "\" type=\"" + att[i][1]
					+ "\" value=\"" + att[i][2] + "\"/>\n";
		}
		result += " </edge>";
		return result;
	}
}
