package org.sikuli.remote.worker;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import org.sikuli.remote.script.ScriptType;

public abstract class RequestUtil {
	
	public static ArrayList<String> getCommand(HttpServletRequest req) throws UnsupportedEncodingException {
        ArrayList<String> source = new ArrayList<String>();
		
		for (int i = 0; ; i++) {
			String cmd = req.getParameter("cmd" + String.valueOf(i));
			if (cmd == null) {
				break;
			}
			source.add(URLDecoder.decode(cmd, "UTF-8"));
		}
		
		return source.size() == 0 ? null : source;
	}

	public static HashMap<String, String> getArgs(HttpServletRequest req) throws UnsupportedEncodingException {
		String param = req.getParameter("args");
		if (param == null) {
			return null;
		}
		
		HashMap<String, String> args = new HashMap<String, String>();
		
		String[] parts = StringUtils.split(URLDecoder.decode(param, "UTF-8"), "|");
		for (int i = 0; i < parts.length; i++) {
			String[] p = parts[i].split("=");
			if (p.length != 2) {
				throw new IllegalArgumentException("The format of argument is invalid!");
			}
			args.put(p[0], p[1]);
		}
		
		return args.size() == 0 ? null : args;
	}
	
	public static ScriptType getScriptType(HttpServletRequest req) throws UnsupportedEncodingException {
		String type = req.getParameter("type");
		if (type == null) {
			throw new IllegalArgumentException("Please specify type!");
		}
		return ScriptType.valueOf(URLDecoder.decode(type, "UTF-8"));
	}
	
	public static String getScriptName(HttpServletRequest req) throws UnsupportedEncodingException {
		String name = req.getParameter("name");
		return name == null ? null : URLDecoder.decode(name, "UTF-8");
	}


}
