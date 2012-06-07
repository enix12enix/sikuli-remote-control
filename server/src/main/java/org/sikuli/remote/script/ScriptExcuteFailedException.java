package org.sikuli.remote.script;

public class ScriptExcuteFailedException extends Exception {
	
	public static final ScriptExcuteFailedException NO_STACKTRACE_FAILURE = new ScriptExcuteFailedException("No detailed information");
	
	public ScriptExcuteFailedException(String msg) {
		super(msg);
	}
	
	public ScriptExcuteFailedException(){};

}
