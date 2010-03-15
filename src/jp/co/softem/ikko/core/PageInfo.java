package jp.co.softem.ikko.core;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String page;
	private String message;
	private final ResourceBundle rs = ResourceBundle.getBundle("Messages");

	public PageInfo() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String key) {
		this.title = rs.getString("title." + key);
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		setTitle(page);
		this.page = page + ".jsp";
	}

	public void setMessage(String key) {
		this.message = rs.getString(key);
	}

	public String getMessage() {
		return message;
	}

}
