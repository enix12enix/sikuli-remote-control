package org.sikuli.remote.client;

public class RemoteScreen {
	
	private Client client;
	
	private static final int DEFAULT_TIMEOUT = 60;
	
	private static final double DEFAULT_MIN_SIMILARITY = 0.7;
	
	public RemoteScreen(String server, int port) throws Exception {
		this.client = new Client(server, port);
		setDefaultMinSimilarity();
	}
	
	public RemoteScreen(String server) throws Exception {
		this.client = new Client(server);
		setDefaultMinSimilarity();
	}
	
	public void click(String psc, int timeout) throws Exception {
		SikuliAction.click.doAction(client, new String[] {psc, String.valueOf(timeout)});
	}
	
	public void click(String psc) throws Exception {
		click(psc, DEFAULT_TIMEOUT);
	}

	public void find(String psc) throws Exception {
		SikuliAction.find.doAction(client, new String[] {psc});
	}
	
	public void doubleClick(String psc) throws Exception {
		SikuliAction.doubleClick.doAction(client, new String[] {psc});
	}
	
	public void rightClick(String psc) throws Exception {
		SikuliAction.rightClick.doAction(client, new String[] {psc});
	}
	
	public void waitUntil(String psc) throws Exception {
		SikuliAction.waitUntil.doAction(client, new String[]{psc, String.valueOf(DEFAULT_TIMEOUT)});
	}
	
	public void waitUntil(String psc, int timeout) throws Exception {
		SikuliAction.waitUntil.doAction(client, new String[]{psc, String.valueOf(timeout)});
	}
	
	public void paste(String psc, String content) throws Exception {
		SikuliAction.paste.doAction(client, new String[] {psc, content});
	}
	
	public void appFocus(String content) throws Exception {
		SikuliAction.focus.doAction(client, new String[] {content});
	}
	
	public void pageDown() throws Exception {
		SikuliAction.pageDown.doAction(client, new String[]{});
	}
	
	public void setMinSimilarity(double similarity) throws Exception {
		if (similarity < 0 || similarity > 1) {
			throw new IllegalArgumentException("similarity should be from 0 to 1");
		}
		SikuliAction.setMinSimilarity.doAction(client, new String[] {String.valueOf(similarity)});
	}
	
	public void setDefaultMinSimilarity() throws Exception {
		setMinSimilarity(DEFAULT_MIN_SIMILARITY);
	}
	
	public void type(String psc, String content) throws Exception {
		SikuliAction.type.doAction(client, new String[] {psc, content});
	}
	

}
