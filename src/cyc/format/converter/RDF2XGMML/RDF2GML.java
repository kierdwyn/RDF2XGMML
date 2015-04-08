package cyc.format.converter.RDF2XGMML;
import java.io.InputStream;

import org.apache.log4j.BasicConfigurator;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class RDF2GML {
	public static Model readRDF(String path) {
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(path);
		if (in == null) {
			throw new IllegalArgumentException("File: " + path + " not found");
		}
		// read the RDF/XML file
		model.read(path);
		return model;
	}
	
	public static void printStmt(Statement s){
		System.out.println("Subject:");
		Resource r = s.getSubject();
		if (r.isAnon()) {
			System.out.println("anno:");
		}
		System.out.println(r.toString());
		System.out.println("Object:");
		RDFNode n = s.getObject();
		if (n.isAnon()) {
			System.out.println("anno:");
		}
		System.out.println(n.toString());
		System.out.println("Predicate:");
		Property p = s.getPredicate();
		if (p.isAnon()) {
			System.out.println("anno:");
		}
		System.out.println(p.toString());
	}
	
	public static void insertStmt(GML g, Statement s){
		Resource r = s.getSubject();
		RDFNode n = s.getObject();
		Property p = s.getPredicate();
		String rs = r.toString();
		String ns = n.toString();
		String ps = p.toString();
		g.addNode(rs, r.isAnon());
		g.addNode(ns, n.isAnon());
		g.addEdge(rs, ns, ps);
		System.out.println("Inserted Statement:"+rs+ns+ps);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		Model m = readRDF("data/example.xml");
		GML g = new GML();
		StmtIterator stmtIter = m.listStatements();
		while (stmtIter.hasNext()) {
			Statement s = stmtIter.next();
			insertStmt(g,s);
		}
		System.out.println(g.print());
	}

}
