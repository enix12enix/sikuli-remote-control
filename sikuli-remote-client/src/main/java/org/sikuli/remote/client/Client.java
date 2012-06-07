package org.sikuli.remote.client;

import java.io.OutputStreamWriter;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;

import org.sikuli.remote.script.SikuliScript;
import org.w3c.dom.Document;


public class Client {
	
	private URL endPoint;
	
	public Client(String server, int port) throws Exception {
		endPoint = new URL("http://" + server + ":" + String.valueOf(port) + "/script.do");
	}
	
	public Client(String server) throws Exception {
		this(server, 9000);
	}
	
	public SikuliScript call(String urlData) throws Exception {
		
		URLConnection conn = endPoint.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(urlData);
		wr.flush();
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream());
		SikuliScript script = new SikuliScript(SikuliScript.retriveName(doc), SikuliScript.retriveStatus(doc), SikuliScript.retriveError(doc));
		
		return script;
	}

}
