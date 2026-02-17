class DummyClass_159706 {
//    @Test
//    public void waitForLock_loggingThenExecute() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new DatabaseTest() {
//
//                    public void performTest(Database database) throws Exception {
//
//                        LockService.getInstance(database).resetAll();
//
//                        try {
//                            ExecutorService.getInstance().getExecutor(database).execute(new DropTableStatement(null, database.getDatabaseChangeLogTableName(), false), new ArrayList<SqlVisitor>());
//                        } catch (DatabaseException e) {
//                            ; //must not be there
//                        }
//                        try {
//                            ExecutorService.getInstance().getExecutor(database).execute(new DropTableStatement(null, database.getDatabaseChangeLogLockTableName(), false), new ArrayList<SqlVisitor>());
//                        } catch (DatabaseException e) {
//                            ; //must not be there
//                        }
//
//                        database.commit();
//
////                        Database clearDatabase = database.getClass().getConstructor().newInstance();
////                        clearDatabase.setConnection(database.getConnection());
//
//                        Executor originalTemplate = ExecutorService.getInstance().getExecutor(database);
//                        ExecutorService.getInstance().setExecutor(database, new LoggingExecutor(originalTemplate, new StringWriter(), database));
//
//                        LockService lockManager = LockService.getInstance(database);
//                        lockManager.waitForLock();
//
//                        ExecutorService.getInstance().setExecutor(database, originalTemplate);
//                        lockManager.waitForLock();
//
////                        database.getWriteExecutor().execute(database.getSelectChangeLogLockSQL());
//                    }

}