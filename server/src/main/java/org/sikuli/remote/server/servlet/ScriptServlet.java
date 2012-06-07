package org.sikuli.remote.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sikuli.remote.script.Script;
import org.sikuli.remote.script.ScriptType;
import static org.sikuli.remote.worker.RequestUtil.*;
import org.sikuli.remote.worker.Worker;

@SuppressWarnings("serial")
public class ScriptServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ScriptType type = getScriptType(req);
		try {
			Worker worker = type.getWorker().newInstance();
		    Script script = new Script(getScriptName(req));
		    script.setArgs(getArgs(req));
		    script.setSource(getCommand(req));
		    
		    worker.work(script);
		    
		    resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().print(script);    
		} catch (Exception e) {
			resp.sendError(500);
		}
	}

	
	
}
