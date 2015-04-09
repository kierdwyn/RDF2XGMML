package cyc.format.converter.RDF2XGMML;

import org.apache.log4j.BasicConfigurator;

public class RDF2XGMML {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		Convertor c;
		String target = "";
		
		switch (args.length){
		case 0:
			System.out.println("usage:java RDF2XGMML RDFfile XGMMLfile/GMLfile.");
			System.exit(0);
			break;
		case 1:
			target = "out.xgmml";
			System.out.println("convert to XGMML with default path: ./"+target);
			break;
		case 2:
			target = args[1];
			break;
		}
		
		if (target.matches("([^\\s]+(\\.(?i)(gml))$)")){
			c = new GMLConvertor();
		}else{
			c = new XGMMLConvertor();
		}
		c.loadRDFModel(args[0]);
		c.convertTo(target);
	}

}
