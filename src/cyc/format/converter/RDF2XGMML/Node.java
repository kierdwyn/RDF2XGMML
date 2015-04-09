package cyc.format.converter.RDF2XGMML;

class Node {
	private int id;
	private String label;
	private String[][] att = { { "displayName", "string", "" },
			{ "width", "integer", "" } };

	Node(int id, String name) {
		this.id = id;
		label = String.valueOf(id);
		att[0][2] = name;
		att[1][2] = String.valueOf(name.length());
	}

	public String print2GML() {
		String result = new String();
		String deli = " ";
		result += "node [\n id " + id + "\n label \"" + label + "\"\n";
		for (int i = 0; i < att.length; i++) {
			if (att[i][1].equals("string")) {
				deli += " \"";
			}
			result += " " + att[i][0] + deli + att[i][2] + deli + "\n";
		}
		result += "]";
		return result;
	}

	public String print2XGMML() {
		String result = new String();
		result += " <node label=\"" + label + "\" id=\"" + id + "\">\n";
		for (int i = 0; i < att.length; i++) {
			result += "  <att name=\"" + att[i][0] + "\" type=\"" + att[i][1]
					+ "\" value=\"" + att[i][2] + "\"/>\n";
		}
		result += " </node>";
		return result;
	}

	public int getId() {
		return id;
	}
}
