package us.renedo.find.hibu;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import akka.config.TypedActorConfigurator;
import us.renedo.find.hibu.actor.WhoisActor;
import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;
import us.renedo.find.hibu.util.State;
import us.renedo.find.hibu.util.VarPool;

public class Verify {
	static public ApplicationContext appContext = new ClassPathXmlApplicationContext("BeanLocations.xml");

	 public static void main( String[] args ){
		 DomainBo domainBo = (DomainBo)appContext.getBean("domainBo");
		 
		 List<Domain> domains = domainBo.getByProvince("BURGOS", "09", "947");
		 
		 if(domains!=null&&domains.size()>0){
			 for(int i=0;i<domains.size();i++){
				 Domain domain = domains.get(i);
				 TypedActorConfigurator configurator = (TypedActorConfigurator) appContext.getBean("supervisor");
				 WhoisActor whoisActor = configurator. getInstance(WhoisActor.class);
				 
				 System.out.println(domain.getDomain());
				 
				 whoisActor.proc(domain, appContext);
				 
				 System.out.println("Currents:"+State.getCurrentVerify());
				 if(i%15==0){
					 try {
						 Thread.sleep(60000);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
				 }
			 }
		 }
		 
	 }
}
