package us.renedo.find.hibu.bo;

import us.renedo.find.hibu.entity.Domain;

public interface DomainBo {
	public Long save(Domain domain);
	public void delete(Domain domain);
	public Domain get(Long id);
}
