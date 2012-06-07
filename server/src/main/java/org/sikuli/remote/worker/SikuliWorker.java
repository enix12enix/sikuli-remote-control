package org.sikuli.remote.worker;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.sikuli.script.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.sikuli.remote.script.Script;
import org.sikuli.remote.script.ScriptExcuteFailedException;
import org.sikuli.remote.script.ScriptStatus;

public class SikuliWorker implements Worker {

	private static Logger logger = LoggerFactory.getLogger(SikuliWorker.class);
	
	@Override
	public void work(Script script) {
		logger.info("Executing Sikuli Script Begins");
        PythonInterpreter pi = ScriptRunner.getInstance(null).getPythonInterpreter();
		
        try {
			Map<String, String> args = script.getArgs();
			
			if (args != null) {
				for (Entry<String, String> arg : args.entrySet()) {
					pi.set(arg.getKey(), new PyString(arg.getValue()));
				}
			} 
		} catch ( IllegalArgumentException ex) {
			logger.warn("Failed to execute sikuli script");
			ex.printStackTrace();
			script.setStatus(ScriptStatus.FAIL);
			script.setFailure_reason(ex);
			return;
		}
		
		try {
			ArrayList<String> source = script.getSource();
			for (String pycode : source) {
				pi.exec(pycode);
			}	
			script.setStatus(ScriptStatus.PASS);
		} catch (Exception ex) {
			script.setStatus(ScriptStatus.FAIL);
			String rootCauseMsg = StringEscapeUtils.escapeXml(ExceptionUtils.getRootCauseStackTrace(ex)[0]);
			script.setFailure_reason(new ScriptExcuteFailedException(rootCauseMsg));
		}
		logger.info("Sikuli Script Completed");
		
	}

	
}
