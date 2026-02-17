class DummyClass_91534 {
    @Test
    public void testListColumns() throws SQLException {
        String schema = "testSchema";
        String table = "testTable";
        ResultSet rs = mock(ResultSet.class);
        when(dbmd.getColumns(null, schema, table, null)).thenReturn(rs);

        ResultSet result = jdbcMetadata.listColumns(dbmd, schema, table);

        verify(dbmd, times(1)).getColumns(null, schema, table, null);
        Assert.assertEquals(rs, result);
    }

}