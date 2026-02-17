class DummyClass_159693 {
//    @Test
//    public void execute_floatDefault() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new SqlStatementDatabaseTest(null, new AddColumnStatement(null, TABLE_NAME, NEW_COLUMN_NAME, "float", 42.5)) {
//                    protected void preExecuteAssert(DatabaseSnapshotGenerator snapshot) {
//                        assertNull(snapshot.getTable(TABLE_NAME).getColumn(NEW_COLUMN_NAME));
//                    }
//
//                    protected void postExecuteAssert(DatabaseSnapshotGenerator snapshot) {
//                        Column columnSnapshot = snapshot.getTable(TABLE_NAME).getColumn(NEW_COLUMN_NAME);
//                        assertNotNull(columnSnapshot);
//                        assertEquals(NEW_COLUMN_NAME.toUpperCase(), columnSnapshot.getName().toUpperCase());
//                        assertEquals(new Double(42.5), new Double(((Number) columnSnapshot.getDefaultValue()).doubleValue()));
//
//                        assertEquals(true, columnSnapshot.isNullable());
//                    }
//                });
//    }

}