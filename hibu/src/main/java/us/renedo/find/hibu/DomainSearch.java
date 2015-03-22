package us.renedo.find.hibu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import akka.actor.TypedActor;
import akka.actor.TypedActorFactory;
import akka.config.TypedActorConfigurator;
import us.renedo.find.hibu.actor.DataActor;
import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;
import us.renedo.find.hibu.util.State;
import us.renedo.find.hibu.util.VarPool;

/**
 * Hello world!
 *
 */

public class DomainSearch {
	static public ApplicationContext appContext = new ClassPathXmlApplicationContext("BeanLocations.xml");
	
	 public static void main( String[] args ){
        
        TypedActorConfigurator configurator = (TypedActorConfigurator) appContext.getBean("supervisor");
        
        for(String what : VarPool.WHAT){
	        for(int i=1;i<20;i++){
	        	DataActor dataActor = configurator. getInstance(DataActor.class);
	        	
	        	//This is mandatory or the ip has been blocked
	        	try {
		        	dataActor.procNoAsync(VarPool.URL_WHAT_START+i+VarPool.URL_WHAT_CO+what+VarPool.URL_WHAT_WHAT+what+VarPool.URL_WHAT_END,i,appContext);
					Thread.sleep(VarPool.WAIT);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	System.out.println("PagesWhitNoData:"+State.getPagesWhitNoData().size());
	        	if(State.getPagesWhitNoData().size()>5){
	        		List<Integer> pages = State.getPagesWhitNoData();
	        		Integer lastPage = 0;
	        		for(Integer cPage : pages){
	    	        	try {
		        			dataActor = configurator.getInstance(DataActor.class);
		    	        	dataActor.procNoAsync(VarPool.URL_WHAT_START+cPage+VarPool.URL_WHAT_CO+what+VarPool.URL_WHAT_WHAT+what+VarPool.URL_WHAT_END,i,appContext);
	    					Thread.sleep(VarPool.WAIT);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	        		}
	        		State.resetPagesWhitNoData();
	        	}
	        }
    	}
    }
}
