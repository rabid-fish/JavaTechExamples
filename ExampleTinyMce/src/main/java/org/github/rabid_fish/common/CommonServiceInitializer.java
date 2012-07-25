package org.github.rabid_fish.common;

import java.util.Date;

import org.github.rabid_fish.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "CommonServiceInitializer")
public class CommonServiceInitializer {

	@Autowired
	@Qualifier("ContentServiceImpl")
	public CommonModelService<Content> contentService;

	public void init() {

		Content content = new Content();
		content.setId(0l);
		content.setUserId(0l);
		content.setCreatedDate(new Date());
		content.setText("Sample Text");
		contentService.insert(content);
	}
}
