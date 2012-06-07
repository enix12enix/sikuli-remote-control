package org.sikuli.remote.client;

import static junit.framework.Assert.*;

import java.net.URLDecoder;
import java.util.HashMap;

import org.junit.Test;
import org.sikuli.remote.client.SikuliAction;

public class TestSikuliAction {

	@Test
	public void testUrlDataBuilder() throws Exception {
		
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("psc", "test.png");
		args.put("timeout", "30");
		
		String urlData = URLDecoder.decode(SikuliAction.click.getUrlData(args), "UTF-8");
		System.out.println(urlData);
		assertNotNull(urlData);
		assertTrue(urlData.contains("args=|psc=test.png|"));
		assertTrue(urlData.contains("name=" + SikuliAction.click.name()));
	
	}
	
	@Test
    public void testUrlDataBuilder2() throws Exception {
		
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("psc1", "test1.png");
		args.put("psc2", "test2.png");
		
		try {
			URLDecoder.decode(SikuliAction.click.getUrlData(args), "UTF-8");
			fail();
		} catch(IllegalArgumentException ex) {
			
		}
		
	}
	
	
	

}
