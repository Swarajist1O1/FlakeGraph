class DummyClass_38195 {
    @Test
    public void testCreateTables() {
        TableReference tableName1 = TableReference.createWithEmptyNamespace(TABLE_NAME + "1");
        TableReference tableName2 = TableReference.createWithEmptyNamespace(TABLE_NAME + "2");
        mockery.checking(new Expectations(){{
            oneOf(kvs).createTables(with(tableMapContainsEntry(tableName1, getSimpleTableDefinitionAsBytes(tableName1))));
            oneOf(kvs).createTables(with(tableMapContainsEntry(tableName2, getSimpleTableDefinitionAsBytes(tableName2))));
        }});
        Map<TableReference, TableDefinition> tables = Maps.newHashMap();
        tables.put(tableName1, getSimpleTableDefinition(tableName1));
        tables.put(tableName2, getSimpleTableDefinition(tableName2));
        Schemas.createTables(kvs, tables);
    }

}