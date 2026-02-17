class DummyClass_91542 {
    @Test
    public void testListTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        tables.add("T1");
        tables.add("T2");
        String databaseName = "testDb";
        when(jdbcMetadata.listTables(databaseName)).thenReturn(tables);

        List<String> result = jdbcExplorer.listTables(databaseName);
        verify(jdbcMetadata, times(1)).listTables(databaseName);
        Assert.assertEquals(tables, result);
    }

}