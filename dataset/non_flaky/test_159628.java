class DummyClass_159628 {
    @Test
    public void runJsonChangelog() throws Exception {
        if (getDatabase() == null) {
            return;
        }

        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        //run again to test changelog testing logic
        liquibase = createLiquibase("changelogs/json/common.tests.changelog.json");
        liquibase.setChangeLogParameter("loginuser", getUsername());

        try {
            liquibase.update(this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }
    }

}