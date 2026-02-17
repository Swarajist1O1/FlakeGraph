class DummyClass_159653 {
    @Test
    public void testTagEmptyDatabase() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.checkLiquibaseTables(false, null, new Contexts(), new LabelExpression());
        liquibase.tag("empty");

        liquibase = createLiquibase(rollbackChangeLog);
        liquibase.update(new Contexts());

        liquibase.rollback("empty", new Contexts());

    }

}