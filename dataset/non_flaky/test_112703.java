class DummyClass_112703 {
	@Test
	public void testRollBack() throws Exception {
		if (connectionSource == null) {
			return;
		}
		TransactionManager mgr = new TransactionManager(connectionSource);
		final Dao<Foo, Integer> fooDao = createDao(Foo.class, true);
		testTransactionManager(mgr, new RuntimeException("What!!  I protest!!"), fooDao);
	}

}