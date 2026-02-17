class DummyClass_159705 {
//    @Test
//    public void waitForLock_loggingDatabase() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new DatabaseTest() {
//
//                    public void performTest(Database database) throws Exception {
//
//                        LockService.getInstance(database).resetAll();
//
//                        Executor executor = ExecutorService.getInstance().getExecutor(database);
//                        try {
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
//                        ExecutorService.getInstance().setExecutor(database, (new LoggingExecutor(ExecutorService.getInstance().getExecutor(database), new StringWriter(), database)));
//
//                        LockService lockManager = LockService.getInstance(database);
//                        lockManager.waitForLock();
//                    }

}