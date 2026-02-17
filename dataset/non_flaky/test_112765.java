class DummyClass_112765 {
	@Test
	public void testDeleteMethod() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		Foo foo = new Foo();
		foo.val = 123123;
		assertEquals(1, dao.create(foo));

		assertNotNull(dao.queryForId(foo.id));
		DeleteBuilder<Foo, Integer> db = dao.deleteBuilder();
		// no match
		db.where().eq(Foo.VAL_COLUMN_NAME, foo.val + 1);
		assertEquals(0, db.delete());
		assertNotNull(dao.queryForId(foo.id));

		db.where().reset();
		db.where().eq(Foo.VAL_COLUMN_NAME, foo.val);
		assertEquals(1, db.delete());
		assertNull(dao.queryForId(foo.id));
	}

}