class DummyClass_159652 {
    @Test
    public void testClearChecksums() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.update(this.contexts);

        liquibase.clearCheckSums();
    }

}