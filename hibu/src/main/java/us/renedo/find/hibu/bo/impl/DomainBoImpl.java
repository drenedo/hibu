package us.renedo.find.hibu.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.dao.DomainDao;
import us.renedo.find.hibu.entity.Domain;

@Service("domainBo")
public class DomainBoImpl implements DomainBo{

	@Autowired
	DomainDao domainDao;
	
	public void save(Domain domain){
		domainDao.save(domain);
	}
}
