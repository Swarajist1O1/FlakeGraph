class DummyClass_112676 {
	@Test
	public void testTableName() {
		JavaxPersistenceConfigurer configurer = new JavaxPersistenceImpl();
		assertEquals(JAVAX_ENTITY_NAME, configurer.getEntityName(Javax.class));
		assertNull(configurer.getEntityName(EntityNoName.class));
	}

}