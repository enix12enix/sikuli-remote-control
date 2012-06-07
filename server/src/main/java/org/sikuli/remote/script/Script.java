package org.sikuli.remote.script;

import java.util.ArrayList;
import java.util.HashMap;

public class Script {
	
	private String name;
	
	private ScriptStatus status;
	
	private Exception failure_reason;
	
	private ArrayList<String> source;
	
	private HashMap<String, String> args;
	
	public Script(String name) {
		this.name = name;
		
	}
	
	public ScriptStatus getStatus() {
		return status;
	}

	public void setStatus(ScriptStatus status) {
		this.status = status;
	}

	public Exception getFailure_reason() {
		return failure_reason;
	}

	public void setFailure_reason(Exception failure_reason) {
		this.failure_reason = failure_reason;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<String> getSource() {
		return source;
	}

	public void setSource(ArrayList<String> source) {
		this.source = source;
	}

	public HashMap<String, String> getArgs() {
		return args;
	}

	public void setArgs(HashMap<String, String> args) {
		this.args = args;
	}

	public String toString() {
        StringBuilder builder = new StringBuilder();
		
		builder.append("<script>");
		builder.append("<name>");
		builder.append(name);
		builder.append("</name>");
		builder.append("<status>");
		builder.append(status.name());
		builder.append("</status>");
		if (failure_reason != null) {
			builder.append("<failure>");
			builder.append("<exception>" + failure_reason.getClass().getCanonicalName() + "</exception>");
			if (failure_reason.getMessage() != null) {
				builder.append("<message>" + failure_reason.getMessage() + "</message>");
			}
			builder.append("</failure>");
		}
		builder.append("</script>");
		
		return builder.toString();
	}
	

}
