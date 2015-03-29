package us.renedo.find.hibu.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.dao.DomainDao;
import us.renedo.find.hibu.entity.Domain;

@Service("domainBo")
@EnableTransactionManagement
public class DomainBoImpl implements DomainBo{

	@Autowired
	DomainDao domainDao;
	
	public Long save(Domain domain){
		return domainDao.save(domain);
	}

	public void update(Domain domain){
		domainDao.update(domain);
	}

	public void delete(Domain domain){
		domainDao.delete(domain);
	}

	public Domain get(Long id){
		return domainDao.get(id);
	}

	public Domain getByDomain(String domain){
		return domainDao.getByDomain(domain);
	}

	public List<Domain> getByProvince(String province,String cp, String telf){
		return domainDao.getByProvince(province,cp,telf);
	}
	
}
