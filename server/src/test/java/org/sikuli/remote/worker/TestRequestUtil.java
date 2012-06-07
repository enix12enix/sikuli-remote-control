package org.sikuli.remote.worker;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import org.sikuli.remote.script.ScriptType;


public class TestRequestUtil {
    private static HttpServletRequest mock;
	
	@Test
	public void testGetCommand() throws UnsupportedEncodingException {
		mock = createMock(HttpServletRequest.class);
		expect(mock.getParameter("cmd0")).andReturn("test0");
		expect(mock.getParameter("cmd1")).andReturn("test1");
		expect(mock.getParameter("cmd2")).andReturn(null);
		replay(mock);
		ArrayList<String> cmds = RequestUtil.getCommand(mock);
		assertEquals("test0", cmds.get(0));
		assertEquals("test1", cmds.get(1));
		verify(mock);
	}
	
	@Test
	public void testGetScriptName() throws UnsupportedEncodingException {
		mock = createMock(HttpServletRequest.class);
		expect(mock.getParameter("name")).andReturn("test");
		replay(mock);
		assertEquals("test", RequestUtil.getScriptName(mock));
		verify(mock);
	}
	
	@Test
	public void testGetArgs() throws UnsupportedEncodingException {
		mock = createMock(HttpServletRequest.class);
		expect(mock.getParameter("args")).andReturn("|arg1=1|arg2=2|");
		replay(mock);
		HashMap<String, String> args = RequestUtil.getArgs(mock);
		assertEquals("1", args.get("arg1"));
		assertEquals("2", args.get("arg2"));
		verify(mock);
	}
	
	@Test
	public void testGetScriptType() throws UnsupportedEncodingException {
		mock = createMock(HttpServletRequest.class);
		expect(mock.getParameter("type")).andReturn("sikuli");
		replay(mock);
		assertEquals(ScriptType.sikuli, RequestUtil.getScriptType(mock));
		verify(mock);
	}
}
