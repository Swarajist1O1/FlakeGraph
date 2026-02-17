class DummyClass_159645 {
    @Test
    public void testRollbackableChangeLog() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(rollbackChangeLog);
        clearDatabase();

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.update(this.contexts);

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.rollback(new Date(0), this.contexts);

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.update(this.contexts);

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.rollback(new Date(0), this.contexts);
    }

}