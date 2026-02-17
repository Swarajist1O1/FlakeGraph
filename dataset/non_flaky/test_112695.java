class DummyClass_112695 {
	@Test
	public void testTransactionManagerRollback() throws Exception {
		ConnectionSource connectionSource = createMock(ConnectionSource.class);
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		expect(conn.isAutoCommitSupported()).andReturn(false);
		Savepoint savePoint = createMock(Savepoint.class);
		expect(savePoint.getSavepointName()).andReturn("name").anyTimes();
		expect(conn.setSavePoint(isA(String.class))).andReturn(savePoint);
		conn.rollback(savePoint);
		expect(connectionSource.getDatabaseType()).andReturn(databaseType);
		expect(connectionSource.getReadWriteConnection(null)).andReturn(conn);
		expect(connectionSource.saveSpecialConnection(conn)).andReturn(true);
		connectionSource.clearSpecialConnection(conn);
		connectionSource.releaseConnection(conn);
		replay(connectionSource, conn, savePoint);
		TransactionManager tm = new TransactionManager(connectionSource);
		try {
			tm.callInTransaction(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					throw new SQLException("you better roll back!!");
				}

}