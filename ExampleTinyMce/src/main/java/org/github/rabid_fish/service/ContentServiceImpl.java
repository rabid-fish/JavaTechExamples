package org.github.rabid_fish.service;

import org.github.rabid_fish.common.CommonModelService;
import org.github.rabid_fish.domain.Content;
import org.github.rabid_fish.service.util.ModelServiceAbstractImpl;
import org.springframework.stereotype.Service;

@Service(value = "ContentServiceImpl")
public class ContentServiceImpl extends ModelServiceAbstractImpl<Content> implements CommonModelService<Content> {

	@Override
	protected Class<Content> getServiceEntityClass() {
		return Content.class;
	}

}
