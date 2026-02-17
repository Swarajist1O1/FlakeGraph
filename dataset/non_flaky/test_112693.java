class DummyClass_112693 {
	@Test
	public void testTransactionManagerTableName() throws Exception {
		ConnectionSource connectionSource = createMock(ConnectionSource.class);
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		expect(conn.isAutoCommitSupported()).andReturn(false);
		Savepoint savePoint = createMock(Savepoint.class);
		expect(savePoint.getSavepointName()).andReturn("name").anyTimes();
		expect(conn.setSavePoint(isA(String.class))).andReturn(savePoint);
		conn.commit(savePoint);
		expect(connectionSource.getDatabaseType()).andReturn(databaseType);
		expect(connectionSource.getReadWriteConnection(FOO_TABLE_NAME)).andReturn(conn);
		expect(connectionSource.saveSpecialConnection(conn)).andReturn(true);
		connectionSource.clearSpecialConnection(conn);
		connectionSource.releaseConnection(conn);
		replay(connectionSource, conn, savePoint);
		TransactionManager tm = new TransactionManager(connectionSource);
		tm.callInTransaction(FOO_TABLE_NAME, new Callable<Void>() {
			@Override
			public Void call() {
				return null;
			}

}