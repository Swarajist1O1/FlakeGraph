class DummyClass_91545 {
    @Test
    public void testListTables() throws Exception {
        List<String> tblList = explorer.listTables("DEFAULT");
        Assert.assertTrue(tblList.size() >= 3);
        Assert.assertTrue(tblList.contains("TEST_KYLIN_FACT"));
        Assert.assertTrue(tblList.contains("TEST_ACCOUNT"));
        Assert.assertTrue(tblList.contains("TEST_COUNTRY"));
    }

}