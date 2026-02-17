class DummyClass_91537 {
    @Test
    public void testListDatabases() throws SQLException {
        when(connection.getCatalog()).thenReturn("catalog1");

        List<String> dbs = jdbcMetadata.listDatabases();

        Assert.assertEquals(1, dbs.size());
        Assert.assertEquals("catalog1", dbs.get(0));
    }

}