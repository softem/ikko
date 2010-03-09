package jp.co.softem.ikko.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import jp.co.softem.ikko.common.Utils;
import jp.co.softem.ikko.core.eis.Section;
import jp.co.softem.ikko.service.SectionService;
import flexjson.JSONSerializer;

@ManagedBean
@RequestScoped
public class SectionAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private SectionService service;

	private Section section;

	private List<Section> sectionList;

	private String response;

	@PostConstruct
	public void init() {
		section = new Section();
		sectionList = service.findAll();
	}

	public Section getSection() {
		return section;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setService(SectionService service) {
		this.service = service;
	}

	public void save(ActionEvent ae) {
		response = new JSONSerializer().serialize(section);
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void validate(ComponentSystemEvent e) {
		UIForm form = (UIForm) e.getComponent();
		String id = (String) Utils.getUIInputValue(form, "id");
		String sectionName = (String) Utils
				.getUIInputValue(form, "sectionName");
		System.out.println(id + "," + sectionName);
		// Employee emp = service.find(loginId, password);
		// if (emp == null) {
		// FacesContext fc = FacesContext.getCurrentInstance();
		// String message = RB.getString("errors.login.failed");
		// fc.addMessage(form.getClientId(), new FacesMessage(message));
		// fc.renderResponse();
		// }
	}

}
