class DummyClass_91544 {
    @Test
    public void testListDatabases() throws Exception {
        List<String> dbList = explorer.listDatabases();
        Assert.assertTrue(dbList.size() >= 3);
        Assert.assertTrue(dbList.contains("EDW"));
        Assert.assertTrue(dbList.contains("DEFAULT"));
    }

}