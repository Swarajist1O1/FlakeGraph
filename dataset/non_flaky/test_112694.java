class DummyClass_112694 {
	@Test
	public void testTransactionManagerSavePointNull() throws Exception {
		ConnectionSource connectionSource = createMock(ConnectionSource.class);
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		expect(conn.isAutoCommitSupported()).andReturn(false);
		expect(conn.setSavePoint(isA(String.class))).andReturn(null);
		conn.commit(null);
		expect(connectionSource.getDatabaseType()).andReturn(databaseType);
		expect(connectionSource.getReadWriteConnection(null)).andReturn(conn);
		expect(connectionSource.saveSpecialConnection(conn)).andReturn(true);
		connectionSource.clearSpecialConnection(conn);
		connectionSource.releaseConnection(conn);
		replay(connectionSource, conn);
		TransactionManager tm = new TransactionManager(connectionSource);
		tm.callInTransaction(new Callable<Void>() {
			@Override
			public Void call() {
				return null;
			}

}