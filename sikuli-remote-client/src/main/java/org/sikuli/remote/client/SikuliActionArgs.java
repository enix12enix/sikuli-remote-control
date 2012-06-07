package org.sikuli.remote.client;

import java.util.HashMap;

public enum SikuliActionArgs {
	psc,
	timeout,
	content,
	similarity;
	
	public void setArgs(HashMap<String, String> args, String value) {
		if (value != null) {
			args.put(name(), value);
		}
	}
	
}
