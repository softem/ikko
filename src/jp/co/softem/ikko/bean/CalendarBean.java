package jp.co.softem.ikko.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class CalendarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Calendar currentCal;

	public CalendarBean() {
	}

	public Calendar getCurrentCal() {
		return currentCal;
	}

	public void setCurrentCal(Calendar currentCal) {
		this.currentCal = currentCal;
	}

}
