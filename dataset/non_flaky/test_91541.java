class DummyClass_91541 {
    @Test
    public void testListDatabases() throws SQLException {
        List<String> databases = new ArrayList<>();
        databases.add("DB1");
        databases.add("DB2");
        when(jdbcMetadata.listDatabases()).thenReturn(databases);

        List<String> result = jdbcExplorer.listDatabases();

        verify(jdbcMetadata, times(1)).listDatabases();
        Assert.assertEquals(databases, result);
    }

}