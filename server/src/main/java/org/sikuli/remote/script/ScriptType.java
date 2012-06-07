package org.sikuli.remote.script;

import org.sikuli.remote.worker.Worker;

public enum ScriptType {
	
	sikuli("org.sikuli.remote.worker.SikuliWorker");
	
	private String className;
	
	private ScriptType(String className) {
		this.className = className;
	}
	
	@SuppressWarnings("unchecked")
	public Class<? extends Worker> getWorker() throws ClassNotFoundException {
		return (Class<? extends Worker>) Class.forName(className);
	}

}
