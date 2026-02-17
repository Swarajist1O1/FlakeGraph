class DummyClass_159657 {
    @Test
    public void testDbDoc() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase;
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.update(this.contexts);

        Path outputDir = tempDirectory.newFolder().toPath().normalize();
        logger.fine(LogType.LOG, "Database documentation will be written to this temporary folder: " + outputDir);

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.generateDocumentation(outputDir.toAbsolutePath().toString(), this.contexts);
    }

}