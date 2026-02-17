class DummyClass_112705 {
	@Test
	public void testNonRuntimeExceptionWiredRollBack() throws Exception {
		if (connectionSource == null) {
			return;
		}
		TransactionManager mgr = new TransactionManager();
		mgr.setConnectionSource(connectionSource);
		mgr.initialize();
		final Dao<Foo, Integer> dao = createDao(Foo.class, true);
		testTransactionManager(mgr, new Exception("What!!  I protest via an Exception!!"), dao);
	}

}