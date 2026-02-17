class DummyClass_159662 {
    @Test
    public void testObjectQuotingStrategy() throws Exception {
        assumeNotNull(this.getDatabase());
        if (!Arrays.asList("oracle,h2,hsqldb,postgresql,mysql").contains(database.getShortName())) {
            return;
        }

        Liquibase liquibase = createLiquibase(objectQuotingStrategyChangeLog);
        clearDatabase();
        liquibase.update(contexts);
        clearDatabase();
    }

}