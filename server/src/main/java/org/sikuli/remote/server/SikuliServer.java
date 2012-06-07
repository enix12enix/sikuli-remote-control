package org.sikuli.remote.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import org.sikuli.remote.server.servlet.ScriptServlet;

public class SikuliServer {
	
	private int port = 9000;
	
	private Server server;
	
	private Context context;
	
	public void servletRegister() {
		server = new Server(port);
		
		context = new Context(server,"/",Context.SESSIONS);
		context.addServlet(new ServletHolder(new ScriptServlet()), "/script.do");
	}
	
	private void boot() throws Exception {
		server.start();
		server.join();
	}
	
	public void startup() throws Exception {
		servletRegister();
		boot();
	}
	
	public Server getServer() {
		return server;
	}
	
	public void stop() throws Exception {
		server.stop();
	}
	
	public static void main(String[] args) throws Exception {
		SikuliServer server = new SikuliServer();
		server.startup();
	}

}
