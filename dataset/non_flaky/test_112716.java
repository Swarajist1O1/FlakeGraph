class DummyClass_112716 {
	@Test
	public void testBasic() throws Exception {
		Dao<Foo, Object> dao = createDao(Foo.class, true);
		Foo foo = new Foo();
		foo.val = 1131233;

		assertEquals(0, OurConnectionProxy.insertCount);
		assertEquals(1, dao.create(foo));
		assertEquals(1, OurConnectionProxy.insertCount);

		Foo result = dao.queryForId(foo.id);
		assertEquals(foo.val + VALUE_INCREMENT, result.val);
	}

}