class DummyClass_159702 {
//    @Test
//    public void waitForLock_twoConnections() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(new DatabaseTest() {
//            public void performTest(Database database) throws Exception {
////                if (database instanceof H2Database) {
////                    return;
////                }
//
//                String url = DatabaseTestContext.getInstance().getTestUrl(database);
//                System.out.println(url);
//                DatabaseConnection connection2 = DatabaseTestContext.getInstance().openDatabaseConnection(url);
//                Database database2 = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection2);
//
//                assertTrue(LockService.getInstance(database).acquireLock());
//                assertTrue(LockService.getInstance(database).hasChangeLogLock());
//                assertFalse(LockService.getInstance(database2).hasChangeLogLock());
//
//                assertFalse(LockService.getInstance(database2).acquireLock());
//                assertFalse(LockService.getInstance(database2).acquireLock());
//
//                LockService.getInstance(database).releaseLock();
//                assertTrue(LockService.getInstance(database2).acquireLock());
//
//            }

}