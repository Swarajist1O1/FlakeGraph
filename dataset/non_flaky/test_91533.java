class DummyClass_91533 {
    @Test
    public void testGetTable() throws SQLException {
        String schema = "testSchema";
        String table = "testTable";
        ResultSet rs = mock(ResultSet.class);
        when(dbmd.getTables(null, schema, table, null)).thenReturn(rs);

        ResultSet result = jdbcMetadata.getTable(dbmd, schema, table);

        verify(dbmd, times(1)).getTables(null, schema, table, null);
        Assert.assertEquals(rs, result);
    }

}