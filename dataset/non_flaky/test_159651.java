class DummyClass_159651 {
    @Test
    public void testRerunDiffChangeLogAltSchema() throws Exception {
        assumeNotNull(this.getDatabase());
        if (database.getShortName().equalsIgnoreCase("mssql")) {
            return; // not possible on MSSQL.
        }
        if (!database.supportsSchemas()) {
            return;
        }

        Liquibase liquibase = createLiquibase(includedChangeLog);
        database.setDefaultSchemaName("lbcat2");
        clearDatabase();


        LockService lockService = LockServiceFactory.getInstance().getLockService(database);
        lockService.forceReleaseLock();

        liquibase.update(includedChangeLog);

        DatabaseSnapshot originalSnapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl(database));

        CompareControl compareControl = new CompareControl(
                new CompareControl.SchemaComparison[]{
                        new CompareControl.SchemaComparison(
                                CatalogAndSchema.DEFAULT,
                                new CatalogAndSchema(null, "lbcat2")
                        )
                },
                originalSnapshot.getSnapshotControl().getTypesToInclude()
        );
        DiffResult diffResult = DiffGeneratorFactory.getInstance().compare(database, null, compareControl);

        File tempFile = File.createTempFile("liquibase-test", ".xml");

        FileOutputStream output = new FileOutputStream(tempFile);
        try {
            new DiffToChangeLog(diffResult, new DiffOutputControl()).print(new PrintStream(output));
            output.flush();
        } finally {
            output.close();
        }

        liquibase = createLiquibase(tempFile.getName());
        clearDatabase();

        //run again to test changelog testing logic
        Executor executor = ExecutorService.getInstance().getExecutor(database);
        try {
            executor.execute(new DropTableStatement("lbcat2", "lbcat2", database.getDatabaseChangeLogTableName(), false));
        } catch (DatabaseException e) {
            //ok
        }
        try {
            executor.execute(new DropTableStatement("lbcat2", "lbcat2", database.getDatabaseChangeLogLockTableName(), false));
        } catch (DatabaseException e) {
            //ok
        }
        database.commit();

        DatabaseConnection connection = DatabaseTestContext.getInstance().getConnection(getJdbcUrl(), username, password);
        database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection);
        database.setDefaultSchemaName("lbcat2");
        liquibase = createLiquibase(tempFile.getName());
        try {
            liquibase.update(this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }

        tempFile.deleteOnExit();

        DatabaseSnapshot finalSnapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl(database));

        CompareControl finalCompareControl = new CompareControl();
        finalCompareControl.addSuppressedField(Column.class, "autoIncrementInformation");
        DiffResult finalDiffResult = DiffGeneratorFactory.getInstance().compare(originalSnapshot, finalSnapshot, finalCompareControl);
        new DiffToReport(finalDiffResult, System.out).print();
        assertTrue("running the same change log two times against an alternative schema should produce " +
                        "equal snapshots.",
                finalDiffResult.areEqual());
    }

}