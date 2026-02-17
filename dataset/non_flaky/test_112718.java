class DummyClass_112718 {
	@Test
	public void testChangeInsertValue() throws Exception {
		Dao<Foo, Object> dao = createDao(Foo.class, true);
		Foo foo = new Foo();
		foo.val = TEST_CHANGE_FROM;

		ConnectionProxy.lastValue = 0;
		assertEquals(1, dao.create(foo));
		/*
		 * After we create an instance of foo, we check to see that our proxy was able to intercept the val argument.
		 */
		assertEquals(foo.val, ConnectionProxy.lastValue);

		Foo result = dao.queryForId(foo.id);
		assertNotNull(result);
		assertEquals(TEST_CHANGE_TO, result.val);
		assertTrue(result.val != TEST_CHANGE_FROM);
	}

}