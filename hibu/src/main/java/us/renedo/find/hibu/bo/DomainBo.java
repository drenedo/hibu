package us.renedo.find.hibu.bo;

import java.util.List;

import us.renedo.find.hibu.entity.Domain;

public interface DomainBo {
	public Long save(Domain domain);
	public void delete(Domain domain);
	public Domain get(Long id);
	public Domain getByDomain(String domain);
	public void update(Domain domain);
	public List<Domain> getByProvince(String province,String cp, String telf);
}
