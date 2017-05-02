package com.github.rabidfish;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractRepository<T extends HasId> {

	protected static final int INVALID_INDEX = -1;
	
	protected AtomicLong sequence;
	protected List<T> list;
	
	public AbstractRepository() {
		this.sequence = new AtomicLong(0);
		this.list = new ArrayList<>();
	}
	
	protected int findObjectIndex(Long id) {
		
		int index = INVALID_INDEX;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				index = i;
				break;
			}
		}
		
		return index;
	}

	public List<T> list() {
		return list;
	}

	public void save(T o) {
		
		if (o.getId() != null) {
			int index = findObjectIndex(o.getId());
			if (index == INVALID_INDEX) {
				throw new RuntimeException("Unable to locate item for update");
			}
			
			list.remove(index);
		} else {
			long id = sequence.incrementAndGet();
			o.setId(id);
		}
		
		list.add(o);
	}
	
	public void reset() {
		list.clear();
	}
}
