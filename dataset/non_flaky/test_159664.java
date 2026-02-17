class DummyClass_159664 {
    @Test
    public void testGenerateChangeLogWithNoChanges() throws Exception {
        assumeNotNull(this.getDatabase());

        runCompleteChangeLog();

        DiffResult diffResult = DiffGeneratorFactory.getInstance().compare(database, database, new CompareControl());

        DiffToChangeLog changeLogWriter = new DiffToChangeLog(diffResult, new DiffOutputControl(false, false, false, null));
        List<ChangeSet> changeSets = changeLogWriter.generateChangeSets();
        assertEquals("generating two change logs without any changes in between should result in an empty generated " +
                "differential change set.", 0, changeSets.size());
    }

}