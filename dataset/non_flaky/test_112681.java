class DummyClass_112681 {
	@Test
	public void testUpdate() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		assertEquals(1, dao.create(one));
		String stuff2 = "fjpfejpwewpfjewfew";
		one.stuff = stuff2;
		assertEquals(1, one.update());
		One one2 = dao.queryForId(one.id);
		assertEquals(stuff2, one2.stuff);
	}

}