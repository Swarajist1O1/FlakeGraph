class DummyClass_112709 {
	@Test
	public void testNestedTransactions() throws Exception {
		final Dao<Foo, Integer> dao = createDao(Foo.class, true);
		final Foo foo = new Foo();
		assertEquals(1, dao.create(foo));

		Foo result = dao.queryForId(foo.id);
		assertNotNull(result);

		try {
			TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							dao.delete(foo);
							return null;
						}

}