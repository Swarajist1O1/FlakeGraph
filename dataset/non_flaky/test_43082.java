class DummyClass_43082 {
    @Test
    public void testTruncateTable()
    {
        if (!hasBehavior(SUPPORTS_TRUNCATE)) {
            assertQueryFails("TRUNCATE TABLE nation", "This connector does not support truncating tables");
            return;
        }

        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE));

        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_truncate", "AS SELECT * FROM region")) {
            assertUpdate("TRUNCATE TABLE " + table.getName());
            assertQuery("SELECT count(*) FROM " + table.getName(), "VALUES 0");
        }
    }

}