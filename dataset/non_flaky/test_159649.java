class DummyClass_159649 {
    @Test
    public void testDiff() throws Exception {
        assumeNotNull(this.getDatabase());

        runCompleteChangeLog();

        CompareControl compareControl = new CompareControl();
        compareControl.addSuppressedField(Column.class, "defaultValue");  //database returns different data even if the same
        compareControl.addSuppressedField(Column.class, "autoIncrementInformation"); //database returns different data even if the same
        DiffResult diffResult = DiffGeneratorFactory.getInstance().compare(database, database, compareControl);

        try {
            assertTrue("comapring a database with itself should return a result of 'DBs are equal'",
                    diffResult.areEqual());
        } catch (AssertionError e) {
            new DiffToReport(diffResult, System.err).print();
            throw e;
        }
    }

}