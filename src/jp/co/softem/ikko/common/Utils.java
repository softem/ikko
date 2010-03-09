package jp.co.softem.ikko.common;

import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class Utils {

	public static void addFlush(String key, String value) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put(key, value);
	}

	public static Object getUIInputValue(UIForm form, String name) {
		return ((UIInput) form.findComponent(name)).getValue();
	}

	public static void putSession(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(key, value);
	}

}
