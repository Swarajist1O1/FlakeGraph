class DummyClass_112768 {
	@Test
	public void testIteratorPrepared() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		Foo foo1 = new Foo();
		assertEquals(1, dao.create(foo1));

		Foo foo2 = new Foo();
		assertEquals(1, dao.create(foo2));

		PreparedQuery<Foo> query = dao.queryBuilder().where().eq(Foo.ID_COLUMN_NAME, foo2.id).prepare();
		CloseableIterator<Foo> iterator = dao.iterator(query);
		assertTrue(iterator.hasNext());
		Foo result = iterator.next();
		assertEquals(foo2.id, result.id);
		assertFalse(iterator.hasNext());
		assertNull(iterator.nextThrow());
	}

}