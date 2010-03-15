package jp.co.softem.ikko.page;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.Section;
import jp.co.softem.ikko.service.SectionService;

import org.t2framework.commons.annotation.composite.RequestScope;
import org.t2framework.t2.action.ErrorInfo;
import org.t2framework.t2.annotation.composite.GET;
import org.t2framework.t2.annotation.composite.POST;
import org.t2framework.t2.annotation.core.ActionPath;
import org.t2framework.t2.annotation.core.Ajax;
import org.t2framework.t2.annotation.core.Default;
import org.t2framework.t2.annotation.core.Form;
import org.t2framework.t2.annotation.core.Page;
import org.t2framework.t2.navigation.Forward;
import org.t2framework.t2.navigation.Json;
import org.t2framework.t2.spi.Navigation;

@RequestScope
@Named
@Page("section")
public class SectionPage extends BasePage {

	@EJB
	SectionService service;

	@Default
	public Navigation index() {
		pageInfo.setPage("section");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form Section section, ErrorInfo info) {
		JsonResult result = new JsonResult();
		if (section.getId() > 0) {
			service.update(section);
		} else {
			if (section.getSectionName() == null
					|| section.getSectionName().length() == 0) {
				result.put("sectionName", "message.required");
			}
			if (!result.isError()) {
				service.insert(section);
			}
		}
		return Json.convert(result);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation delete(@Form Section section, ErrorInfo info) {
		service.delete(section.getId());
		return Json.convert(section);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/section_table.jsp");
	}

	public List<Section> getList() {
		return service.findAll();
	}

	public Section getSection() {
		return new Section();
	}

}
