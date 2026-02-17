class DummyClass_112766 {
	@Test
	public void testUpdateLimit() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		int num = 3;
		for (int i = 0; i < num; i++) {
			dao.create(new Foo());
		}
		long limit = 2;
		assertEquals(limit, dao.deleteBuilder().limit(limit).delete());
		int count = (int) dao.countOf();
		assertEquals(num - limit, count);
		assertNotEquals(num, count);
	}

}