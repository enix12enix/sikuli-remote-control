package org.sikuli.remote.script;

import java.lang.reflect.Constructor;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SikuliScript {
	
	private String name;
	
	private ScriptStatus status;
	
	private Exception failure_reason;
	
	public SikuliScript(String name, ScriptStatus status, Exception failure_reason) {
		this.name = name;
		this.status = status;
		this.failure_reason = failure_reason;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
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

	public static String retriveName(Document doc) {
		return doc.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
	}
	
	public static ScriptStatus retriveStatus(Document doc) {
		String value = doc.getElementsByTagName("status").item(0).getFirstChild().getNodeValue();
		return ScriptStatus.valueOf(value);
	}
	
	public static Exception retriveError(Document doc) throws Exception {
		NodeList excepts = doc.getElementsByTagName("exception");
		if (excepts.getLength() == 0) {
			return null;
		}
		String exceptName = excepts.item(0).getFirstChild().getNodeValue();
		Class<?> clz =  Class.forName(exceptName);
		NodeList messages = doc.getElementsByTagName("message");
		if (messages.getLength() == 0) {
			return (Exception) clz.newInstance();
		} else {
			Constructor<?> con = clz.getConstructor(String.class);
			return (Exception) con.newInstance(StringEscapeUtils.unescapeXml(messages.item(0).getFirstChild().getNodeValue()));
		}
	}

}
