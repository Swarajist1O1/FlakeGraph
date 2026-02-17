class DummyClass_112710 {
	@Test
	public void testNestedTransactionsReleaseFails() throws Exception {
		final ConnectionSource connectionSource = createMock(ConnectionSource.class);
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		expect(conn.isAutoCommitSupported()).andReturn(true).times(2);
		expect(conn.isAutoCommit()).andReturn(true).times(2);
		conn.setAutoCommit(false);
		expectLastCall().times(2);
		Savepoint savePoint = createMock(Savepoint.class);
		expect(savePoint.getSavepointName()).andReturn("name").anyTimes();
		expect(conn.setSavePoint(isA(String.class))).andReturn(savePoint).times(2);
		expect(connectionSource.getDatabaseType()).andReturn(databaseType).times(2);
		expect(connectionSource.getReadWriteConnection(null)).andReturn(conn).times(2);
		expect(connectionSource.saveSpecialConnection(conn)).andReturn(true);
		expect(connectionSource.saveSpecialConnection(conn)).andReturn(false);
		// should only get one of these because we only returned save once
		connectionSource.clearSpecialConnection(conn);
		conn.releaseSavePoint(savePoint);
		expectLastCall().andThrow(new SQLException("testing the release to fail"));
		conn.rollback(savePoint);
		expectLastCall().times(2);
		connectionSource.releaseConnection(conn);
		expectLastCall().times(2);
		conn.setAutoCommit(true);
		expectLastCall().times(2);

		replay(connectionSource, conn, savePoint);
		try {
			TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
				@Override
				public Void call() throws SQLException {
					TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
						@Override
						public Void call() {
							return null;
						}

}