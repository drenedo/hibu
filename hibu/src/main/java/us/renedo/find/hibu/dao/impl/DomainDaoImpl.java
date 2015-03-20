package us.renedo.find.hibu.dao.impl;

import org.springframework.stereotype.Repository;

import us.renedo.find.hibu.dao.DomainDao;
import us.renedo.find.hibu.entity.Domain;
import us.renedo.find.hibu.util.DaoSupport;

@Repository("domainDao")
public class DomainDaoImpl extends DaoSupport implements DomainDao{

	@Override
	public void save(Domain domain) {
		getSession().save(domain);
	}

}
