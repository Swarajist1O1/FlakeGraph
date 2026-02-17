class DummyClass_91554 {
    @Test
    public void testBasics() throws IOException {
        ISource source = SourceManager.getSource(new JdbcSourceAware());
        ISourceMetadataExplorer explorer = source.getSourceMetadataExplorer();
        ISampleDataDeployer deployer = source.getSampleDataDeployer();

        Assert.assertTrue(source instanceof JdbcSource);
        Assert.assertTrue(explorer instanceof JdbcExplorer);
        Assert.assertTrue(deployer instanceof JdbcExplorer);

        IMRInput input = source.adaptToBuildEngine(IMRInput.class);
        Assert.assertNotNull(input);

        Class adaptTo = Object.class;
        expectedCannotAdaptEx.expect(RuntimeException.class);
        expectedCannotAdaptEx.expectMessage("Cannot adapt to " + adaptTo);
        source.adaptToBuildEngine(adaptTo);

        TableMetadataManager tblManager = TableMetadataManager.getInstance(getTestConfig());
        IReadableTable table = source.createReadableTable(tblManager.getTableDesc("test_kylin_fact", "default"), null);
        Assert.assertTrue(table instanceof JdbcTable);

        source.close();
    }

}