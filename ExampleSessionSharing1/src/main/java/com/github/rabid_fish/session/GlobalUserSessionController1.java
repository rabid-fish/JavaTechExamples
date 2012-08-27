package com.github.rabid_fish.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/session")
public class GlobalUserSessionController1 {

	@Autowired
	GlobalUserSessionRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public String formDisplay(@CookieValue("JSESSIONID") String sessionId, Model model) {
		
		GlobalUserSession session = repository.findOrCreate(sessionId);
		Map<String, String> map = getMapFromSession(session);
		
		List<String> list = new ArrayList<String>();
		Set<String> keys = map.keySet();
		for (String key : keys) 
		{
			list.add(key + ":" + map.get(key) + "\n");
		}
		
		model.addAttribute("list", list);
		
		return "form";
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String formSubmit(HttpSession httpSession, @RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
//
//		GlobalUserSession session = repository.find(httpSession);
//		Map<String, String> map = getMapFromSession(session);
//		putRequestParametersInMap(map, key, value);
//		updateMapInSession(session, map);
//		
//		return "redirect:session";
//	}
//
//	@RequestMapping(value = "/clear", method = RequestMethod.GET)
//	public String clear(HttpSession httpSession, Model model) {
//		
//		GlobalUserSession session = repository.find(httpSession);
//		Map<String, String> map = getMapFromSession(session);
//		map.clear();
//		updateMapInSession(session, map);
//		
//		return "redirect:../session";
//	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getMapFromSession(GlobalUserSession session) 
	{
		Map<String, String> map = new HashMap<String, String>();
		
		byte[] serializedValues = session.getSerializedValues();
		if (serializedValues != null)
		{
			map = (Map<String, String>) SerializationUtils.deserialize(serializedValues);
		}
		
		return map;
	}

//	private void updateMapInSession(GlobalUserSession session, Map<String, String> map) 
//	{
//		byte[] serializedValues = SerializationUtils.serialize(map);
//		session.setSerializedValues(serializedValues);
//		repository.update(session);
//	}
//
//	protected void putRequestParametersInMap(Map<String, String> map, String key, String value) 
//	{
//		if (key == null || key.length() == 0 || value == null || value.length() == 0) {
//			return;
//		}
//		
//		map.put(key, value);
//	}
}
