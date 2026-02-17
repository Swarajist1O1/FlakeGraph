class DummyClass_43077 {
    @Test
    public void verifySupportsRowLevelDeleteDeclaration()
    {
        if (hasBehavior(SUPPORTS_ROW_LEVEL_DELETE)) {
            // Covered by testRowLevelDelete
            return;
        }

        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE));
        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_supports_row_level_delete", "AS SELECT * FROM region")) {
            assertQueryFails("DELETE FROM " + table.getName() + " WHERE regionkey = 2", "This connector does not support deletes");
        }
    }

}