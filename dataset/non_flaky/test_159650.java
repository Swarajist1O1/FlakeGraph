class DummyClass_159650 {
    @Test
    public void testRerunDiffChangeLog() throws Exception {
        assumeNotNull(this.getDatabase());

        for (int run=0; run < 2; run++) { //run once outputting data as insert, once as csv
            boolean outputCsv = run == 1;
            runCompleteChangeLog();

            SnapshotControl snapshotControl = new SnapshotControl(database);

            DatabaseSnapshot originalSnapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, snapshotControl);

            CompareControl compareControl = new CompareControl();
            compareControl.addSuppressedField(Column.class, "defaultValue");  //database returns different data even if the same
            compareControl.addSuppressedField(Column.class, "autoIncrementInformation"); //database returns different data even if the same
            if (database instanceof OracleDatabase) {
                compareControl.addSuppressedField(Column.class, "type"); //database returns different nvarchar2 info even though they are the same
                compareControl.addSuppressedField(Column.class, "nullable"); // database returns different nullable on views, e.g. v_person.id
            }
            if (database instanceof PostgresDatabase) {
                compareControl.addSuppressedField(Column.class, "type"); //database returns different nvarchar2 info even though they are the same
            }

            DiffOutputControl diffOutputControl = new DiffOutputControl();
            File tempFile = tempDirectory.getRoot().createTempFile("liquibase-test", ".xml");

            if (outputCsv) {
                diffOutputControl.setDataDir(new File(tempFile.getParentFile(), "liquibase-data").getCanonicalPath().replaceFirst("\\w:",""));
            }

            DiffResult diffResult = DiffGeneratorFactory.getInstance().compare(database, null, compareControl);


            FileOutputStream output = new FileOutputStream(tempFile);
            try {
                new DiffToChangeLog(diffResult, new DiffOutputControl()).print(new PrintStream(output));
                output.flush();
            } finally {
                output.close();
            }

            Liquibase liquibase = createLiquibase(tempFile.getName());
            clearDatabase();

            DatabaseSnapshot emptySnapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl(database));

            //run again to test changelog testing logic
            liquibase = createLiquibase(tempFile.getName());
            try {
                liquibase.update(this.contexts);
            } catch (ValidationFailedException e) {
                e.printDescriptiveError(System.out);
                throw e;
            }

            DatabaseSnapshot migratedSnapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl(database));

            DiffResult finalDiffResult = DiffGeneratorFactory.getInstance().compare(originalSnapshot, migratedSnapshot, compareControl);
            try {
                assertTrue("recreating the database from the generated change log should cause both 'before' and " +
                                "'after' snapshots to be equal.",
                        finalDiffResult.areEqual());
            } catch (AssertionError e) {
                new DiffToReport(finalDiffResult, System.err).print();
                throw e;
            }

            //diff to empty and drop all
            DiffResult emptyDiffResult = DiffGeneratorFactory.getInstance().compare(emptySnapshot, migratedSnapshot, compareControl);
            output = new FileOutputStream(tempFile);
            try {
                new DiffToChangeLog(emptyDiffResult, new DiffOutputControl(true, true, true, null)).print(new PrintStream(output));
                output.flush();
            } finally {
                output.close();
            }

            liquibase = createLiquibase(tempFile.getName());
            Scope.getCurrentScope().getLog(getClass()).info(LogType.LOG, "updating from "+tempFile.getCanonicalPath());
            try {
                liquibase.update(this.contexts);
            } catch (LiquibaseException e) {
                throw e;
            }

            DatabaseSnapshot emptyAgainSnapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl(database));
            assertEquals("a database that was 'updated' to an empty snapshot should only have 2 tables left: " +
                            "the database change log table and the lock table.",
                    2, emptyAgainSnapshot.get(Table.class).size());
            assertEquals("a database that was 'updated' to an empty snapshot should not contain any views.",
                    0, emptyAgainSnapshot.get(View.class).size());
        }
    }

}