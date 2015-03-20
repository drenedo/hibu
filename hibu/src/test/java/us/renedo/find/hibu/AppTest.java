package us.renedo.find.hibu;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest{
	private final static String NAME = "lala.es";
	private final static String WHOIS = "whois lala.es";
	
	private static ApplicationContext appContext = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
    	appContext = 
    	    	  new ClassPathXmlApplicationContext("BeanLocations.xml");
	}
	

    @Test
    public void testApp()
    {
        assertTrue( true );
    }

    @Test
    public void testDB()
    {
        Domain domain = new Domain();
        domain.setDomain(NAME);
        domain.setWhois(WHOIS);

        DomainBo domainBo = (DomainBo)appContext.getBean("domainBo");
        
        Long id = domainBo.save(domain);
        assertNotNull(id);
        
        domain = domainBo.get(id);
        assertNotNull(domain);
        assertEquals(NAME, domain.getDomain());
        assertEquals(WHOIS, domain.getWhois());
        
        domainBo.delete(domain);

        domain = domainBo.get(id);

        assertNull(domain);
    }
}
