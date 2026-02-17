class DummyClass_159642 {
    @Test
    public void testOutputChangeLog() throws Exception {
        assumeNotNull(this.getDatabase());

        StringWriter output = new StringWriter();
        Liquibase liquibase;
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter("loginuser", getUsername());
        liquibase.update(this.contexts, output);

        String outputResult = output.getBuffer().toString();
        assertNotNull("generated output change log must not be empty", outputResult);
        assertTrue("generated output change log is at least 100 bytes long", outputResult.length() > 100);

        // TODO should better written to a file so CI servers can pick it up as test artifacts.
        System.out.println(outputResult);
        assertTrue("create databasechangelog command not found in: \n" + outputResult, outputResult.contains("CREATE TABLE "+database.escapeTableName(database.getLiquibaseCatalogName(), database.getLiquibaseSchemaName(), database.getDatabaseChangeLogTableName())));
        assertTrue("create databasechangeloglock command not found in: \n" + outputResult, outputResult.contains("CREATE TABLE "+database.escapeTableName(database.getLiquibaseCatalogName(), database.getLiquibaseSchemaName(), database.getDatabaseChangeLogLockTableName())));

        assertTrue("generated output contains a correctly encoded Euro sign", outputResult.contains("â¬"));

        DatabaseSnapshot snapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl(database));
        assertEquals("no database objects were actually created during creation of the output changelog",
                0, snapshot.get(Schema.class).iterator().next().getDatabaseObjects(Table.class).size());
    }

}