class DummyClass_91531 {
    @Test
    public void testListDatabases() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString("TABLE_SCHEM")).thenReturn("schema1").thenReturn("schema2");
        when(rs.getString("TABLE_CATALOG")).thenReturn("catalog1").thenReturn("catalog2");

        when(connection.getMetaData()).thenReturn(dbmd);
        when(dbmd.getSchemas()).thenReturn(rs);

        List<String> dbs = jdbcMetadata.listDatabases();

        Assert.assertEquals(2, dbs.size());
        Assert.assertEquals("schema1", dbs.get(0));
    }

}