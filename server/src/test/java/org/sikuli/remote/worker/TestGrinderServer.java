package org.sikuli.remote.worker;

import static junit.framework.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.remote.server.SikuliServer;


public class TestGrinderServer {
	
	private SikuliServer serv;
	
	@Before
	public void setUp() throws Exception {
		serv = new SikuliServer();
		serv.servletRegister();
		serv.getServer().start();
	}
	
	@Test
	public void testGrinderServer() throws Exception{
		String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("sikuli", "UTF-8");
		data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8");
		data += "&" + URLEncoder.encode("cmd0", "UTF-8") + "=" + URLEncoder.encode("from sikuli.Sikuli import *", "UTF-8");
		data += "&" + URLEncoder.encode("cmd1", "UTF-8") + "=" + URLEncoder.encode("print a", "UTF-8");
		data += "&" + URLEncoder.encode("args", "UTF-8") + "=" + URLEncoder.encode("|a=3|", "UTF-8");
			
		URL url = new URL("http://localhost:9000/script.do");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String xml = "";
		String line;
		while ((line = rd.readLine()) != null) {
			xml += StringEscapeUtils.unescapeXml(line);
		}
		wr.close();
		rd.close();
	
		assertEquals("<script><name>test</name><status>PASS</status></script>", xml);
	}
	
	@After
	public void tearDown() throws Exception {
		serv.getServer().stop();
	}

}
