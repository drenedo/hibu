package us.renedo.find.hibu.actor;

import org.springframework.context.ApplicationContext;

import us.renedo.find.hibu.entity.Domain;

public interface WhoisActor {

	public void proc(Domain domain,ApplicationContext appContext);
}
