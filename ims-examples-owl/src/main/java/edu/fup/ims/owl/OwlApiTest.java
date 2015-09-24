package edu.fup.ims.owl;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;

public class OwlApiTest {

	static final String pizza_url = "http://www.dcs.bbk.ac.uk/~michael/sw/slides/pizza.owl";
	static final String pizza_local_url = "src\\main\\resources\\edu\\fup\\ims\\ontologies\\pizza.owl";
	static final String secpront_local_url= "src\\main\\resources\\edu\\fup\\ims\\ontologies\\SecurityAndPrivacyOntology.owl";
	
	OWLDataFactory df = OWLManager.getOWLDataFactory();		
			
	public static void main(String[] args) {
		
        OWLOntologyManager manager = create(); //OWLManager.createOWLOntologyManager();
        try {
        	File fileOntology = new File(secpront_local_url);
        	IRI pizzaLocalIRI = IRI.create(fileOntology);
        	IRI secprontLocalIRI = IRI.create(fileOntology);
        	
			OWLOntology ontology = manager.loadOntologyFromOntologyDocument( secprontLocalIRI );
			System.out.println("pizzaLocalIRI: "+ pizzaLocalIRI);
			System.out.println("secprontLocalIRI: "+ secprontLocalIRI);
			
			System.out.println("\n******************Classes******************");
			for (OWLClass cls : ontology.getClassesInSignature())
				System.out.println(cls);
			
			System.out.println("\n******************Adding classes******************");
			OWLDataFactory df = manager.getOWLDataFactory();
			
			
			PrefixOWLOntologyFormat pm = (PrefixOWLOntologyFormat) manager.getOntologyFormat(ontology);
	        System.out.println("pm.getDefaultPrefix(): "+pm.getDefaultPrefix()); 
			
			OWLClass clsA = df.getOWLClass(IRI.create(pizzaLocalIRI + "#A"));
			OWLClass clsB = df.getOWLClass(IRI.create(pizzaLocalIRI + "#B"));
			// Now create the axiom
			OWLAxiom axiom = df.getOWLSubClassOfAxiom(clsA, clsB);
			// add the axiom to the ontology.
			AddAxiom addAxiom = new AddAxiom(ontology, axiom);
			// We now use the manager to apply the change
			manager.applyChange(addAxiom);
			
			System.out.println("\n******************Classes******************");
			for (OWLClass cls : ontology.getClassesInSignature())
				System.out.println(cls);
			
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		/*
		try {
			
			OWLOntologyManager m = create();
			
			//****************************************
			File pizza_iri = new File(pizza_local_url);
			OWLOntology o = m.loadOntologyFromOntologyDocument(pizza_iri);
			System.out.println("o.isEmpty(): " + o.isEmpty());
			
			for (OWLClass cls : o.getClassesInSignature())
				System.out.println(cls);
						
			File output = File.createTempFile("saved_pizza", "owl");
			IRI documentIRI2 = IRI.create(output);
			
			System.out.println("documentIRI2.toURI(): "+documentIRI2.toURI());
			// save in OWL/XML format
			m.saveOntology(o, new OWLXMLOntologyFormat(), documentIRI2);
			
			
			m.saveOntology(o, documentIRI2);
			// print out the ontology on System.out
			m.saveOntology(o, new SystemOutDocumentTarget());
			// Remove the ontology from the manager
			m.removeOntology(o);
			
			
			//***********************************************************
			StringDocumentTarget target = new StringDocumentTarget();
			m.saveOntology(o, target);
			m.removeOntology(o);
			
			OWLOntology o2 = m.loadOntologyFromOntologyDocument(
					new StringDocumentSource(target.toString()));
			
			System.out.println("o2.isEmpty(): " + o2.isEmpty());
			//*************************************************************
			
			
		} catch(OWLOntologyCreationException e1) {
			System.out.println("OWLOntologyCreationException");
			e1.printStackTrace();
		} catch (OWLOntologyStorageException e2) {
			System.out.println("OWLOntologyStorageException");
			e2.printStackTrace();
		} catch (IOException e3) {
			System.out.println("IOException");
			e3.printStackTrace();
		}
		*/

	}
	
	public static OWLOntologyManager create() {
		OWLOntologyManager m = OWLManager.createOWLOntologyManager();
		m.addIRIMapper(new AutoIRIMapper(new File("src\\main\\resources\\edu\\fup\\ims\\ontologies"), true));
		return m;
		}

}
