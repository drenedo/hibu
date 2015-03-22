package us.renedo.find.hibu.dao;


import us.renedo.find.hibu.entity.Domain;

public interface DomainDao{
	public Long save(Domain domain);
	public void delete(Domain domain);
	public void update(Domain domain);
	public Domain get(Long id);
	public Domain getByDomain(String domain);
}
