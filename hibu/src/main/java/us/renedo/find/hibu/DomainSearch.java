package us.renedo.find.hibu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import akka.config.TypedActorConfigurator;
import us.renedo.find.hibu.actor.DataActor;
import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;

/**
 * Hello world!
 *
 */

public class DomainSearch 
{
	private final static String URL_START = "http://www.paginasamarillas.es/search/all-ac/all-ma/all-pr/all-is/all-ci/all-ba/all-pu/all-nc/";
	private final static String URL_END = "?co=Burgos&where=burgos&ub=false&qc=false";
	
    public static void main( String[] args )
    {
    	ApplicationContext appContext = 
    	    	  new ClassPathXmlApplicationContext("BeanLocations.xml");
        
        TypedActorConfigurator configurator = (TypedActorConfigurator) appContext.getBean("supervisor");

        for(int i=1;i<200;i++){
        	DataActor dataActor = configurator.getInstance(DataActor.class);
        	dataActor.setDomainBo(appContext.getBean(DomainBo.class));
        	dataActor.proc(URL_START+i+URL_END);
        }
        
    }
}
