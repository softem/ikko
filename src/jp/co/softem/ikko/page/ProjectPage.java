package jp.co.softem.ikko.page;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import jp.co.softem.ikko.core.BasePage;
import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.Project;
import jp.co.softem.ikko.service.ProjectService;

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
@Page("project")
public class ProjectPage extends BasePage {

	@EJB
	ProjectService service;

	@Default
	public Navigation index() {
		pageInfo.setPage("project");
		return Forward.to(TEMPLATE);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation save(@Form Project project, ErrorInfo info) {
		JsonResult result = service.save(project, info);
		return Json.convert(result);
	}

	@POST
	@ActionPath
	@Ajax
	public Navigation delete(@Form Project project, ErrorInfo info) {
		JsonResult result = new JsonResult();
		service.delete(project.getId());
		return Json.convert(result);
	}

	@GET
	@Ajax
	public Navigation table() {
		return Forward.to("/WEB-INF/pages/project_table.jsp");
	}

	public List<Project> getList() {
		return service.findAll();
	}

	public Project getProject() {
		return new Project();
	}

}
