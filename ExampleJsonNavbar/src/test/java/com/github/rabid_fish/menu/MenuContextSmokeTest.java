package com.github.rabid_fish.menu;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.menu.MenuItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/servlet-context.xml")
public class MenuContextSmokeTest {

	@Test
	public void test() {
		System.out.println("Success reading context file");
	}

	@Test
	public void testReadFile() throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream("/menu.json");
		MenuItem[] menu = mapper.readValue(stream, MenuItem[].class);
		System.out.println(menu[0].getTitle());
	}

}
