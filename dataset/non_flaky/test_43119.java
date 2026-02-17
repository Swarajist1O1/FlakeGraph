class DummyClass_43119 {
    @Test
    public void testInsert()
    {
        if (!hasBehavior(SUPPORTS_INSERT)) {
            assertQueryFails("INSERT INTO region (regionkey) VALUES (42)", "This connector does not support inserts");
            return;
        }

        if (!hasBehavior(SUPPORTS_CREATE_TABLE)) {
            throw new AssertionError("Cannot test INSERT without CREATE TABLE, the test needs to be implemented in a connector-specific way");
        }

        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_insert_", "(a bigint, b double)")) {
            assertUpdate("INSERT INTO " + table.getName() + " (a, b) VALUES (42, -38.5)", 1);
            assertThat(query("SELECT CAST(a AS bigint), b FROM " + table.getName()))
                    .matches("VALUES (BIGINT '42', -385e-1)");
        }
    }

}