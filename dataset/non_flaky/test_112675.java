class DummyClass_112675 {
	@Test
	public void testConversions() throws Exception {
		Field[] fields = Javax.class.getDeclaredFields();
		for (Field field : fields) {
			DatabaseFieldConfig config = new JavaxPersistenceImpl().createFieldConfig(databaseType, field);
			if (field.getName().equals("generatedId")) {
				assertFalse(config.isId());
				assertTrue(config.isGeneratedId());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertEquals(field.getName(), config.getFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("id")) {
				assertTrue(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertEquals(field.getName(), config.getFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("stuff")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertEquals(field.getName(), config.getFieldName());
				assertEquals(STUFF_FIELD_NAME, config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("unknown")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getDataPersister());
				assertEquals(field.getName(), config.getFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("foreignManyToOne")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertTrue(config.isForeign());
				assertFalse(config.isForeignCollection());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getDataPersister());
				assertEquals(field.getName(), config.getFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("foreignOneToOne")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertTrue(config.isForeign());
				assertFalse(config.isForeignCollection());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getDataPersister());
				assertEquals(field.getName(), config.getFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("foreignOneToMany")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertTrue(config.isForeignCollection());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getDataPersister());
				assertEquals(field.getName(), config.getFieldName());
				assertNull(config.getForeignCollectionForeignFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("mappedByField")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertTrue(config.isForeignCollection());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getDataPersister());
				assertEquals(field.getName(), config.getFieldName());
				assertEquals(MAPPED_BY_FIELD_NAME, config.getForeignCollectionForeignFieldName());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("joinFieldName")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertTrue(config.isForeign());
				assertFalse(config.isForeignCollection());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getDataPersister());
				assertEquals(field.getName(), config.getFieldName());
				assertEquals(JOIN_FIELD_NAME, config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("columnDefinition")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertFalse(config.isVersion());
				assertTrue(config.isCanBeNull());
				assertEquals(COLUMN_DEFINITION, config.getColumnDefinition());
			} else if (field.getName().equals("uniqueColumn")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertTrue(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("nullableColumn")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertFalse(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("uniqueJoinColumn")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertTrue(config.isForeign());
				assertFalse(config.isForeignCollection());
				assertTrue(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("nullableJoinColumn")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertTrue(config.isForeign());
				assertFalse(config.isForeignCollection());
				assertFalse(config.isUnique());
				assertFalse(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("ourEnumOrdinal")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertFalse(config.isVersion());
				assertTrue(config.isCanBeNull());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
				assertTrue(config.getDataPersister() instanceof EnumIntegerType);
			} else if (field.getName().equals("ourEnumString")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertFalse(config.isVersion());
				assertTrue(config.isCanBeNull());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
				assertTrue(config.getDataPersister() instanceof EnumStringType);
			} else if (field.getName().equals("version")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertTrue(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("basic")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertTrue(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else if (field.getName().equals("basicNotOptional")) {
				assertFalse(config.isId());
				assertFalse(config.isGeneratedId());
				assertFalse(config.isForeign());
				assertFalse(config.isUnique());
				assertFalse(config.isCanBeNull());
				assertFalse(config.isVersion());
				assertNull(config.getColumnName());
				assertNull(config.getColumnDefinition());
			} else {
				System.err.println("\n\n\nUnknown field: " + field.getName());
			}
		}
	}

}