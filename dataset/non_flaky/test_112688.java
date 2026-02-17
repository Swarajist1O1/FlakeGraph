class DummyClass_112688 {
	@Test
	public void testForeign() throws Exception {
		Dao<One, Integer> oneDao = createDao(One.class, true);
		Dao<ForeignDaoEnabled, Integer> foreignDao = createDao(ForeignDaoEnabled.class, true);

		One one = new One();
		String stuff = "fewpfjewfew";
		one.stuff = stuff;
		one.setDao(oneDao);
		assertEquals(1, one.create());

		ForeignDaoEnabled foreign = new ForeignDaoEnabled();
		foreign.one = one;
		foreign.setDao(foreignDao);
		assertEquals(1, foreign.create());

		ForeignDaoEnabled foreign2 = foreignDao.queryForId(foreign.id);
		assertNotNull(foreign2);
		assertEquals(one.id, foreign2.one.id);
		assertNull(foreign2.one.stuff);
		assertEquals(1, foreign2.one.refresh());
		assertEquals(stuff, foreign2.one.stuff);
	}

}