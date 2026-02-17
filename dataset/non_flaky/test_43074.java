class DummyClass_43074 {
    @Test
    public void testRenameTableAcrossSchema()
    {
        if (!hasBehavior(SUPPORTS_RENAME_TABLE_ACROSS_SCHEMAS)) {
            if (!hasBehavior(SUPPORTS_RENAME_TABLE)) {
                throw new SkipException("Skipping since rename table is not supported at all");
            }
            assertQueryFails("ALTER TABLE nation RENAME TO other_schema.yyyy", "This connector does not support renaming tables across schemas");
            return;
        }

        if (!hasBehavior(SUPPORTS_CREATE_SCHEMA)) {
            throw new AssertionError("Cannot test ALTER TABLE RENAME across schemas without CREATE SCHEMA, the test needs to be implemented in a connector-specific way");
        }

        if (!hasBehavior(SUPPORTS_CREATE_TABLE)) {
            throw new AssertionError("Cannot test ALTER TABLE RENAME across schemas without CREATE TABLE, the test needs to be implemented in a connector-specific way");
        }

        String tableName = "test_rename_old_" + randomTableSuffix();
        assertUpdate("CREATE TABLE " + tableName + " AS SELECT 123 x", 1);

        String schemaName = "test_schema_" + randomTableSuffix();
        assertUpdate("CREATE SCHEMA " + schemaName);

        String renamedTable = schemaName + ".test_rename_new_" + randomTableSuffix();
        assertUpdate("ALTER TABLE " + tableName + " RENAME TO " + renamedTable);

        assertFalse(getQueryRunner().tableExists(getSession(), tableName));
        assertQuery("SELECT x FROM " + renamedTable, "VALUES 123");

        assertUpdate("DROP TABLE " + renamedTable);
        assertUpdate("DROP SCHEMA " + schemaName);

        assertFalse(getQueryRunner().tableExists(getSession(), tableName));
        assertFalse(getQueryRunner().tableExists(getSession(), renamedTable));
    }

}