class DummyClass_43083 {
    @Test(dataProvider = "testColumnNameDataProvider")
    public void testMaterializedViewColumnName(String columnName)
    {
        skipTestUnless(hasBehavior(SUPPORTS_CREATE_MATERIALIZED_VIEW));

        if (!requiresDelimiting(columnName)) {
            testMaterializedViewColumnName(columnName, false);
        }
        testMaterializedViewColumnName(columnName, true);
    }

}