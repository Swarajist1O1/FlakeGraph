class DummyClass_43076 {
    @Test
    public void verifySupportsDeleteDeclaration()
    {
        if (hasBehavior(SUPPORTS_DELETE)) {
            // Covered by testDeleteAllDataFromTable
            return;
        }

        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE));
        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_supports_delete", "AS SELECT * FROM region")) {
            assertQueryFails("DELETE FROM " + table.getName(), "This connector does not support deletes");
        }
    }

}