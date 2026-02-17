class DummyClass_112708 {
	@Test
	public void testConnectionLeakCreateList() throws Exception {
		final Dao<Foo, Integer> dao = createDao(Foo.class, true);
		final List<Foo> list = new ArrayList<Foo>();
		Foo foo1 = new Foo();
		foo1.val = 1;
		list.add(foo1);
		Foo foo2 = new Foo();
		foo2.val = 2;
		list.add(foo2);
		Foo foo3 = new Foo();
		foo3.val = 3;
		list.add(foo3);
		assertTrue(connectionSource.isOkay());
		assertEquals(0, connectionSource.getConnectionCount());
		TransactionManager.callInTransaction(connectionSource, new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return dao.create(list) >= 0;
			}

}