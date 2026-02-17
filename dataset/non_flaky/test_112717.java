class DummyClass_112717 {
	@Test
	public void testBasic() throws Exception {
		Dao<Foo, Object> dao = createDao(Foo.class, true);
		Foo foo = new Foo();
		foo.val = 100;

		ConnectionProxy.lastValue = 0;
		assertEquals(1, dao.create(foo));
		/*
		 * After we create an instance of foo, we check to see that our proxy was able to intercept the val argument.
		 */
		assertEquals(foo.val, ConnectionProxy.lastValue);
	}

}