class DummyClass_112677 {
	@Test
	public void testUpperCaseFieldNames() throws Exception {
		Field[] fields = Javax.class.getDeclaredFields();
		UpperCaseFieldDatabaseType ucDatabaseType = new UpperCaseFieldDatabaseType();
		for (Field field : fields) {
			DatabaseFieldConfig config = new JavaxPersistenceImpl().createFieldConfig(ucDatabaseType, field);
			if (field.getName().equals("id")) {
				assertTrue(config.isId());
				assertFalse(config.isGeneratedId());
				assertEquals("ID", config.getFieldName());
			}
		}
	}

}