package us.renedo.find.hibu.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import us.renedo.find.hibu.dao.DomainDao;
import us.renedo.find.hibu.entity.Domain;
import us.renedo.find.hibu.util.DaoSupport;

@Repository("domainDao")
@Transactional
public class DomainDaoImpl extends DaoSupport implements DomainDao{

	@Override
	public Long save(Domain domain) {
		return (Long)getSession().save(domain);
	}

	@Override
	public void delete(Domain domain) {
		getSession().delete(domain);
	}
	
	@Override
	public Domain get(Long id) {
		return (Domain)getSession().get(Domain.class,id);
	}
}
