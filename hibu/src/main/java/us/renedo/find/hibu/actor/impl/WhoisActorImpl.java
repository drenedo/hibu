package us.renedo.find.hibu.actor.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;

import us.renedo.find.hibu.actor.GenericActor;
import us.renedo.find.hibu.actor.WhoisActor;
import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;
import us.renedo.find.hibu.util.SyncRead;

public class WhoisActorImpl extends GenericActor implements WhoisActor {
	
	private final static String AMARILLAS = ".*amarillas.*";

	@Override
	public void proc(Domain domain,ApplicationContext appContext) {
		this.domainBo = (DomainBo)appContext.getBean("domainBo");
		
		SyncRead sr = new SyncRead();
		String response = sr.getUrl(domain.getDomain(), null);

		int coincidences = 0;
		System.out.println("Path!"+response);
		if(response.matches(AMARILLAS)){
			System.out.println("Path!");
			 Pattern replace = Pattern.compile(AMARILLAS);
			 
			 Matcher matcher = replace.matcher(response);
			 
			 while(matcher.find()){
				 coincidences++;
			 }
		}
		
		System.out.println(domain.getDomain()+"-"+coincidences);
		
		domain.setWhois(String.valueOf(coincidences));
		
		domainBo.update(domain);
	}

}
