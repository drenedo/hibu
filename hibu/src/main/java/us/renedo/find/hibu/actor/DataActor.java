package us.renedo.find.hibu.actor;

import us.renedo.find.hibu.bo.DomainBo;

public interface DataActor {

	public void proc(String url);
	public void setDomainBo(DomainBo domainBo);
}
