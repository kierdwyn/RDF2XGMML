package cyc.format.converter.RDF2XGMML;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public abstract class Convertor {
	HashMap<String, Node> nodeList;
	ArrayList<Edge> edgeList;
	int nodeCount;

	Convertor() {
		nodeList = new HashMap<String, Node>();
		edgeList = new ArrayList<Edge>();
		nodeCount = 0;
	}

	public void loadRDFModel(String path) {
		// construct node list and edge list.
		Model m = readModel(path);
		StmtIterator stmtIter = readModel(path).listStatements();
		while (stmtIter.hasNext()) {
			Statement s = stmtIter.next();
			insertStmt(s,m);
		}
	}

	public void convertTo(String path) {
		try {
			PrintWriter out = new PrintWriter(path);
			out.println(print());
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	abstract String print();

	Model readModel(String path) {
		// check if file existed.
		InputStream in = FileManager.get().open(path);
		if (in == null) {
			throw new IllegalArgumentException("File: " + path + " not found");
		}
		// create model and read from file.
		Model model = ModelFactory.createDefaultModel();
		model.read(path);
		return model;
	}

	protected void insertStmt(Statement s, Model m) {
		Resource r = s.getSubject();
		RDFNode n = s.getObject();
		Property p = s.getPredicate();
		String rs = r.toString();
		String ns = n.toString();
		String ps = m.getNsURIPrefix(p.getNameSpace()) + ":" + p.getLocalName();
		addNode(r);
		addNode(n);
		addEdge(rs, n, ps);
		System.out.println("Inserted Statement:" + rs + "\t" + ns + "\t" + ps);
	}

	protected void addNode(RDFNode rdfN) {
		String key = rdfN.toString();
		String name = key;
		if (rdfN.isLiteral()) {
			key = "id:" + nodeCount;
		}
		if (rdfN.isAnon()) {
			name = "[]";
		}
		if (!nodeList.containsKey(key)) {
			Node n = new Node(nodeCount, name);
			nodeList.put(key, n);
			nodeCount++;
		}
	}

	protected void addEdge(String s, RDFNode t, String l) {
		int sId = nodeList.get(s).getId();
		int tId;
		if (t.isLiteral()) {
			tId = nodeCount - 1;
		} else {
			tId = nodeList.get(t.toString()).getId();
		}
		Edge e = new Edge(sId, tId, l);
		edgeList.add(e);
	}
}
