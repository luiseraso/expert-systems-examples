package edu.fup.ims.services;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class ServiceRegister {
	
	static final String soa_uri = "http://www.semanticweb.org/ontologies/2010/01/core-soa.owl";
	static final String soa_local_url = "file:src/main/resources/edu/fup/ims/ontologies/soa.owl";
	static final String NS = soa_uri + "#";
	
	public static void main(String[] args) {
		
		test();
		//serviceRegister();

	}
	
	public static void serviceRegister(){
		
		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		m.getDocumentManager().addAltEntry( soa_uri, soa_local_url );		
		m.read( soa_uri, "RDF/XML" );
		
		// create the reasoning model using the base model
		//OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, m);
		
		//Get most important class references
		OntClass service = m.getOntClass( NS + "Service" );
		OntClass serviceContract = m.getOntClass( NS + "ServiceContract" );
		OntClass serviceIntarface = m.getOntClass( NS + "ServiceIntarface" );
		OntClass informationType = m.getOntClass( NS + "InformationType" );
		
		//Get most important property references
		OntProperty hasInterface = m.getOntProperty( NS + "hasInterface" );
		OntProperty hasContract = m.getOntProperty( NS + "hasContract" );
		OntProperty hasInput = m.getOntProperty( NS + "hasInput" );
		OntProperty hasOutput = m.getOntProperty( NS + "hasOutput" );
		
		// Register vitalSigns service
		Individual vitalSings = m.createIndividual( NS + "vitalSings", service);
		Individual sc1 = m.createIndividual( NS + "sc1", serviceContract);
		Individual si1 = m.createIndividual( NS + "si1", serviceIntarface);
		Individual userId = m.createIndividual( NS + "userId", informationType);
		Individual age = m.createIndividual( NS + "age", informationType);
		Individual weight = m.createIndividual( NS + "weight", informationType);
		Individual height = m.createIndividual( NS + "height", informationType);
		
		vitalSings.addProperty(hasContract, sc1);
		vitalSings.addProperty(hasInterface, si1);
		si1.addProperty(hasInput, userId);
		si1.addProperty(hasOutput, age);
		si1.addProperty(hasOutput, weight);
		si1.addProperty(hasOutput, height);
		
	}
	
	public static void test(){
		
		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		m.getDocumentManager().addAltEntry( soa_uri, soa_local_url );		
		m.read( soa_uri, "RDF/XML" );
		
		// create the reasoning model using the base model
		OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, m);
				
		//register a new service	
		OntClass service = m.getOntClass( NS + "Service" );
		Individual vitalSings = m.createIndividual( NS + "vitalSings", service );
		
		for (Iterator<Resource> i = vitalSings.listRDFTypes(true); i.hasNext(); ) {
		    System.out.println( vitalSings.getURI() + " is asserted in class " + i.next() );
		}
		
		vitalSings = inf.getIndividual( NS + "vitalSings" );
		for (Iterator<Resource> i = vitalSings.listRDFTypes(true); i.hasNext(); ) {
		    System.out.println( vitalSings.getURI() + " is infered in class " + i.next() );
		}
		
		System.out.println("*****listDeclaredProperties*****");
		for( Iterator<OntProperty> i = service.listDeclaredProperties(true); i.hasNext(); ){
			System.out.println( i.next() );
		}
					
		DatatypeProperty deadline = m.createDatatypeProperty( NS + "deadline" );
		deadline.addDomain( m.getOntClass( NS + "Service" ) );
		deadline.addRange( XSD.dateTime );
		
	}
}
