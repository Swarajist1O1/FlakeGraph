class DummyClass_112683 {
	@Test
	public void testDelete() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		assertEquals(1, dao.create(one));
		assertNotNull(dao.queryForId(one.id));
		assertEquals(1, one.delete());
		assertNull(dao.queryForId(one.id));
	}

}