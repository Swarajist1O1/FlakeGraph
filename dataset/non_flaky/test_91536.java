class DummyClass_91536 {
    @Test(expected = IllegalArgumentException.class)
    public void testListDatabasesWithoutSpecificDB() throws SQLException {
        when(connection.getCatalog()).thenReturn("");
        jdbcMetadata.listDatabases();
    }

}