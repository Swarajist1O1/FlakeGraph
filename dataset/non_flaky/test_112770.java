class DummyClass_112770 {
	@Test(expected = IllegalStateException.class)
	public void testIteratorNextRemoveRemoveNoNext() throws Exception {
		Dao<Foo, Object> dao = createDao(Foo.class, true);
		Foo foo1 = new Foo();
		assertEquals(1, dao.create(foo1));
		Foo foo2 = new Foo();
		assertEquals(1, dao.create(foo2));
		CloseableIterator<Foo> iterator = dao.iterator();
		try {
			iterator.next();
			iterator.remove();
			iterator.remove();
		} finally {
			iterator.close();
		}
	}

}