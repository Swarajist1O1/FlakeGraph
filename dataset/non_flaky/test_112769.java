class DummyClass_112769 {
	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveNoNext() throws Exception {
		Dao<Foo, Object> dao = createDao(Foo.class, true);
		CloseableIterator<Foo> iterator = dao.iterator();
		try {
			iterator.remove();
		} finally {
			iterator.close();
		}
	}

}