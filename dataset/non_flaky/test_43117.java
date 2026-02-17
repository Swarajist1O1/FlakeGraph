class DummyClass_43117 {
    @Test
    public void testCreateTable()
    {
        if (!hasBehavior(SUPPORTS_CREATE_TABLE)) {
            assertQueryFails("CREATE TABLE xxxx (a bigint, b double)", "This connector does not support creating tables");
            return;
        }

        String tableName = "test_create_" + randomTableSuffix();
        assertUpdate("CREATE TABLE " + tableName + " (a bigint, b double)");
        assertThat(query("SELECT a, b FROM " + tableName))
                .returnsEmptyResult();
        assertUpdate("DROP TABLE " + tableName);
    }

}