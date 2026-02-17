class DummyClass_159663 {
    @Test
    public void testOutputChangeLogIgnoringSchema() throws Exception {
        assumeNotNull(this.getDatabase());

        String schemaName = getDatabase().getDefaultSchemaName();
        if (schemaName == null) {
            return;
        }

        getDatabase().setOutputDefaultSchema(false);
        getDatabase().setOutputDefaultCatalog(false);

        StringWriter output = new StringWriter();
        Liquibase liquibase = createLiquibase(includedChangeLog);
        clearDatabase();

        liquibase = createLiquibase(includedChangeLog);
        liquibase.update(contexts, output);

        String outputResult = output.getBuffer().toString();
        assertNotNull("generated SQL may not be empty", outputResult);
        assertTrue("Expect at least 100 bytes of output in generated SQL", outputResult.length() > 100);
        CharSequence expected = "CREATE TABLE "+getDatabase().escapeTableName(getDatabase().getLiquibaseCatalogName(), getDatabase().getLiquibaseSchemaName(), getDatabase().getDatabaseChangeLogTableName());
        assertTrue("create databasechangelog command not found in: \n" + outputResult, outputResult.contains(expected));
        assertTrue("create databasechangeloglock command not found in: \n" + outputResult, outputResult.contains(expected));
        assertFalse("the schema name '" + schemaName + "' should be ignored\n\n" + outputResult, outputResult.contains
                (schemaName+"."));
    }

}