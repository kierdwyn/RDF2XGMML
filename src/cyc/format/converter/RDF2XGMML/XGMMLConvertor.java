package cyc.format.converter.RDF2XGMML;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class XGMMLConvertor extends Convertor {
	String graphLabel;
	ArrayList<String> xmlnsList;
	
	XGMMLConvertor(){
		super();
		xmlnsList = new ArrayList<String>();
	}
	
	@Override
	public void loadRDFModel(String path) {
		graphLabel = path;
		Model m = readModel(path);
		for (Entry<String, String> e:m.getNsPrefixMap().entrySet()){
			xmlnsList.add(e.getKey()+"=\""+e.getValue()+"\"");
		}
		// construct node list and edge list.
		StmtIterator stmtIter = m.listStatements();
		while (stmtIter.hasNext()) {
			Statement s = stmtIter.next();
			insertStmt(s,m);
		}
	}

	@Override
	String print() {
		String result = new String();
		result += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
				+ "<graph label=\"" + graphLabel +"\"\n";
		for (String ns:xmlnsList){
			result += "\txmlns:"+ ns + "\n";
		}
		result += "\txmlns=\"http://www.cs.rpi.edu/XGMML\" directed=\"1\">\n";
		for (Entry<String, Node> item:nodeList.entrySet()){
			result += item.getValue().print2XGMML() + "\n";
		}
		for (Edge item:edgeList){
			result += item.print2XGMML() + "\n";
		}
		result += "</graph>";
		return result;
	}

}
