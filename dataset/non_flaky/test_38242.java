class DummyClass_38242 {
    @Test
    public void testGetAllTableNames() {
        final TableReference anotherTable = TableReference.createWithEmptyNamespace("AnotherTable");
        assertEquals(1, keyValueService.getAllTableNames().size());
        assertEquals(TEST_TABLE, keyValueService.getAllTableNames().iterator().next());
        keyValueService.createTable(anotherTable, AtlasDbConstants.GENERIC_TABLE_METADATA);
        assertEquals(2, keyValueService.getAllTableNames().size());
        assertTrue(keyValueService.getAllTableNames().contains(anotherTable));
        assertTrue(keyValueService.getAllTableNames().contains(TEST_TABLE));
        keyValueService.dropTable(anotherTable);
        assertEquals(1, keyValueService.getAllTableNames().size());
        assertEquals(TEST_TABLE, keyValueService.getAllTableNames().iterator().next());
    }

}