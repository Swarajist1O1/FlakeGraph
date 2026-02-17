class DummyClass_112706 {
	@Test
	public void testTransactionWithinTransaction() throws Exception {
		if (connectionSource == null) {
			return;
		}
		final TransactionManager mgr = new TransactionManager(connectionSource);
		final Dao<Foo, Integer> dao = createDao(Foo.class, true);
		mgr.callInTransaction(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				testTransactionManager(mgr, null, dao);
				return null;
			}

}