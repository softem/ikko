package jp.co.softem.ikko.core;

import javax.inject.Inject;

public abstract class BasePage {

	protected static final String TEMPLATE = "/WEB-INF/pages/template.jsp";

	@Inject
	protected PageInfo pageInfo;

}
