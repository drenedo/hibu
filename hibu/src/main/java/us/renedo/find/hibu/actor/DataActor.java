package us.renedo.find.hibu.actor;

import org.springframework.context.ApplicationContext;


public interface DataActor {

	public void proc(String url,Integer page);
	public void procNoAsync(String url,Integer page,ApplicationContext appContext);
	
}
