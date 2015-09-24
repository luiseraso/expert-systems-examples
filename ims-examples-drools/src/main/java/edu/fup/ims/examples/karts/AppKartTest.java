package edu.fup.ims.examples.karts;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Karts and users <br>
 * Kart model based on Mario Kart 8
 * Available in: http://www.mariowiki.com/Mario_Kart_8 <br>
 * 
 * Rules based on basic drools examples and documentation 
 * Available in: http://docs.jboss.org/drools/release/6.1.0.Final/drools-docs/html/ch06.html
 * 
 * @author Luis Eraso <luiseraso@gmail.com>
 *
 */
public class AppKartTest {

	public static void main(String[] args) {
 
		//KieSession 
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("KartsKS");         
        
        //create son instances of classes Kart and User...
        User yoshi = new User("Yoshi", 17);
        User luigi = new User("Luigi Bros", 19);
        User mario = new User("Mario Bros", 21);
        Kart standard = new Kart("Standard");
        Kart mach8 = new Kart("Mach 8");
        
        //select option
        int op = readOption();       
        
        switch (op){
        
	        case 1:
	        	//Lanza solo la regla 1                
	            ksession.insert( standard );
	            break;
	        case 2:
	        	//No lanza ninguna regla	                 
	            ksession.insert( yoshi );
	            break;
	        case 3:
	        	//Lanza solo la regla 2  
	            ksession.insert( luigi );
	            break;
	        case 4:
	        	//Lanza las reglas 1 y 2 
	            ksession.insert( luigi );
	            ksession.insert( standard );
	            break;
	        case 5:
	        	//Lanza las reglas 1, 2 y 3 
	            ksession.insert( luigi );
	            ksession.insert( mach8 );
	            break;	           
	        case 6:
	        	//Lanza las reglas 2 y 4 
	            ksession.insert( mario );	            
	            break;
	        default:
	            System.out.println("Just options from 1 to 6!");	            
	            break;  
        }
        
        ksession.fireAllRules();                
   
        //Close session
        ksession.dispose();

	}
	
	/**
	 * 
	 * @return The number of selected option
	 */
    public static int readOption() {
        System.out.println( "Pressure enter your option:" );
        System.out.println( "[1] R1" );  
        System.out.println( "[2] None" );
        System.out.println( "[3] R2" );
        System.out.println( "[4] R1 y R2" );
        System.out.println( "[5] R1, R2 y R3" );
        System.out.println( "[6] R2 y R4" );
        
		Scanner keyboard = new Scanner(System.in);
        int result= keyboard.nextInt();
        keyboard.close();
        return result;
        
    }

}
