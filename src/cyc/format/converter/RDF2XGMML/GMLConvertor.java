package cyc.format.converter.RDF2XGMML;

import java.util.Map.Entry;

public class GMLConvertor extends Convertor {
	GMLConvertor(){
		super();
	}
	
	@Override
	String print() {
		// TODO Auto-generated method stub
		String result = new String();
		result += "graph [\n";
		result += "directed 1\n";
		for (Entry<String, Node> item:nodeList.entrySet()){
			result += item.getValue().print2GML() + "\n";
		}
		for (Edge item:edgeList){
			result += item.print2GML() + "\n";
		}
		result += "]";
		return result;
	}

}
