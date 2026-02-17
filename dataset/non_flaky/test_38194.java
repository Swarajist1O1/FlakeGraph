class DummyClass_38194 {
    @Test
    public void testCreateTable() {
        mockery.checking(new Expectations(){{
            oneOf(kvs).createTables(with(tableMapContainsEntry(TABLE_REF, getSimpleTableDefinitionAsBytes(TABLE_REF))));
        }});
        Schemas.createTable(kvs, TABLE_REF, getSimpleTableDefinition(TABLE_REF));
    }

}