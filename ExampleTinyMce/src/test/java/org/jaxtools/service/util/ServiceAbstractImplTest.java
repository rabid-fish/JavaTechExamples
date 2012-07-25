package org.jaxtools.service.util;

import static org.junit.Assert.*;

import org.github.rabid_fish.service.util.ModelServiceAbstractImpl;
import org.junit.Before;
import org.junit.Test;

public class ServiceAbstractImplTest {

	private static final long TEST_ENTITY_ID = 1L;
	private static final ServiceEntityMock TEST_ENTITY = new ServiceEntityMock(TEST_ENTITY_ID);

	public class ModelPersistenceImplMock extends ModelServiceAbstractImpl<ServiceEntityMock> {

		@Override
		protected Class<ServiceEntityMock> getServiceEntityClass() {
			return ServiceEntityMock.class;
		}
		
	}

	private ModelServiceAbstractImpl<ServiceEntityMock> service;

	@Before
	public void setUp() {
		service = new ModelPersistenceImplMock();
	}
	
	@Test
	public void testList() {
		assertTrue(service.list().size() == 0);
	}
	
	@Test
	public void testListAfterInsert() {
		service.insert(TEST_ENTITY);
		assertTrue(service.list().size() == 1);
		assertTrue(service.list().get(0) == TEST_ENTITY);
	}
	
	@Test
	public void testRead() {
		service.insert(TEST_ENTITY);
		assertTrue(service.read(TEST_ENTITY_ID) == TEST_ENTITY);
	}
	
	@Test
	public void testInsert() {
		service.insert(TEST_ENTITY);
	}
	
	@Test
	public void testUpdate() {
		
		ServiceEntityMock mock1 = new ServiceEntityMock(TEST_ENTITY_ID);
		mock1.setValue("Value1");
		service.insert(mock1);
		
		ServiceEntityMock mock2 = new ServiceEntityMock(TEST_ENTITY_ID);
		mock2.setValue("Value2");
		service.update(mock2);
		
		assertTrue(service.read(TEST_ENTITY_ID).getValue().equals("Value2"));
	}
	
}
