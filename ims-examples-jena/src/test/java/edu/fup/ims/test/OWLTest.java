package edu.fup.ims.test;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OWLTest {
	
	static final String pizza_uri = "http://www.pizza.com/ontologies/pizza.owl";
	static final String pizza_local_url = "file:src/main/resources/edu/fup/ims/ontologies/pizza.owl";
	
	static final String soa_uri = "http://www.semanticweb.org/ontologies/2010/01/core-soa.owl";
	static final String soa_local_url = "file:src/main/resources/edu/fup/ims/ontologies/soa.owl";

	static final String secpront_local_url= "file:src/main/resources/edu/fup/ims/ontologies/SecurityAndPrivacyOntology.owl";
		
	public static void main(String[] args) {
		

		//OntModelSpec.OWL_MEM_MICRO_RULE_INF
		//OWL_DL_MEM_TRANS_INF
		
		//pizzaOntologyTest( OntModelSpec.OWL_MEM_MICRO_RULE_INF );
		soaOntologyTest( OntModelSpec.OWL_MEM  );
		
		
//        DescribeClass dc = new DescribeClass();
//
//        if (args.length >= 2) {
//            // we have a named class to describe
//            OntClass c = m.getOntClass( args[1] );
//            dc.describeClass( System.out, c );
//        }
//        else {
//            for (Iterator<OntClass> i = m.listClasses();  i.hasNext(); ) {
//                // now list the classes
//                dc.describeClass( System.out, i.next() );
//            }
//        }
        
	}
	
	
	static void soaOntologyTest(OntModelSpec ontModelSpec) {
		
		OntModel m = ModelFactory.createOntologyModel( ontModelSpec );
		m.getDocumentManager().addAltEntry( soa_uri, soa_local_url );
		
		m.read( soa_uri );		
		//m.write(System.out);

		String NS = soa_uri + "#";
		
		OntClass service = m.getOntClass( NS + "Service" );
		System.out.println( "service.getLocalName(): "+ service.getLocalName() );
		System.out.println( "service.getDisjointWith(): "+ service.getDisjointWith() );
		
		System.out.println("*****SuperClasses*****");
		ExtendedIterator<OntClass> it = service.listSuperClasses(false);		
		OntClass superClass;
		while(it.hasNext()){
			superClass = it.next();
			System.out.println( "superClass.getLocalName(): "+ superClass.getURI() );
		}

		System.out.println("*****DisjointWith*****");
		ExtendedIterator<OntClass> it2 = service.listDisjointWith();	
		OntClass disjointClass;
		while(it2.hasNext()){
			disjointClass = it2.next();
			System.out.println( "disjointClass.getLocalName(): "+ disjointClass.getURI() );
		}
		//System.out.println( "service.getSameAs(): "+ service.getSameAs() .getLocalName() ); 
		
		
	}
	
	static void pizzaOntologyTest(OntModelSpec ontModelSpec) {
		
		OntModel m = ModelFactory.createOntologyModel( ontModelSpec );
		m.getDocumentManager().addAltEntry( pizza_uri, pizza_local_url );
		
		m.read( pizza_uri );		
		//m.write(System.out);

		String NS = pizza_uri + "#";
		
		
		OntClass sohoPizza = m.getOntClass( NS + "SohoPizza" );
		System.out.println( "sohoPizza.getLocalName(): "+ sohoPizza.getLocalName() ); 
		System.out.println( "sohoPizza.getSuperClass(): "+ sohoPizza.getSuperClass().getURI() );
		//System.out.println( "sohoPizza.getSameAs(): "+ sohoPizza.getSameAs().getLocalName() ); 
		System.out.println( "sohoPizza.getDisjointWith(): "+ sohoPizza.getDisjointWith() ); 
		
		System.out.println();
		OntClass cheesyPizza = m.getOntClass( NS + "CheesyPizza" );
		System.out.println( "cheesyPizza.getLocalName(): "+ cheesyPizza.getLocalName() ); 
		System.out.println( "cheesyPizza.getSuperClass(): "+ cheesyPizza.getSuperClass().getURI() );
		//System.out.println( "cheesyPizza.getSameAs(): "+ cheesyPizza.getSameAs().getLocalName() ); 
		System.out.println( "cheesyPizza.getDisjointWith(): "+ cheesyPizza.getDisjointWith() ); 
		
		System.out.println();
		OntClass namedPizza = m.getOntClass( NS + "NamedPizza" );
		System.out.println( "namedPizza.getLocalName(): "+ namedPizza.getLocalName() ); 
		System.out.println( "namedPizza.getSuperClass(): "+ namedPizza.getSuperClass().getURI() );
		//System.out.println( "namedPizza.getSameAs(): "+ namedPizza.getSameAs() .getLocalName() ); 
		System.out.println( "namedPizza.getDisjointWith(): "+ namedPizza.getDisjointWith() ); 
		
	}
	

	
		
}
