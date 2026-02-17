class DummyClass_91548 {
    @Test
    public void testLoadTableMetadata() throws Exception {
        Pair<TableDesc, TableExtDesc> pair = explorer.loadTableMetadata("DEFAULT", "TEST_KYLIN_FACT", "DEFAULT");
        Assert.assertNotNull(pair.getFirst());
        Assert.assertNotNull(pair.getSecond());

        TableDesc tblDesc = pair.getFirst();
        TableExtDesc tblExtDesc = pair.getSecond();
        Assert.assertEquals("TEST_KYLIN_FACT", tblDesc.getName());
        Assert.assertEquals("TABLE", tblDesc.getTableType());
        Assert.assertEquals("DEFAULT.TEST_KYLIN_FACT", tblDesc.getIdentity());
        Assert.assertEquals("DEFAULT", tblDesc.getDatabase());
        Assert.assertEquals("DEFAULT", tblDesc.getProject());
        Assert.assertEquals(tblDesc.getIdentity(), tblExtDesc.getIdentity());
        Assert.assertEquals(tblDesc.getProject(), tblExtDesc.getProject());

        ColumnDesc[] columnDescs = tblDesc.getColumns();
        Assert.assertEquals(tblDesc.getColumnCount(), columnDescs.length);
        Assert.assertNotNull(columnDescs[0].getName());
        Assert.assertNotNull(columnDescs[0].getDatatype());
        Assert.assertNotNull(columnDescs[0].getType());
        Assert.assertNotNull(columnDescs[0].getId());
    }

}