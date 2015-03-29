package us.renedo.find.hibu.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
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
	public void update(Domain domain) {
		getSession().update(domain);
	}

	@Override
	public void delete(Domain domain) {
		getSession().delete(domain);
	}
	
	@Override
	public Domain get(Long id) {
		return (Domain)getSession().get(Domain.class,id);
	}

	@Override
	public Domain getByDomain(String domain) {
		String hql = "FROM Domain where domain = '"+domain+"'";
		Query query = getSession().createQuery(hql);
		List results = query.list();
		if(results.size()>0)
			return (Domain)results.get(0);
		else
			return null;
	}

	@Override
	public List<Domain> getByProvince(String province,String cp, String telf) {
		String hql = "FROM Domain where dir like '%"+province+"%' or telf like '"+telf+"%' or dir like '%"+cp+"%'";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
