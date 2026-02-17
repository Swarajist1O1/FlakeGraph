class DummyClass_43118 {
    @Test
    public void testCreateTableAsSelect()
    {
        if (!hasBehavior(SUPPORTS_CREATE_TABLE_WITH_DATA)) {
            assertQueryFails("CREATE TABLE xxxx AS SELECT BIGINT '42' a, DOUBLE '-38.5' b", "This connector does not support creating tables with data");
            return;
        }

        String tableName = "test_create_" + randomTableSuffix();
        assertUpdate("CREATE TABLE " + tableName + " AS SELECT BIGINT '42' a, DOUBLE '-38.5' b", 1);
        assertThat(query("SELECT CAST(a AS bigint), b FROM " + tableName))
                .matches("VALUES (BIGINT '42', -385e-1)");
        assertUpdate("DROP TABLE " + tableName);
    }

}