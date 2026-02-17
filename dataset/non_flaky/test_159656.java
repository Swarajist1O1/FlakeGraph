class DummyClass_159656 {
    @Test
    public void testRollbackToChange() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(rollbackChangeLog);
        wipeDatabase();

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.update(this.contexts);

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.rollback(8, this.contexts);

        liquibase.tag("testRollbackToChange");

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.update(this.contexts);

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.rollback("testRollbackToChange", this.contexts);
    }

}