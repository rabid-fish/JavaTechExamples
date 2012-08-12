package com.github.rabid_fish.content;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/servlet-context.xml")
public class ContentRepositoryTest {

	@Autowired
	ContentRepository repository;

	@Test
	public void testSave() {

		Content content1 = new Content();
		content1.setText("example content");
		content1 = repository.save(content1);

		Content content2 = repository.findOne(content1.getId());
		
		assertEquals(content1.getText(), content2.getText());
	}

}
