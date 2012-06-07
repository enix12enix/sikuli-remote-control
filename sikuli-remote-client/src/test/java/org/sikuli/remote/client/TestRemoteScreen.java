package org.sikuli.remote.client;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.remote.client.RemoteScreen;



public class TestRemoteScreen {
	
	private static String psc;
	
	private static int timeout;
	
	@BeforeClass
	public static void setUp() throws Exception {
		psc = new File(Thread.currentThread().getContextClassLoader().getResource("image/start.png").toURI()).getAbsolutePath();
		timeout = 5000;
	}
	
	@Test
	public void testClick() throws Exception {
		RemoteScreen rs = new RemoteScreen("localhost");
		rs.click(psc);
	}
	
	@Test
	public void testFind() throws Exception {
		RemoteScreen rs = new RemoteScreen("localhost");
		rs.find(psc);
	}
	
	@Test
	public void testDoubleClick() throws Exception {
		RemoteScreen rs = new RemoteScreen("localhost");
		rs.doubleClick(psc);
	}
	
	@Test
	public void testWaitUntil() throws Exception {
		RemoteScreen rs = new RemoteScreen("localhost");
		rs.waitUntil(psc, timeout);
	}
	
	@Test
	public void testSetMinSimilarity() throws Exception {
		RemoteScreen rs = new RemoteScreen("localhost");
		rs.setMinSimilarity(0.9);
	}
	

}
