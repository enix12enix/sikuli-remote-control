package org.sikuli.remote.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.sikuli.remote.script.ScriptStatus;
import org.sikuli.remote.script.SikuliScript;


public enum SikuliAction {
	
	click(new SikuliActionArgs[]{SikuliActionArgs.psc, SikuliActionArgs.timeout}),
	find(new SikuliActionArgs[]{SikuliActionArgs.psc}),
	doubleClick(new SikuliActionArgs[]{SikuliActionArgs.psc}),
	rightClick(new SikuliActionArgs[]{SikuliActionArgs.psc, SikuliActionArgs.timeout}),
	waitUntil(new SikuliActionArgs[]{SikuliActionArgs.psc, SikuliActionArgs.timeout}),
	paste(new SikuliActionArgs[]{SikuliActionArgs.psc, SikuliActionArgs.content}),
	focus(new SikuliActionArgs[]{SikuliActionArgs.content}),
	pageDown(new SikuliActionArgs[]{}),
	type(new SikuliActionArgs[]{SikuliActionArgs.psc, SikuliActionArgs.content}),
	setMinSimilarity(new SikuliActionArgs[]{SikuliActionArgs.similarity});
	
	private ArrayList<String> command;
	
	private SikuliActionArgs[] argNames;
	
	private SikuliAction(SikuliActionArgs[] argNames) {
		this.argNames = argNames;
		command = new ArrayList<String>();
		InputStream is = this.getClass().getResourceAsStream("/py/" + name() + ".py");
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while ((line = rd.readLine()) != null) {
				command.add(line);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to read Sikuli action " + name());
		}
	}
	
	private SikuliAction() {
		this(null);
	}

	public String getUrlData(HashMap<String, String> args) throws UnsupportedEncodingException {
		if (args != null && argNames != null) {
			for (SikuliActionArgs arg : argNames) {
				if (!args.containsKey(arg.name())) {
					throw new IllegalArgumentException(name() + " :: " + arg.name() + " is required!");
				}
			}
		}
		return new UrlDataBuilder(name()).command(command).args(args).toString();
	}
	
	public void doAction(Client client, String[] values) throws Exception {
		HashMap<String, String> container = new HashMap<String, String>();
		if (values.length != argNames.length) {
			throw new IllegalArgumentException("The quantity of parameters should be " 
					+ argNames.length + "! But is " + values.length);
		}
		for (int i = 0; i < values.length; i++) {
			argNames[i].setArgs(container, values[i]);
		}
		SikuliScript script = client.call(getUrlData(container));
		if (script.getStatus() == ScriptStatus.FAIL) {
			throw script.getFailure_reason();
		}
		
	}
	
    private static class UrlDataBuilder {
		
        private StringBuilder builder;
		
		public UrlDataBuilder(String name) throws UnsupportedEncodingException {
			builder = new StringBuilder();
			builder.append("type=" + URLEncoder.encode("sikuli", "UTF-8"));
			builder.append("&name=" + URLEncoder.encode(name, "UTF-8"));
		}
		
		public UrlDataBuilder command(ArrayList<String> command) throws UnsupportedEncodingException {
			if (command == null || command.size() == 0) {
				throw new IllegalArgumentException("command is null!");
			}
			int i = 0;
			for (String c : command) {
				builder.append("&cmd").append(String.valueOf(i)).append("=").append(URLEncoder.encode(c, "UTF-8"));
				i++;
			}
			return this;
		}
		
		public UrlDataBuilder args(HashMap<String, String> args) throws UnsupportedEncodingException {
			if (args == null || args.isEmpty()) {
				return this;
			}
			
			builder.append("&args=").append(buildArgs(args));
			return this;
		}
		
		private String buildArgs(HashMap<String, String> args) throws UnsupportedEncodingException {
			StringBuilder builder = new StringBuilder();
			builder.append("|");
			for (Entry<String, String> arg : args.entrySet()) {
				builder.append(arg.getKey()).append("=").append(arg.getValue()).append("|");
			}
			return URLEncoder.encode(builder.toString(), "UTF-8");
		}
		
		@Override
		public String toString() {
			return builder.toString();
		}
	}
	

}
