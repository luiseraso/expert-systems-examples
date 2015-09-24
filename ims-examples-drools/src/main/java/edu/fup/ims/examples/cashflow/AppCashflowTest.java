package edu.fup.ims.examples.cashflow;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.FactHandle;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Rules based on basic drools examples and documentation 
 * Available in: http://docs.jboss.org/drools/release/6.1.0.Final/drools-docs/html/ch06.html
 * 
 * @author Luis Eraso <luiseraso@gmail.com>
 *
 */
public class AppCashflowTest {

    public static void main(String[] args) throws Exception {
    	
    	
		// KieSession 
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("CashFlowKS");

        AccountPeriod acp = new AccountPeriod(date( "2013-01-01"), date( "2013-03-31"));

        Account ac = new Account(1, 0);

        CashFlow cf1 = new CashFlow(date( "2013-01-12"), 100, CashFlowType.CREDIT, 1 );
        CashFlow cf2 = new CashFlow(date( "2013-02-2"), 200, CashFlowType.DEBIT, 1 );
        CashFlow cf3 = new CashFlow(date( "2013-05-18"), 50, CashFlowType.CREDIT, 1 );
        CashFlow cf4 = new CashFlow(date( "2013-03-07"), 75, CashFlowType.CREDIT, 1 );

        FactHandle fh = ksession.insert( acp );
        ksession.insert( ac );

        ksession.insert( cf1 );
        ksession.insert( cf2 );
        ksession.insert( cf3 );
        ksession.insert( cf4 );
        
        Agenda agenda = ksession.getAgenda();
        agenda.getAgendaGroup( "calculation" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "report" ).setFocus();
        ksession.fireAllRules();
        System.out.println();
        
        
        acp.setStart(date( "2013-04-01"));
        acp.setEnd(date( "2013-06-31"));
        ksession.update(fh, acp);

        agenda.getAgendaGroup( "calculation" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "report" ).setFocus();
        ksession.fireAllRules();     
        System.out.println();
    }

    public static Date date(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse( str );
    }


}
