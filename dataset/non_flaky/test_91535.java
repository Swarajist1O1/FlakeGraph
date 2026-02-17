class DummyClass_91535 {
    @Test
    public void testListDatabases() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString("TABLE_SCHEM")).thenReturn("schema1").thenReturn("schema2");
        when(rs.getString("TABLE_CAT")).thenReturn("catalog1").thenReturn("testdb");

        when(connection.getCatalog()).thenReturn("testdb");
        when(connection.getMetaData()).thenReturn(dbmd);
        when(dbmd.getTables("testdb", null, null, null)).thenReturn(rs);

        List<String> dbs = jdbcMetadata.listDatabases();

        Assert.assertEquals(1, dbs.size());
        Assert.assertEquals("schema2", dbs.get(0));
    }

}