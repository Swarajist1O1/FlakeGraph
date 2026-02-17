class DummyClass_159631 {
    @Test
    public void smartDataLoad() throws Exception {
        if (this.getDatabase() == null) {
            return;
        }

        Liquibase liquibase = createLiquibase("changelogs/common/smartDataLoad.changelog.xml");
        clearDatabase();

        try {
            liquibase.update(this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }

        // check that the automatically rollback now works too
        try {
            liquibase.rollback(new Date(0), this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }
    }

}