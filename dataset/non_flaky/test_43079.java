class DummyClass_43079 {
    @Test
    public void testRowLevelDelete()
    {
        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE) && hasBehavior(SUPPORTS_ROW_LEVEL_DELETE));
        // TODO (https://github.com/trinodb/trino/issues/5901) Use longer table name once Oracle version is updated
        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_row_delete", "AS SELECT * FROM region")) {
            assertUpdate("DELETE FROM " + table.getName() + " WHERE regionkey = 2", 1);
            assertQuery("SELECT count(*) FROM " + table.getName(), "VALUES 4");
        }
    }

}