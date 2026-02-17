class DummyClass_91532 {
    @Test
    public void testListTables() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString("TABLE_NAME")).thenReturn("KYLIN_SALES").thenReturn("CAT_DT").thenReturn("KYLIN_CAT");

        String schema = "testschema";
        when(connection.getMetaData()).thenReturn(dbmd);
        when(dbmd.getTables(null, schema, null, null)).thenReturn(rs);

        List<String> tables = jdbcMetadata.listTables(schema);

        Assert.assertEquals(3, tables.size());
        Assert.assertEquals("CAT_DT", tables.get(1));
    }

}