class DummyClass_112767 {
	@Test
	public void testIterator() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		CloseableIterator<Foo> iterator = dao.iterator();
		assertFalse(iterator.hasNext());

		Foo foo1 = new Foo();
		assertEquals(1, dao.create(foo1));

		Foo foo2 = new Foo();
		assertEquals(1, dao.create(foo2));

		iterator = dao.iterator();
		assertTrue(iterator.hasNext());
		Foo result = iterator.next();
		assertEquals(foo1.id, result.id);
		assertTrue(iterator.hasNext());

		result = iterator.next();
		assertEquals(foo2.id, result.id);

		assertFalse(iterator.hasNext());
		assertNull(iterator.nextThrow());
	}

}