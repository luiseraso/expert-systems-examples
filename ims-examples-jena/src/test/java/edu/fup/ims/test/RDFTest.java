package edu.fup.ims.test;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;


public class RDFTest {
	
	static final String rdfDocument = "src/main/resources/edu/fup/ims/fragment.rdf";
	
	public static void main(String[] args){
		
		System.out.println("***************test1***************");
		test1();
		System.out.println("***************test2***************");
		test2();
	}

	static void test1() {
		
		 Model m = ModelFactory.createDefaultModel();
		 String nsA = "http://somewhere/else#";
		 String nsB = "http://nowhere/else#";
		 Resource root = m.createResource( nsA + "root" );
		 Property P = m.createProperty( nsA + "P" );
		 Property Q = m.createProperty( nsB + "Q" );
		 Resource x = m.createResource( nsA + "x" );
		 Resource y = m.createResource( nsA + "y" );
		 Resource z = m.createResource( nsA + "z" );
		 m.add( root, P, x ).add( root, P, y ).add( y, Q, z );
		 System.out.println( "# -- no special prefixes defined" );
		 m.write( System.out );
		 System.out.println( "# -- nsA defined" );
		 m.setNsPrefix( "nsA", nsA );
		 m.write( System.out );
		 System.out.println( "# -- nsA and cat defined" );
		 m.setNsPrefix( "cat", nsB );
		 m.write( System.out );
		 
	}
	
	static void test2() {
		
		Model m2 = ModelFactory.createDefaultModel();
		//m2.read( "file:src/main/resources/ims/fup/fragment.rdf" );
		m2.read( rdfDocument );
		m2.write( System.out );
		
	}
	
}
