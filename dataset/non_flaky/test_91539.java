class DummyClass_91539 {
    @Test
    public void testGetTable() throws SQLException {
        String catalog = "testSchema";
        String table = "testTable";
        ResultSet rs = mock(ResultSet.class);
        when(dbmd.getTables(catalog, null, table, null)).thenReturn(rs);

        ResultSet result = jdbcMetadata.getTable(dbmd, catalog, table);

        verify(dbmd, times(1)).getTables(catalog, null, table, null);
        Assert.assertEquals(rs, result);
    }

}