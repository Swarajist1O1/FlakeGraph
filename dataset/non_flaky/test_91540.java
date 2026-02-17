class DummyClass_91540 {
    @Test
    public void testListColumns() throws SQLException {
        String catalog = "testSchema";
        String table = "testTable";
        ResultSet rs = mock(ResultSet.class);
        when(dbmd.getColumns(catalog, null, table, null)).thenReturn(rs);

        ResultSet result = jdbcMetadata.listColumns(dbmd, catalog, table);

        verify(dbmd, times(1)).getColumns(catalog, null, table, null);
        Assert.assertEquals(rs, result);
    }

}