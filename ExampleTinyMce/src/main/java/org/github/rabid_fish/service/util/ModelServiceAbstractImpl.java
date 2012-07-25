package org.github.rabid_fish.service.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.github.rabid_fish.common.CommonModelService;

public abstract class ModelServiceAbstractImpl<T extends ServiceEntity> implements CommonModelService<T> {

	@SuppressWarnings("rawtypes")
	private static Map<Class, PersistenceTable> database = new HashMap<Class, PersistenceTable>();
	
	private final Class<T> c;

	protected abstract Class<T> getServiceEntityClass();

	public ModelServiceAbstractImpl() {
		this.c = getServiceEntityClass();
		database.put(c, new PersistenceTable<T>());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return database.get(c).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T read(Long id) {
		return (T) database.get(c).read(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized void insert(T item) {
		database.get(c).insert(item, item.getId());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update(T item) {
		database.get(c).update(item, item.getId());
	}

}
