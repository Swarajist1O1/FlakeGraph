class DummyClass_159647 {
    @Test
    public void testRollbackableChangeLogScriptOnFutureDatabase() throws Exception {
        assumeNotNull(this.getDatabase());

        StringWriter writer = new StringWriter();

        Liquibase liquibase = createLiquibase(rollbackChangeLog);
        clearDatabase();

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.futureRollbackSQL(new Contexts(this.contexts), new LabelExpression(), writer);
    }

}