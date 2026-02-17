class DummyClass_159644 {
    @Test
    public void testUpdateClearUpdate() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.update(this.contexts);
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.update(this.contexts);
    }

}