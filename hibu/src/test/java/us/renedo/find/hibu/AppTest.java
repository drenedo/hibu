package us.renedo.find.hibu;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
	
	private static appContext = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

    	appContext = 
    	    	  new ClassPathXmlApplicationContext("BeanLocations.xml");
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testDB()
    {
        Domain domain = new Domain();
        domain.setDomain("lala.es");
        domain.setWhois("pp ss");
        
        
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
