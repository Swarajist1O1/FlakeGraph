class DummyClass_91550 {
    @Test
    public void testBasics() throws Exception {
        TableMetadataManager tblManager = TableMetadataManager.getInstance(getTestConfig());
        TableDesc tblDesc = tblManager.getTableDesc("test_kylin_fact", "default");
        IReadableTable table = SourceManager.getSource(new JdbcSourceTest.JdbcSourceAware())
                .createReadableTable(tblDesc, null);

        // test TableReader
        try (IReadableTable.TableReader reader = table.getReader()) {
            Assert.assertTrue(reader instanceof JdbcTableReader);
            Assert.assertTrue(table instanceof JdbcTable);

            Assert.assertTrue(reader.next());
            String[] row = reader.getRow();
            Assert.assertNotNull(row);
            Assert.assertEquals(tblDesc.getColumnCount(), row.length);
        }

        // test basics
        Assert.assertTrue(table.exists());

        IReadableTable.TableSignature sign = table.getSignature();
        Assert.assertNotNull(sign);
        Assert.assertEquals(String.format(Locale.ROOT, "%s.%s", tblDesc.getDatabase(), tblDesc.getName()), sign.getPath());
        Assert.assertTrue(sign.getLastModifiedTime() > 0);
    }

}