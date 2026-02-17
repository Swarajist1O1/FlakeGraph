class DummyClass_91543 {
    @Test
    public void testLoadTableMetadata() throws SQLException {
        String tableName = "tb1";
        String databaseName = "testdb";
        ResultSet rs1 = mock(ResultSet.class);
        when(rs1.next()).thenReturn(true).thenReturn(false);
        when(rs1.getString("TABLE_TYPE")).thenReturn("TABLE");

        ResultSet rs2 = mock(ResultSet.class);
        when(rs2.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs2.getString("COLUMN_NAME")).thenReturn("COL1").thenReturn("COL2").thenReturn("COL3");
        when(rs2.getInt("DATA_TYPE")).thenReturn(Types.VARCHAR).thenReturn(Types.INTEGER).thenReturn(Types.DECIMAL);
        when(rs2.getInt("COLUMN_SIZE")).thenReturn(128).thenReturn(10).thenReturn(19);
        when(rs2.getInt("DECIMAL_DIGITS")).thenReturn(0).thenReturn(0).thenReturn(4);
        when(rs2.getInt("ORDINAL_POSITION")).thenReturn(1).thenReturn(3).thenReturn(2);
        when(rs2.getString("REMARKS")).thenReturn("comment1").thenReturn("comment2").thenReturn("comment3");

        when(jdbcMetadata.getTable(dbmd, databaseName, tableName)).thenReturn(rs1);
        when(jdbcMetadata.listColumns(dbmd, databaseName, tableName)).thenReturn(rs2);

        Pair<TableDesc, TableExtDesc> result = jdbcExplorer.loadTableMetadata(databaseName, tableName, "proj");
        TableDesc tableDesc = result.getFirst();
        ColumnDesc columnDesc = tableDesc.getColumns()[1];

        Assert.assertEquals(databaseName.toUpperCase(Locale.ROOT), tableDesc.getDatabase());
        Assert.assertEquals(3, tableDesc.getColumnCount());
        Assert.assertEquals("TABLE", tableDesc.getTableType());
        Assert.assertEquals("COL2", columnDesc.getName());
        Assert.assertEquals("integer", columnDesc.getTypeName());
        Assert.assertEquals("comment2", columnDesc.getComment());
        Assert.assertEquals(databaseName.toUpperCase(Locale.ROOT) + "." + tableName.toUpperCase(Locale.ROOT),
                result.getSecond().getIdentity());
    }

}