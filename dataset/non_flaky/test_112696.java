class DummyClass_112696 {
	@Test
	public void testTransactionManagerRollbackNullSavePoint() throws Exception {
		ConnectionSource connectionSource = createMock(ConnectionSource.class);
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		expect(conn.isAutoCommitSupported()).andReturn(false);
		expect(conn.setSavePoint(isA(String.class))).andReturn(null);
		conn.rollback(null);
		expect(connectionSource.getDatabaseType()).andReturn(databaseType);
		expect(connectionSource.getReadWriteConnection(null)).andReturn(conn);
		expect(connectionSource.saveSpecialConnection(conn)).andReturn(true);
		connectionSource.clearSpecialConnection(conn);
		connectionSource.releaseConnection(conn);
		replay(connectionSource, conn);
		TransactionManager tm = new TransactionManager(connectionSource);
		try {
			tm.callInTransaction(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					throw new SQLException("you better roll back!!");
				}

}