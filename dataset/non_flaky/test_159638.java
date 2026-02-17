class DummyClass_159638 {
    @Test
    public void testBatchInsert() throws Exception {
        if (this.getDatabase() == null) {
            return;
        }
        clearDatabase();

        createLiquibase("changelogs/common/batchInsert.changelog.xml").update(this.contexts);
        // ChangeLog already contains the verification code
    }

}