class DummyClass_159704 {
//    @Test
//    public void waitForLock_emptyDatabase() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new DatabaseTest() {
//
//                    public void performTest(Database database) throws Exception {
//                        Executor executor = ExecutorService.getInstance().getExecutor(database);
//                        try {
//                            LockService.getInstance(database).resetAll();
//
//                            executor.execute(new DropTableStatement(null, database.getDatabaseChangeLogTableName(), false), new ArrayList<SqlVisitor>());
//                        } catch (DatabaseException e) {
//                            ; //must not be there
//                        }
//                        try {
//                            executor.execute(new DropTableStatement(null, database.getDatabaseChangeLogLockTableName(), false), new ArrayList<SqlVisitor>());
//                        } catch (DatabaseException e) {
//                            ; //must not be there
//                        }
//
//                        database.commit();
//
//                        LockService lockManager = LockService.getInstance(database);
//                        lockManager.waitForLock();
//                        lockManager.waitForLock();
//                    }

}