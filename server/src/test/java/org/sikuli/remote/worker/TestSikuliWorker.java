package org.sikuli.remote.worker;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.*;

import org.junit.Test;

import org.sikuli.remote.script.Script;
import org.sikuli.remote.script.ScriptExcuteFailedException;
import org.sikuli.remote.script.ScriptStatus;

public class TestSikuliWorker {
	
	
	@Test
	public void testExecuteScriptSuccessful1() {	
		Script script = new Script("test");
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("a", "3");
		script.setArgs(args);
		ArrayList<String> source = new ArrayList<String>();
		source.add("from sikuli.Sikuli import *");
		source.add("print a");
		script.setSource(source);
		
		Worker worker = new SikuliWorker();
		worker.work(script);
		
		assertEquals(ScriptStatus.PASS, script.getStatus());
	}
	
	
	@Test
	public void testExecuteScriptSuccessful2() {	
		Script script = new Script("test");
		ArrayList<String> source = new ArrayList<String>();
		source.add("from sikuli.Sikuli import *");
		script.setSource(source);
		
		Worker worker = new SikuliWorker();
		worker.work(script);
		
		assertEquals(ScriptStatus.PASS, script.getStatus());
	}
	
	@Test
	public void testExcuteScriptFailed() {
		Script script = new Script("test");
		ArrayList<String> source = new ArrayList<String>();
		source.add("raise NameError");
		script.setSource(source);
		
		Worker worker = new SikuliWorker();
		worker.work(script);
		
		assertEquals(ScriptStatus.FAIL, script.getStatus());
		assertTrue(script.getFailure_reason() instanceof ScriptExcuteFailedException);
	}
}
