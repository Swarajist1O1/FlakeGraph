class DummyClass_159654 {
    @Test
    public void testUnrunChangeSetsEmptyDatabase() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        List<ChangeSet> list = liquibase.listUnrunChangeSets(new Contexts(this.contexts), new LabelExpression());

        assertTrue("querying the changelog table on an empty target should return at least 1 un-run change set", !list.isEmpty());

    }

}