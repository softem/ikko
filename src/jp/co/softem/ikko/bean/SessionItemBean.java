package jp.co.softem.ikko.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class SessionItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Calendar currentCal;

	public SessionItemBean() {
	}

	public Calendar getCurrentCal() {
		return currentCal;
	}

	public void setCurrentCal(Calendar currentCal) {
		this.currentCal = currentCal;
	}

}
