class DummyClass_159669 {
    @Test
    public void smartDataLoad() throws Exception {
        assumeNotNull(this.getDatabase());
        Liquibase liquibase = createLiquibase("changelogs/common/smartDataLoad.changelog.xml");
        clearDatabase();
        try {
            liquibase.update(this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }
        try {
            liquibase.rollback(new Date(0), this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }
    }

}