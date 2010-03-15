package jp.co.softem.ikko.core;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class JsonResult {

	private final ResourceBundle rs = ResourceBundle.getBundle("Messages");

	private final Map<String, String> messages = new ConcurrentHashMap<String, String>();

	private boolean error;

	public void put(String key, String value) {
		error = true;
		messages.put(key, rs.getString(value));
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public boolean isError() {
		return error;
	}

}
