package jp.co.softem.ikko.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import jp.co.softem.ikko.core.eis.Section;
import jp.co.softem.ikko.service.SectionService;

@ManagedBean
@RequestScoped
public class SectionAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private SectionService service;

	private Section section;

	private List<Section> sectionList;

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

}
