class DummyClass_159627 {
    @Test
    public void runYamlChangelog() throws Exception {
        if (getDatabase() == null) {
            return;
        }

        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        //run again to test changelog testing logic
        liquibase = createLiquibase("changelogs/yaml/common.tests.changelog.yaml");
        liquibase.setChangeLogParameter("loginuser", getUsername());

        try {
            liquibase.update(this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }


    }

}