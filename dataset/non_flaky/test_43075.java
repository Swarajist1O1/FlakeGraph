class DummyClass_43075 {
    @Test
    public void testInsertIntoNotNullColumn()
    {
        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE));

        if (!hasBehavior(SUPPORTS_NOT_NULL_CONSTRAINT)) {
            assertQueryFails(
                    "CREATE TABLE not_null_constraint (not_null_col INTEGER NOT NULL)",
                    format("line 1:35: Catalog '%s' does not support non-null column for column name 'not_null_col'", getSession().getCatalog().orElseThrow()));
            return;
        }

        try (TestTable table = new TestTable(getQueryRunner()::execute, "insert_not_null", "(nullable_col INTEGER, not_null_col INTEGER NOT NULL)")) {
            assertUpdate(format("INSERT INTO %s (not_null_col) VALUES (2)", table.getName()), 1);
            assertQuery("SELECT * FROM " + table.getName(), "VALUES (NULL, 2)");
            // The error message comes from remote databases when ConnectorMetadata.supportsMissingColumnsOnInsert is true
            assertQueryFails(format("INSERT INTO %s (nullable_col) VALUES (1)", table.getName()), errorMessageForInsertIntoNotNullColumn("not_null_col"));
        }

        try (TestTable table = new TestTable(getQueryRunner()::execute, "commuted_not_null", "(nullable_col BIGINT, not_null_col BIGINT NOT NULL)")) {
            assertUpdate(format("INSERT INTO %s (not_null_col) VALUES (2)", table.getName()), 1);
            assertQuery("SELECT * FROM " + table.getName(), "VALUES (NULL, 2)");
            // This is enforced by the engine and not the connector
            assertQueryFails(format("INSERT INTO %s (not_null_col, nullable_col) VALUES (NULL, 3)", table.getName()), "NULL value not allowed for NOT NULL column: not_null_col");
        }
    }

}