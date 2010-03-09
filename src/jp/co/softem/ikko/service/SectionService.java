package jp.co.softem.ikko.service;

import javax.ejb.Stateless;

import jp.co.softem.ikko.core.eis.Section;

@Stateless
public class SectionService extends BaseService<Section> {

	private static final long serialVersionUID = 1L;

	public SectionService() {
		super(Section.class);
	}

}
