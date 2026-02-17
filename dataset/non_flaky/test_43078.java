class DummyClass_43078 {
    @Test
    public void testDeleteAllDataFromTable()
    {
        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE) && hasBehavior(SUPPORTS_DELETE));
        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_delete_all_data", "AS SELECT * FROM region")) {
            // not using assertUpdate as some connectors provide update count and some not
            getQueryRunner().execute("DELETE FROM " + table.getName());
            assertQuery("SELECT count(*) FROM " + table.getName(), "VALUES 0");
        }
    }

}