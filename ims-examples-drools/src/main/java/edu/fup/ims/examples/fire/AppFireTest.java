package edu.fup.ims.examples.fire;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * 
 * Rules based on basic drools examples and documentation 
 * Available in: http://docs.jboss.org/drools/release/6.1.0.Final/drools-docs/html/ch06.html
 * 
 * @author Luis Eraso <luiseraso@gmail.com>
 *
 */
public class AppFireTest {

	public static void main(String[] args) {
 
		// KieSession 
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("FireKS");

        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String,Room> name2room = new HashMap<String,Room>();
        for( String name: names ){
	        Room room = new Room( name );
	        name2room.put( name, room );
	        ksession.insert( room );
	        Sprinkler sprinkler = new Sprinkler( room );
	        ksession.insert( sprinkler );
        }
        
        ksession.fireAllRules();
        System.out.println();
        pause();
        
        Fire kitchenFire = new Fire( name2room.get( "kitchen" ) );
        Fire officeFire = new Fire( name2room.get( "office" ) );
        FactHandle kitchenFireHandle = ksession.insert( kitchenFire );
        FactHandle officeFireHandle = ksession.insert( officeFire );
        ksession.fireAllRules();
        System.out.println();
        pause();
        
        ksession.delete( kitchenFireHandle );
        ksession.delete( officeFireHandle );
        ksession.fireAllRules();
        System.out.println();
        pause();
        
        //Close session
        ksession.dispose();

	}
	
    public static void pause() {
        System.out.println( "Pressure enter to contnue" );
        @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
        //keyboard.close();
    }

}
