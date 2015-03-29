package us.renedo.find.hibu;

import org.junit.Test;

import us.renedo.find.hibu.util.SyncRead;
import us.renedo.find.hibu.util.VarPool;
import static org.junit.Assert.*;

public class UrlTest {

    @Test
    public void testUrl(){
    	SyncRead sr = new SyncRead();
    	
    	String value = sr.getUrl("http://google.com", null);
    	
    	System.out.println("-->"+value);
    	
    	assertNotEquals(value, VarPool.BLANK);
    }
}
