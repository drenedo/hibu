package us.renedo.find.hibu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;

/**
 * Hello world!
 *
 */

public class DomainSearch 
{
    public static void main( String[] args )
    {
    	ApplicationContext appContext = 
    	    	  new ClassPathXmlApplicationContext("BeanLocations.xml");
        System.out.println( "Hello World!" );

        Domain domain = new Domain();
        domain.setDomain("lala.es");
        domain.setWhois("pp ss");

        DomainBo domainBo = (DomainBo)appContext.getBean("domainBo");
        domainBo.save(domain);
        
    }
}
