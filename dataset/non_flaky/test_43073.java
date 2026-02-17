class DummyClass_43073 {
    @Test
    public void testRenameSchema()
    {
        if (!hasBehavior(SUPPORTS_RENAME_SCHEMA)) {
            String schemaName = getSession().getSchema().orElseThrow();
            assertQueryFails(
                    format("ALTER SCHEMA %s RENAME TO %s", schemaName, schemaName + randomTableSuffix()),
                    "This connector does not support renaming schemas");
            return;
        }

        if (!hasBehavior(SUPPORTS_CREATE_SCHEMA)) {
            throw new SkipException("Skipping as connector does not support CREATE SCHEMA");
        }

        String schemaName = "test_rename_schema_" + randomTableSuffix();
        try {
            assertUpdate("CREATE SCHEMA " + schemaName);
            assertUpdate("ALTER SCHEMA " + schemaName + " RENAME TO " + schemaName + "_renamed");
        }
        finally {
            assertUpdate("DROP SCHEMA IF EXISTS " + schemaName);
            assertUpdate("DROP SCHEMA IF EXISTS " + schemaName + "_renamed");
        }
    }

}