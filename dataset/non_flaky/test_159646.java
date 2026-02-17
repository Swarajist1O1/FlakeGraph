class DummyClass_159646 {
    @Test
    public void testRollbackableChangeLogScriptOnExistingDatabase() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(rollbackChangeLog);
        clearDatabase();

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.update(this.contexts);

        StringWriter writer = new StringWriter();

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.rollback(new Date(0), this.contexts, writer);
    }

}