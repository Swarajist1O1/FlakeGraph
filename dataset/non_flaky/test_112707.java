class DummyClass_112707 {
	@Test
	public void testTransactionWithinTransactionFails() throws Exception {
		if (connectionSource == null) {
			return;
		}
		final TransactionManager mgr = new TransactionManager(connectionSource);
		final Dao<Foo, Integer> dao = createDao(Foo.class, true);
		try {
			mgr.callInTransaction(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					dao.create(new Foo());
					mgr.callInTransaction(new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							dao.create(new Foo());
							throw new SQLException("Exception ahoy!");
						}

}