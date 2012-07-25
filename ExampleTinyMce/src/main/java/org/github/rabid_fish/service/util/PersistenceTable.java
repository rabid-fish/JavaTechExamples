package org.github.rabid_fish.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenceTable<T> {

	private List<T> list = new ArrayList<T>();
	private Map<Long, T> map = new HashMap<Long, T>();

	public List<T> list() {
		return list;
	}
	
	public T read(Long id) {
		return map.get(id);
	}

	public synchronized void insert(T item, Long id) {
		list.add(item);
		map.put(id, item);
	}

	public synchronized void update(T item, Long id) {
		
		int index = 0;
		T old = map.get(id);
		for (; index < list.size(); index++) {
			// Check to see if the list and map object refs are the same
			// We are NOT checking to see if the values are the same
			if (list.get(index) == old) {
				break;
			}
		}
		
		list.remove(index);
		list.add(index, item);
		
		map.put(id, item);
	}

}
