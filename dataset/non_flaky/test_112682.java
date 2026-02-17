class DummyClass_112682 {
	@Test
	public void testUpdateId() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		assertEquals(1, dao.create(one));
		int id = one.id;
		assertNotNull(dao.queryForId(id));
		assertEquals(1, one.updateId(id + 1));
		assertNull(dao.queryForId(id));
		assertNotNull(dao.queryForId(id + 1));
	}

}