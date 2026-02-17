class DummyClass_159648 {
    @Test
    public void testTag() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.update(this.contexts);

        liquibase.tag("Test Tag");
    }

}